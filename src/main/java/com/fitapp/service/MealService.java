package com.fitapp.service;

import com.fitapp.model.AppUser;
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
    @Autowired
    private UserService userService;


    public Meal saveMeal(Meal meal) {
       return  mealRepository.save(meal);
    }

    public Meal findById(Integer id) {
        return mealRepository.findOne(id);
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

    public void calculateCaloriesPerMeal(int id, int mealId) {

        int mealCalories = (int)(userService.findById(id).getCalories() * 0.33);

        Meal meal = mealRepository.findOne(mealId);

        meal.setCalTarget(mealCalories);

        int proteinsCals = (int)(userService.findById(id).getWeight() * 1.8 * 4 * 0.33 );
        meal.setCalsFromProteins(proteinsCals);

        int fatsCals =(int)(mealCalories * 0.3);
        meal.setCalsFromFats(fatsCals);

        int carbsCals = mealCalories - (fatsCals + proteinsCals);
        meal.setCalsFromCarbs(carbsCals);

        mealRepository.save(meal);


    }
}
