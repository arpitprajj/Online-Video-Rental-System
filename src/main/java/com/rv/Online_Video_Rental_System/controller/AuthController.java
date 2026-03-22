package com.rv.Online_Video_Rental_System.controller;

import com.rv.Online_Video_Rental_System.dto.RequestDto;
import com.rv.Online_Video_Rental_System.entity.User;
import com.rv.Online_Video_Rental_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody User user){
        User user1=userService.createUser(user);
        if(user1!=null){
            return ResponseEntity.ok("You successfully registered, please login");
        }
        return ResponseEntity.badRequest().build();
    }
//    @PostMapping("/login")
//    public ResponseEntity<String>login(@RequestBody RequestDto requestDto){
//
//    }
}
