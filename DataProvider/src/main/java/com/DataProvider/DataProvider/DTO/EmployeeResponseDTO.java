package com.DataProvider.DataProvider.DTO;

import com.DataProvider.DataProvider.Entity.Department;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Entity.Role;

import java.util.List;

public class EmployeeResponseDTO {
    private int id;
    private String name;
    private boolean enabled;
    private int salary;
    private DepartmentResponseDTO department;
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public DepartmentResponseDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponseDTO department) {
        this.department = department;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public EmployeeResponseDTO(Employee employee ) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.enabled = employee.isEnabled();
        this.salary = employee.getSalary();
        DepartmentResponseDTO departmentResponseDTO=new DepartmentResponseDTO(employee.getDepartment());
        this.department=departmentResponseDTO;
        this.roles=employee.getRoles();
    }

    public EmployeeResponseDTO() {
    }
}
