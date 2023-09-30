package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository <Role, Integer>{
    List<Role> findAllByIdIn(List <Integer> ids);

}
