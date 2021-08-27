package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //if role is not ADMIN, 403
    @RequestMapping(value="/test", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testDelete(){
        return "testDelete";
    }

    //if role is not USER, 403
    @RequestMapping(value="/test", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testGet(){
        return "testGet";
    }

    @RequestMapping(value="/test", method = RequestMethod.POST)
    public String testPost(){
        return "testPost";
    }
}
