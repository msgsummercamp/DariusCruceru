package com.example.springbootchapter.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record User(@NotNull(message = "ID is required") Long id, @NotBlank(message = "Name cannot be blank") String name) {

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }
}
