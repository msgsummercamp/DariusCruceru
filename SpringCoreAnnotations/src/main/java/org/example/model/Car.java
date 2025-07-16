package org.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {
    Engine engine;

    @Autowired
    public Car(@Qualifier("electricEngine") Engine engine) {
        this.engine = engine;
    }


    public void drive() {
        System.out.println("Driving a car with engine: " + engine.getType() + ", " + engine.getHorsepower() + " HP");
    }

}
