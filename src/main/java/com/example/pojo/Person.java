package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.LocalDate;

@JsonRootName("person")
public class Person {
    @JsonProperty
    private String name;
    @JsonProperty
    private int id;
    @JsonFormat(pattern = "yyyy-MM-DD")
    private LocalDate birthday;

    public Person(String name, int id, LocalDate birthday) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
    }
}
