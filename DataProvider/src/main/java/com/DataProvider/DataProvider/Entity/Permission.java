package com.DataProvider.DataProvider.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String permissionName;
    private String permissionType;
    private boolean status;
    private String permissionUrl;

    public Permission(int id, String permissionName, String permissionType, boolean status, String permissionUrl) {
        this.id = id;
        this.permissionName = permissionName;
        this.permissionType = permissionType;
        this.status = status;
        this.permissionUrl = permissionUrl;
    }

    public Permission() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }
}
