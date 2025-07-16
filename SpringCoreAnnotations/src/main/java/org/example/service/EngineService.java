package org.example.service;

import org.example.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EngineService {

    private final Engine v8;
    private final Engine electric;

    @Autowired
    public EngineService(@Qualifier("v8Engine") Engine v8,
                         @Qualifier("electricEngine") Engine electric) {
        this.v8 = v8;
        this.electric = electric;
    }

    public void compare() {
        System.out.println("Comparing engines:");
        System.out.println("V8 HP: " + v8.getHorsepower());
        System.out.println("Electric HP: " + electric.getHorsepower());
    }
}
