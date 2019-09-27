package com.cappuccino.foodcourter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodCourterApplication {

    public static void main(String[] args){
        SpringApplication.run(FoodCourterApplication.class, args);
    }

}
