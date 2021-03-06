package com.fitapp.controller;

import com.fitapp.model.*;
import com.fitapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class  UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MealService mealService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private DietService dietService;


    @PostMapping(path="/users/sign-up")
    public ResponseEntity<AppUser> addNewUser (@Valid @RequestBody AppUser user) {

        user.setPassword(passwordService.encodePassword(user.getPassword()));
        user.setCalories(userService.calculateCalories(user));
        dietService.saveDiet(new Diet(), user);
        userService.saveUser(user);


        return new ResponseEntity<AppUser>(user, HttpStatus.OK);
    }


    @GetMapping(path="/users")
    public  Iterable<AppUser> getAllUsers() {

        return userService.findAllUsers();
    }

    @GetMapping(path="/users/{id}")
    public ResponseEntity getUser(@PathVariable Integer id) {
        if(userService.ifUserIdExist(id)) {
            AppUser user = userService.findById(id);

            return ResponseEntity.ok(user);
        }
        return null;
    }

    @GetMapping(path = "/getUserIdFromToken")
    public ResponseEntity getUserID() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser user = userService.findUserByName(auth.getName());


        return ResponseEntity.ok(user);
    }


    @PutMapping(path="/users/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Integer id, @RequestBody AppUser user){
        AppUser currtUser = userService.findById(id);

        if(currtUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }


        userService.saveUser(currtUser);

        return new ResponseEntity<AppUser>(currtUser, HttpStatus.OK);


    }
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable Integer id ) {
        if (id != null) {
            if(userService.ifUserIdExist(id)) {
                userService.deleteUserById(id);
            }
        }
    }

    @PostMapping(path = "/users/{id}/measurements")
    public ResponseEntity<Measurement> addUserMeasurements (@PathVariable Integer id, @RequestBody Measurement measurement) {
        userService.addUserMeasurements(measurement,id);

        return new ResponseEntity<Measurement>(measurement,HttpStatus.OK);
    }

//    @GetMapping(path = "users/{id}/calories")
//    public int getCalories(@PathVariable Integer id) {
//        return userService.calculateCalories(id);
//    }

//    @PostMapping(path = "users/{id}/diets/meals/{mealId}")
//    public ResponseEntity<Meal> setUserCaloriesPerMeal (@PathVariable Integer id , @PathVariable Integer mealId) {
//
//        mealService.calculateCaloriesPerMeal(id,mealId);
//
//        foodService.calculateAmountofFood(mealId);
//
//        return new ResponseEntity<Meal>(mealService.findById(mealId),HttpStatus.OK);

   // }

    @GetMapping(path ="users/{id}/measurements")
    public  Iterable<Measurement> getUserMeasurements (@PathVariable Integer id) {

        AppUser user = userService.findById(id);

        return user.getMeasurements();
    }


    @GetMapping(path = "users/{id}/diets")
    public  Iterable<Food> getUserDiet (@PathVariable Integer id) {
        AppUser user = userService.findById(id);


         Set<Meal> meals = user.getDiet().getMeals();

        List<Food> foods = new ArrayList<>();

        for (Meal meal:meals) {
             foods.addAll(meal.getFoods());
        }

//
//        for (Meal meal: meals) {
//             System.out.println(meal.getFoods().size());
//
//        }

//        while(true) {
//            for (Meal meal : meals) {
//                if (meal.getName().equals("meal1") && foods.size() == 0) {
//
//                    foods.addAll(meal.getFood());
//
//                } else if (meal.getName().equals("meal2") && foods.size() == 3) {
//
//                    foods.addAll(meal.getFood());
//
//                } else if (meal.getName().equals("meal3") && foods.size() == 6) {
//
//                    foods.addAll(meal.getFood());
//                }
//
//
//            }
//
//            if(foods.size() == 9) {
//                break;
//            }
//        }

        return foods;
    }
}