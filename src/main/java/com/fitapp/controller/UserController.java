package com.fitapp.controller;

import com.fitapp.model.Measurements;
import com.fitapp.model.User;
import com.fitapp.repository.MeasurementsRepository;
import com.fitapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class  UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MeasurementsRepository measurementsRepository;


    @PostMapping(path="/users")
    public ResponseEntity<User> addNewUser (@Valid @RequestBody User user) {

        //if (user != null) {

            //user = new User(user.getName(), user.getEmail(),passwordService.encodePassword(user.getPassword()));
            userService.saveUser(user);
        //}
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @GetMapping(path="/users")
    public  Iterable<User> getAllUsers() {

        return userService.findAllUsers();
    }

    @GetMapping(path="/users/{id}")
    public ResponseEntity getUser(@PathVariable Integer id) {
        if(userService.ifUserIdExist(id)) {
            User user = userService.findById(id);

            return ResponseEntity.ok(user);
        }
        return null;
    }



    @PutMapping(path="/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        User currtUser = userService.findById(id);

        if(currtUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currtUser.setEmail(user.getEmail());
        currtUser.setPassword(user.getPassword());
        userService.saveUser(currtUser);

        return new ResponseEntity<User>(currtUser, HttpStatus.OK);


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
    public ResponseEntity<Measurements> addUserMeasurements (@PathVariable Integer id,@RequestBody Measurements measurements) {
        userService.addUserMeasurements(measurements,id);

        return new ResponseEntity<Measurements>(measurements,HttpStatus.OK);
    }


}