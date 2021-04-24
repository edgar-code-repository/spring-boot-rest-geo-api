package com.example.springbootrestgeoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "countries")
@Data
@NoArgsConstructor
public class Country {

    @Id
    private String id;
    private String name;
    private Continent continent;

    public String toString() {
        String str;

        str = "Country [";
        str = str + "Id: " + this.id + " - ";
        str = str + "Name: " + this.name + "]\n";
        str = str + "Continent: " + this.continent + "]\n";

        return str;
    }
}
