package com.example.springbootrestgeoapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "continents")
public class Continent {

    @Id
    private String id;

    private String code;

    private String name;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        str = str + "Code: " + this.code + " - ";
        str = str + "Name: " + this.name + "]\n";

        return str;
    }

}

