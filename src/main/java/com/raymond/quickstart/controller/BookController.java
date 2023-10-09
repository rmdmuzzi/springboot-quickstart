package com.raymond.quickstart.controller;

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

    @GetMapping
    public String getById() {
        System.out.println("Springboot is running...");
        return "Springboot is running...";
    }
}
