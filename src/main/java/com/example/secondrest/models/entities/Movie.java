package com.example.secondrest.models.entities;

public class Character {
    public int id;
    public String name;
    public double rating;

    public Character(int id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }
}
