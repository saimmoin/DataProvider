package com.DataProvider.DataProvider.Entity;

import com.DataProvider.DataProvider.DTO.EmployeeRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
public class Employee   implements UserDetails,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private boolean enabled;
    private int salary;
    private String password;
    @Column(name = "email_address")
    private String emailAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles=new ArrayList<>();
    public Employee(int id, String name, boolean enabled, int salary, Department department) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.salary = salary;
        this.department = department;
    }

    public Employee() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        roles.stream().forEach(r->{
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(r.getName());
            list.add(simpleGrantedAuthority);

        });
        return  list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
