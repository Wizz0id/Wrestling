package com.example.Wrestling.enumurate;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String description;

    Gender(String desc){
        this.description = desc;
    }
    public String getDescription(){
        return description;
    }
}
