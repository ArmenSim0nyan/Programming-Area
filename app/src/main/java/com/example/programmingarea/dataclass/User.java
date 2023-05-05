package com.example.programmingarea.dataclass;

public class User {
    private String name;
    private String language;
    private Integer points;
    private Integer win;
    private Integer lose;

    public User() {

    }

    public User(String name, String language, Integer points, Integer win, Integer lose) {
        this.name = name;
        this.language = language;
        this.points = points;
        this.win = win;
        this.lose = lose;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getWin() {
        return win;
    }

    public Integer getLose() {
        return lose;
    }
}
