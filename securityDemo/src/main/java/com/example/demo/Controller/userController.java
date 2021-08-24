package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String userGet(){
        return "userGet";
    }
}
