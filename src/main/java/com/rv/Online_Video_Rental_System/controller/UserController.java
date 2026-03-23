package com.rv.Online_Video_Rental_System.controller;

import com.rv.Online_Video_Rental_System.entity.User;
import com.rv.Online_Video_Rental_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/admin/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        Optional<User> user = userService.getUserById(id);


        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id,
                                        @RequestBody User user) {

            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);


        }


    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {


            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");

    }
}