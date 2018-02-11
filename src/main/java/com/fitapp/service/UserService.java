package com.fitapp.service;

import com.fitapp.model.AppUser;
import com.fitapp.model.Measurement;
import com.fitapp.repository.MeasurementRepository;
import com.fitapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private MeasurementRepository measurementRepository;

   public AppUser saveUser (AppUser user) {
       user.setPassword(passwordService.encodePassword(user.getPassword()));

       return userRepository.save(user);
   }

   public List<AppUser> findAllUsers() {
       return userRepository.findAll();
   }

   public boolean ifUserIdExist (Integer id) {
       return userRepository.exists(id);
   }

   public AppUser findById(Integer id) {
       return userRepository.findOne(id);
   }

   public void deleteUserById(Integer id) {
       userRepository.delete(id);
   }
   public void deleteAllUsers(){
       userRepository.deleteAll();
   }

   public AppUser addUserMeasurements(Measurement measurement, int id){


       AppUser user = userRepository.findOne(id);

       measurement.setUser(user);

       user.getMeasurements().add(measurement);

       return userRepository.save(user);



   }

   public int calculateCalories(int id) {

       AppUser user = userRepository.findOne(id);

       double calories = 0;


       if(user.getGender() == "female") {
           calories = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
           calories *= user.getActivity();
       } else {
           calories = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
           calories *= user.getActivity();
       }

       if(user.getGoal() == "bulk"){
           calories = calories + (15 * calories) / 100;

       } else if(user.getGoal() == "cut") {

           calories = calories - (15 * calories) / 100 ;
       }

        int roundCalories = (int)calories;
        user.setCalories(roundCalories);
        userRepository.save(user);

        return roundCalories;

       //for females = 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age - 161;
       // for males= 10 x (Weight in kg) + 6.25 x (Height in cm) - 5 x age + 5;

   }

   public AppUser findUserByName (String name) {
       return userRepository.findByName(name);
   }


}
