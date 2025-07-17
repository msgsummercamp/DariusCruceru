package com.example.springbootchapter.model;

public record User(Long id, String name) {

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }
}
