package com.fitapp.service;

import com.fitapp.model.AppUser;
import com.fitapp.model.Diet;
import com.fitapp.model.Food;
import com.fitapp.model.Meal;
import com.fitapp.repository.DietRepository;
import com.fitapp.repository.MealRepository;
import com.fitapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DietService {

    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealService mealService;
    @Autowired
    private FoodService foodService;


    public void saveDiet (Diet diet , AppUser user) {
        Meal meal1 = new Meal();
        meal1.setName("meal1");
        mealService.calculateCaloriesPerMeal(user,meal1);

        Meal meal2 = new Meal();
        meal2.setName("meal2");
        mealService.calculateCaloriesPerMeal(user,meal2);

        Meal meal3 = new Meal();
        meal3.setName("meal3");
        mealService.calculateCaloriesPerMeal(user,meal3);

        List<Food> foodList1 = new ArrayList<>(
                Arrays.asList(
                        new Food("spagetti1", 3.7,"Carbs"),
                        new Food("pork neck1", 1.86,"Proteins"),
                        new Food ("olive oil1",8.86,"Fats")

                ));

        for (Food food:foodList1) {

            food.setMeal(meal1);
        }

        meal1.setFoods(foodList1);
        foodService.calculateAmountofFood1(meal1);



        List<Food> foodList2 = new ArrayList<>(
                Arrays.asList(
                        new Food("oats1", 3.02,"Carbs"),
                        new Food("cottage cheese1", 1.11,"Proteins"),
                        new Food ("butter1",7.16,"Fats")

                ));

        for (Food food:foodList2) {

            food.setMeal(meal2);
        }

        meal2.setFoods(foodList2);
        foodService.calculateAmountofFood1(meal2);


        List<Food> foodList3 = new ArrayList<>(
                Arrays.asList(
                        new Food("rice1", 3.65, "Carbs"),
                        new Food("chicken breast1", 1.64, "Proteins"),
                        new Food ("almonds1",5.75,"Fats")

                ));

        for (Food food:foodList3) {

            food.setMeal(meal3);
        }

        meal3.setFoods(foodList3);
        foodService.calculateAmountofFood1(meal3);



        Set<Meal> meals = new LinkedHashSet<>();

        meals.add(meal1);
        meals.add(meal2);
        meals.add(meal3);


        addDietMeals(meals, diet);

       // dietRepository.save(diet);

        addDietUsers(user, diet);


    }

    public List<Diet> findAllDiets() {
        return dietRepository.findAll();
    }

    public void deleteAllDiets(){
        dietRepository.deleteAll();
    }

    public void addDietMeals(Set<Meal> meals, Diet diet){

        for (Meal meal: meals) {
            meal.setDiet(diet);
        }

        diet.getMeals().addAll(meals);


        //return dietRepository.save(diet);


    }

    public Diet addDietUsers(AppUser user, Diet diet) {

        //AppUser user = userRepository.findOne(userId);

        //Diet diet = dietRepository.findOne(id);

        user.setDiet(diet);

        diet.getUsers().add(user);

        return dietRepository.save(diet);
    }

}
