package com.fitapp.service;

import com.fitapp.model.User;
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

   public User saveUser (User user) {
       user.setName(user.getName());
       user.setEmail(user.getEmail());
       user.setPassword(passwordService.encodePassword(user.getPassword()));

       return userRepository.save(user);
   }

   public List<User> findAllUsers() {
       return userRepository.findAll();
   }

   public boolean ifUserIdExist (Integer id) {
       return userRepository.exists(id);
   }

   public User findById(Integer id) {
       return userRepository.findOne(id);
   }

   public void deleteUserById(Integer id) {
       userRepository.delete(id);
   }
   public void deleteAllUsers(){
       userRepository.deleteAll();
   }

}
