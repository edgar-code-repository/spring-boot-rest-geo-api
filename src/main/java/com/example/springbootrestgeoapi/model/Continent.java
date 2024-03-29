package com.example.springbootrestgeoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "continents")
@Data
@NoArgsConstructor
public class Continent {

    @Id
    private String id;

    private String name;

    public Continent(String name) {
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

