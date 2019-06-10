package com.example.springbootrestgeoapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "continents")
public class Continent {

    @Id
    private String id;

    private String name;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        String str;

        str = "Continent [";
        str = str + "Id: " + this.id + " - ";
        str = str + "Name: " + this.name + "]\n";

        return str;
    }

}

