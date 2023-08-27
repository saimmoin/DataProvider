package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @GeneratedValue
    @Id
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "course_name")

    private String name ;

}
