package com.rv.Online_Video_Rental_System.controller;

import com.rv.Online_Video_Rental_System.dto.RequestDto;
import com.rv.Online_Video_Rental_System.entity.User;
import com.rv.Online_Video_Rental_System.service.UserService;
import com.rv.Online_Video_Rental_System.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody User user){
        User user1=userService.createUser(user);
        if(user1!=null){
            return ResponseEntity.ok("You successfully registered, please login");
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of(
                "token", token
        ));
    }
}
