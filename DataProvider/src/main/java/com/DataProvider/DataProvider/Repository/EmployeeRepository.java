package com.DataProvider.DataProvider.Repository;

import com.DataProvider.DataProvider.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
        List<Employee> getEmployeesByEnabledTrue();
//        Optional<Employee> findAllByEmailAddress(String email);
        Employee findAllByEmailAddress(String email);

        Employee findByEmailAddressAndPassword(String email,String password);
}
