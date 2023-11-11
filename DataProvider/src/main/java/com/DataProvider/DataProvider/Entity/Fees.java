package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "fees")

public class Fees {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private int fees;
    private String feesType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public String getFeesType() {
        return feesType;
    }

    public void setFeesType(String feesType) {
        this.feesType = feesType;
    }

    public Fees(int id, int fees, String feesType) {
        this.id = id;
        this.fees = fees;
        this.feesType = feesType;
    }

    public Fees() {
    }


}
