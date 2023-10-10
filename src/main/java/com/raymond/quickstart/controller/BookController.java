package com.raymond.quickstart.controller;

import com.raymond.quickstart.config.MyDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raymondmuzzi
 * @since 2023-10-09 20:35:59
 */
@RestController
@RequestMapping("/books")
public class BookController {

//    @Value("${country}")
//    private String country1;
//
//    @Value("${user.name}")
//    private String userName;
//
//    @Autowired
//    private MyDatasource myDatasource;

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping
    public String getById() {
        System.out.println("Springboot is running...");
//        System.out.println("Country1: " + country1);
//        System.out.println("User name: " + userName);
//        System.out.println(myDatasource);
        System.out.println(serverProperties);
        return "Springboot is running...";
    }

}
