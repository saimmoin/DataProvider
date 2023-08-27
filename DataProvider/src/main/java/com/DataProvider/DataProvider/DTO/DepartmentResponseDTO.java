package com.DataProvider.DataProvider.DTO;

import com.DataProvider.DataProvider.Entity.Department;

public class DepartmentResponseDTO {
    private int id;
    private String name;
    private boolean enabled;

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

    public DepartmentResponseDTO(Department dept) {
        this.id = dept.getId();
        this.name = dept.getName();
        this.enabled = dept.isEnabled();
    }
}
