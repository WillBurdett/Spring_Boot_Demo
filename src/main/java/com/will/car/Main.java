package com.will.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

//        You'd think it looks like this, but with spring it does not
//        CarDAO carDAO = new FakeCarDataAccessService();
//        CarService carService = new CarService(carDAO);
//        CarController carController = new CarController();
        SpringApplication.run(Main.class, args);
    }
}
