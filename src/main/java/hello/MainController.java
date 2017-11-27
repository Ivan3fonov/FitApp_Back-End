package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import hello.User;
import hello.UserRepository;

import javax.validation.Valid;

@RestController

public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/users")
    public ResponseEntity<User> addNewUser (@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            //
            //??
           // return result.getAllErrors();
        }
        if (user != null) {
            user = new User(user.getName(), user.getEmail(), user.getPassword());
            userRepository.save(user);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @GetMapping(path="/users")
    public  Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(path="/users/{id}")
    public ResponseEntity getUser(@PathVariable Integer id) {
        if(userRepository.exists(id)) {
            User user = userRepository.findOne(id);

            return ResponseEntity.ok(user);
        }
        return null;
    }

  /*  @PutMapping(path="/update/{id}")
    public  void updateUser (@PathVariable Integer id,
                             @RequestParam String name,
                             @RequestParam String email) {

       if (userRepository.exists(id)) {
            User user = userRepository.findOne(id);

            if (name != null)
                user.setName(name);
            if (email != null)
                user.setEmail(email);

            userRepository.save(user);
        }
    }
    */

    @PutMapping(path="/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        User currtUser = userRepository.findOne(id);

        if(currtUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currtUser.setEmail(user.getEmail());
        currtUser.setPassword(user.getPassword());
        userRepository.save(currtUser);

        return new ResponseEntity<User>(currtUser, HttpStatus.OK);


    }
    @DeleteMapping(path="/users/{id}")
    public void deleteUser(@PathVariable Integer id ) {
        if (id != null) {
            if(userRepository.exists(id)) {
                userRepository.delete(id);
            }
        }
    }

}