package com.fitapp.service;

import com.fitapp.model.Food;
import com.fitapp.model.Meal;
import com.fitapp.repository.FoodRepository;
import com.fitapp.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MealService {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private FoodRepository foodRepository;


    public Meal saveMeal(Meal meal) {
       return  mealRepository.save(meal);
    }

    public void deleteMeal(Meal meal) {
        mealRepository.delete(meal);
    }

    public void deleteAllMeals(){
        mealRepository.deleteAll();
    }

    public Meal addMealFoods(Food food, int id){


        Meal meal = mealRepository.findOne(id);

        food.setMeal(meal);

        meal.getFood().add(food);

        return  mealRepository.save(meal);

    }
}
