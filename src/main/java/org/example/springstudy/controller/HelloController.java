package org.example.springstudy.controller;

import org.example.springstudy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    } // 생성자로

    @GetMapping("/hello")
    public String hi(){
        String output = helloService.hi();
        return output;
    }


}
