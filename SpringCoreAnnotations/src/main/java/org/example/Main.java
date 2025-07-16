package org.example;

import org.example.Model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // This tells Spring to scan all packages under "org.example"
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        Car car = context.getBean(Car.class);
        car.drive();
    }
}
