package com.fitapp.service;

import com.fitapp.model.AppUser;
import com.fitapp.model.Measurements;
import com.fitapp.repository.MeasurementsRepository;
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
    private MeasurementsRepository measurementsRepository;

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

   public Measurements addUserMeasurements(Measurements measurements,int id){


       AppUser user = userRepository.findOne(id);

       measurements.setUser(user);

       user.getMeasurements().add(measurements);

       userRepository.save(user);

       return measurementsRepository.save(measurements);


   }

}
