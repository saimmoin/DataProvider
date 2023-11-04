package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;
@Entity
@Table(name = "Player")


public class Player {

    private String name;
    private String profession;

    @GeneratedValue
    @Id
    private  int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @OneToOne(fetch = FetchType.EAGER) // EAGER loading brings all the data togethere(as soon as the code is executed)
    @JoinColumn(name = "address")
    private Address address;

}
