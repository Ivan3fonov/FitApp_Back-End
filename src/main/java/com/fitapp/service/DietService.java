package com.fitapp.service;

import com.fitapp.model.Diet;
import com.fitapp.model.Meal;
import com.fitapp.repository.DietRepository;
import com.fitapp.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DietService {

    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private MealRepository mealRepository;


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
}
