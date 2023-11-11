package com.DataProvider.DataProvider.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserType() {
    }
}
