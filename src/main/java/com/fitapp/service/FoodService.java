package com.fitapp.service;

import com.fitapp.model.Food;
import com.fitapp.model.Meal;
import com.fitapp.repository.FoodRepository;
import com.fitapp.repository.MealRepository;
import com.fitapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealRepository mealRepository;

    private Food saveFood(Food food){
        return foodRepository.save(food);
    }

    private void deleteFoodById(Food food){
        foodRepository.delete(food);
    }

    public void deleteAllFoods(){
        foodRepository.deleteAll();
    }

    public void calculateAmountofFood  ( int  mealId) {

     List<Food> foods = mealRepository.findOne(mealId).getFood();

        for (Food food:foods) {

            if(food.getPredominatMacros().equals("Proteins")) {
               int foodAmount = (int)(mealRepository.findOne(mealId).getCalsFromProteins() / food.getCalsPerUnit());
               food.setAmount(foodAmount);

            } else if (food.getPredominatMacros().equals("Carbs")) {
                int foodAmount = (int)(mealRepository.findOne(mealId).getCalsFromCarbs() / food.getCalsPerUnit());
                food.setAmount(foodAmount);

            } else {
                int foodAmount = (int)(mealRepository.findOne(mealId).getCalsFromFats() / food.getCalsPerUnit());
                food.setAmount(foodAmount);
            }
        }

        foodRepository.save(foods);
    }

    public void calculateAmountofFood1  (Meal meal) {

        List<Food> foods =  meal.getFood();

        for (Food food:foods) {

            if(food.getPredominatMacros().equals("Proteins")) {
                int foodAmount = (int)(meal.getCalsFromProteins() / food.getCalsPerUnit());
                food.setAmount(foodAmount);

            } else if (food.getPredominatMacros().equals("Carbs")) {
                int foodAmount = (int)(meal.getCalsFromCarbs() / food.getCalsPerUnit());
                food.setAmount(foodAmount);

            } else {
                int foodAmount = (int)(meal.getCalsFromFats() / food.getCalsPerUnit());
                food.setAmount(foodAmount);
            }
        }

       // foodRepository.save(foods);
    }

}
