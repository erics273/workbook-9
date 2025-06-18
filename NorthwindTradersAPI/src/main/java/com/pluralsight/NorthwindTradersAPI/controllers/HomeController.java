package com.pluralsight.NorthwindTradersAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homePage(@RequestParam(defaultValue="World") String country){
        return "Hello " + country;
    }

    @GetMapping("/eric")
    public String ericPage(){
        return "Hello Eric";
    }

}
