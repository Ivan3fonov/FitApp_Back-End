package com.fitapp.controller;

import com.fitapp.model.AppUser;
import com.fitapp.model.Measurement;
import com.fitapp.repository.MeasurementRepository;
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
    private MeasurementRepository measurementRepository;


    @PostMapping(path="/users/sign-up")
    public ResponseEntity<AppUser> addNewUser (@Valid @RequestBody AppUser user) {
;
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



    @PutMapping(path="/users/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Integer id, @RequestBody AppUser user){
        AppUser currtUser = userService.findById(id);

        if(currtUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }

        currtUser.setEmail(user.getEmail());
        currtUser.setPassword(user.getPassword());
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

    @GetMapping(path = "users/{id}/calories")
    public int getCalories(@PathVariable Integer id) {
        return userService.calculateCalories(id);
    }


}