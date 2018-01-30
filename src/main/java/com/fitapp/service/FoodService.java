package com.fitapp.service;

import com.fitapp.model.Food;
import com.fitapp.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    private Food saveFood(Food food){
        return foodRepository.save(food);
    }

    private void deleteFoodById(Food food){
        foodRepository.delete(food);
    }

    public void deleteAllFoods(){
        foodRepository.deleteAll();
    }

}
