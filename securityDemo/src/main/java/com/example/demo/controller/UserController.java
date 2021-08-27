package com.example.demo.controller;

import com.example.demo.model.JwtToken;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public String userPost(@RequestBody User user){
        String newPw = new BCryptPasswordEncoder().encode(user.getUserPw());
        user.setUserPw(newPw);
        userService.addUser(user);
        return "userPost";
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public ResponseEntity<JwtToken> userGet(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User result = userService.getUser(user);
        JwtToken jwt = new JwtToken();
        if((result == null)||(!encoder.matches(user.getUserPw(), result.getUserPw())))
            return new ResponseEntity<>(jwt, HttpStatus.UNAUTHORIZED);
        user.setUserPw("");
        jwt.setAccessToken(jwtUtil.generateJwtToken(result));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
