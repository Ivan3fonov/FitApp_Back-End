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
                        new Food("spagetti", 3.7,"Carbs"),
                        new Food("pork neck", 1.86,"Proteins"),
                        new Food ("olive oil",8.86,"Fats")

                ));

        for (Food food:foodList1) {

            food.setMeal(meal1);
        }

        meal1.setFoods(foodList1);
        foodService.calculateAmountofFood1(meal1);



        List<Food> foodList2 = new ArrayList<>(
                Arrays.asList(
                        new Food("oats", 3.02,"Carbs"),
                        new Food("cottage cheese", 1.11,"Proteins"),
                        new Food ("butter",7.16,"Fats")

                ));

        for (Food food:foodList2) {

            food.setMeal(meal2);
        }

        meal2.setFoods(foodList2);
        foodService.calculateAmountofFood1(meal2);


        List<Food> foodList3 = new ArrayList<>(
                Arrays.asList(
                        new Food("rice", 3.65, "Carbs"),
                        new Food("chicken breast", 1.64, "Proteins"),
                        new Food ("almonds",5.75,"Fats")

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


        if(user.getMeals() == 4 || user.getMeals() == 5 ) {

            Meal meal4 = new Meal();
            meal4.setName("meal4");
            mealService.calculateCaloriesPerMeal(user,meal4);

            List<Food> foodList4 = new ArrayList<>(
                    Arrays.asList(
                            new Food("banana", 0.81, "Carbs"),
                            new Food("whey protein",3.89, "Proteins"),
                            new Food ("milk",0.61,"Fats")

                    ));

            for (Food food:foodList4) {

                food.setMeal(meal4);
            }

            meal4.setFoods(foodList4);
            foodService.calculateAmountofFood1(meal4);

            meals.add(meal4);
        }

        if(user.getMeals() == 5) {

            Meal meal5 = new Meal();
            meal5.setName("meal5");
            mealService.calculateCaloriesPerMeal(user,meal5);

            List<Food> foodList5 = new ArrayList<>(
                    Arrays.asList(
                            new Food("wholegrain bread", 2.5, "Carbs"),
                            new Food("eggs-Lsize",74, "Proteins"),
                            new Food ("avocado",1.6,"Fats")

                    ));

            for (Food food:foodList5) {

                food.setMeal(meal5);
            }

            meal5.setFoods(foodList5);
            foodService.calculateAmountofFood1(meal5);

            meals.add(meal5);

        }




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

    public void addDietUsers(AppUser user, Diet diet) {

        //AppUser user = userRepository.findOne(userId);

        //Diet diet = dietRepository.findOne(id);

        user.setDiet(diet);

        diet.setUser(user);

    }

}
