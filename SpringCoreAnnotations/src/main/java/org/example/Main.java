package org.example;

import org.example.model.Car;
import org.example.model.Engine;
import org.example.service.EngineService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // This tells Spring to scan all packages under "org.example"
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        EngineService engineService = context.getBean(EngineService.class);
        engineService.compare(); // ✅ Calls your method

        Car car = context.getBean(Car.class);
        car.drive();
    }
}
