package com.fitapp.service;

import com.fitapp.model.AppUser;
import com.fitapp.model.Diet;
import com.fitapp.model.Meal;
import com.fitapp.repository.DietRepository;
import com.fitapp.repository.MealRepository;
import com.fitapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DietService {

    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserRepository userRepository;


    public Diet saveDiet (Diet diet) {

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
