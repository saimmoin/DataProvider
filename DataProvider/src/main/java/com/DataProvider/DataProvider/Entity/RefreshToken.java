package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity // Define Entity for a class
@Data // Getter setter method and converts all the parameter into String method
@Table (name = "RefreshToken") // Creates the table named RefreshToken


public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @Column(name = "token_expiry")
    private Date tokenExpiry;
    @OneToOne(fetch = FetchType.EAGER) // EAGER loading brings all the data togethere(as soon as the code is executed)
    @JoinColumn()
    private Employee user;
    private Boolean status; // boolean(throws exception when null) Boolean(does not throw exception)



}
