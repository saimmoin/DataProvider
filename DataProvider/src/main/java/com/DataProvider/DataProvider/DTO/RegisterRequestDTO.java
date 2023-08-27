package com.DataProvider.DataProvider.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RegisterRequestDTO {
    private int id;
    private String name;
    private boolean enabled;
    private int salary;
    private String password;
    private String emailAddress;
    private Integer department;
    private List<Integer> roles;

}
