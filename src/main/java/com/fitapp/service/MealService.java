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

        meal.getFoods().add(food);

        return  mealRepository.save(meal);

    }

    public void calculateCaloriesPerMeal(AppUser user, Meal meal) {

        int mealCalories = 0;
        int proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.33);

        if(user.getMeals() == 3) {

            mealCalories = (int) (user.getCalories() * 0.33);
        } else if(user.getMeals() == 4) {

           // System.out.println(meal.getName());

            switch (meal.getName()) {

                case "meal1":

                    mealCalories = (int) (user.getCalories() * 0.30);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.25);
                    break;

                case "meal2":

                    mealCalories = (int) (user.getCalories() * 0.35);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.25);
                    break;

                case "meal3":

                    mealCalories = (int) (user.getCalories() * 0.20);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.25);
                    break;

                case "meal4":

                    mealCalories = (int) (user.getCalories() * 0.15);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.25);
                    break;
            }

        } else if(user.getMeals() == 5) {

            switch (meal.getName()) {

                case "meal1":

                    mealCalories = (int) (user.getCalories() * 0.25);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.20);
                    break;

                case "meal2":

                    mealCalories = (int) (user.getCalories() * 0.20);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.20);
                    break;

                case "meal3":

                    mealCalories = (int) (user.getCalories() * 0.25);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.20);
                    break;

                case "meal4":

                    mealCalories = (int) (user.getCalories() * 0.15);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.20);
                    break;

                case "meal5":

                    mealCalories = (int) (user.getCalories() * 0.15);
                    proteinsCals = (int) (user.getWeight() * 1.8 * 4 * 0.20);
                    break;
            }
        }


            meal.setCalTarget(mealCalories);


            meal.setCalsFromProteins(proteinsCals);

            int fatsCals = (int) (mealCalories * 0.3);
            meal.setCalsFromFats(fatsCals);

            int carbsCals = mealCalories - (fatsCals + proteinsCals);
            meal.setCalsFromCarbs(carbsCals);




    }


}
