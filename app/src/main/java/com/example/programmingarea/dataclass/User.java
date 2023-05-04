package com.example.programmingarea.dataclass;

public class User {
    private String name;
    private String language;

    public User() {

    }

    public User(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }
}
