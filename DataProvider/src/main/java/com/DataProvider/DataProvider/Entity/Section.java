package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String sectionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Section(int id, String sectionName) {
        this.id = id;
        this.sectionName = sectionName;
    }

    public Section() {
    }
}
