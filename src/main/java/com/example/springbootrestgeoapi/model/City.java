package com.example.springbootrestgeoapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cities")
@Data
@NoArgsConstructor
public class City {

    @Id
    private String id;
    private String name;
    private Country country;

    public String toString() {
        String str;

        str = "City [";
        str = str + "Id: " + this.id + " - ";
        str = str + "Name: " + this.name + " - ";
        str = str + "Country: " + this.country + "]\n";

        return str;
    }
}
