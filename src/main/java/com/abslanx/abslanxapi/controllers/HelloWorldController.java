package com.abslanx.abslanxapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/api/hello-world")
    public String helloWorld(){
        return "Hello World!  Auth required!";
    };

    @GetMapping("/hello-world")
    public String helloWorld2(){ return "Hello World!  No Auth Needed!"; };

}
