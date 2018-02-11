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


    public Diet saveDiet (Diet diet) {
        Meal meal1 = new Meal();
        meal1.setName("meal1");

        Meal meal2 = new Meal();
        meal2.setName("meal2");

        Meal meal3 = new Meal();
        meal3.setName("meal3");

        List<Food> foodList1 = new ArrayList<>(
                Arrays.asList(
                        new Food("spagetti2", 3.7),
                        new Food("pork neck2", 1.86),
                        new Food ("olive oil2",8.86)

                ));

        for (Food food:foodList1) {

            food.setMeal(meal1);
        }

        meal1.setFood(foodList1);


        List<Food> foodList2 = new ArrayList<>(
                Arrays.asList(
                        new Food("oats", 3.02),
                        new Food("cottage cheese", 1.11),
                        new Food ("butter",7.16)

                ));

        for (Food food:foodList2) {

            food.setMeal(meal2);
        }

        meal2.setFood(foodList2);


        List<Food> foodList3 = new ArrayList<>(
                Arrays.asList(
                        new Food("rice", 1.3),
                        new Food("chicken breast", 1.64),
                        new Food ("almonds",5.75)

                ));

        for (Food food:foodList3) {

            food.setMeal(meal3);
        }

        meal3.setFood(foodList3);



        Set<Meal> meals = new HashSet<Meal>();

        meals.add(meal1);
        meals.add(meal2);
        meals.add(meal3);

        meal1.setDiet(diet);
        meal2.setDiet(diet);
        meal3.setDiet(diet);

        diet.setMeals(meals);

        return dietRepository.save(diet);
    }

    public List<Diet> findAllDiets() {
        return dietRepository.findAll();
    }

    public void deleteAllDiets(){
        dietRepository.deleteAll();
    }

    public Diet addDietMeals(Meal meal, int id){


        Diet diet = dietRepository.findOne(id);

        meal.setDiet(diet);

        diet.getMeals().add(meal);


        return dietRepository.save(diet);


    }

    public Diet addDietUsers(int userId, int id) {

        AppUser user = userRepository.findOne(userId);

        Diet diet = dietRepository.findOne(id);

        user.setDiet(diet);

        diet.getUsers().add(user);

        return dietRepository.save(diet);
    }
}
