package com.wzl.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Movie {
    private Integer id;
    private String name;
    private String director;
    private String location;
    private String actor;
    private Date filmedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Date getFilmedTime() {
        return filmedTime;
    }

    public void setFilmedTime(Date filmedTime) {
        this.filmedTime = filmedTime;
    }
}
