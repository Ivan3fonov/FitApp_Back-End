package com.fitapp.controller;

import com.fitapp.model.Diet;
import com.fitapp.model.Food;
import com.fitapp.model.Meal;
import com.fitapp.service.DietService;
import com.fitapp.service.FoodService;
import com.fitapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/diets")
public class DietController {

    @Autowired
    private DietService dietService;
    @Autowired
    private MealService mealService;
    @Autowired
    private FoodService foodService;



    @PostMapping()
    public ResponseEntity<Diet> createDiet(@Valid @RequestBody Diet diet) {

            dietService.saveDiet(diet);

        return new ResponseEntity<>(diet, HttpStatus.OK);
    }

    @GetMapping()
    public Iterable<Diet> getAllDiets() {

        return dietService.findAllDiets();
    }

    @DeleteMapping()
    public void deleteAllDiets() {

        dietService.deleteAllDiets();
    }

    @PostMapping(path ="/meals")
    public ResponseEntity<Meal> addNewMeal (@Valid @RequestBody Meal meal) {
        ;
        mealService.saveMeal(meal);

        return new ResponseEntity<Meal>(meal, HttpStatus.OK);
    }

    @PostMapping(path = "/meals/{id}/foods")
    public ResponseEntity<Food> addMealFood (@PathVariable Integer id, @RequestBody Food food) {
       mealService.addMealFoods(food,id);

        return new ResponseEntity<Food>(food,HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/meals")
    public ResponseEntity<Meal> addDietMeal (@PathVariable Integer id, @RequestBody Meal meal) {
        dietService.addDietMeals(meal,id);

        return new ResponseEntity<Meal>(meal,HttpStatus.OK);
    }






}
