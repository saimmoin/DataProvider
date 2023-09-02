package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.RegisterRequestDTO;
import com.DataProvider.DataProvider.DTO.loginReSPONSEDTO;
import com.DataProvider.DataProvider.DTO.loginRequestDTO;
import com.DataProvider.DataProvider.Entity.Department;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Entity.Role;
import com.DataProvider.DataProvider.Repository.DepartmentRepository;
import com.DataProvider.DataProvider.Repository.EmployeeRepository;
import com.DataProvider.DataProvider.Repository.RoleRepository;
import com.DataProvider.DataProvider.Service.AuthService;
import com.DataProvider.DataProvider.config.jwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired

    private EmployeeRepository employeeRepository;
    @Autowired

    private jwtService jwtService;
    @Autowired

    private DepartmentRepository departmentRepository;
    @Autowired

    private RoleRepository roleRepository;
    @Override
    public loginReSPONSEDTO login(loginRequestDTO dto) {
        loginReSPONSEDTO loginReSPONSEDTO=new loginReSPONSEDTO();
        try{

         Employee e= employeeRepository.findAllByEmailAddress(dto.getUsername());
         boolean passMatch = passwordEncoder.matches(dto.getPassword(), e.getPassword());
        if(Objects.nonNull(e) && passMatch)
        {
            String token=   generateToken(e);
            loginReSPONSEDTO.setToken(token);
            return loginReSPONSEDTO;
        }
           return loginReSPONSEDTO;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public String generateToken(Employee e)
    {
        String token=   jwtService.generateToken(e);
        return token;
    }

    @Override
    public loginReSPONSEDTO register(RegisterRequestDTO dto) {
        try{
            loginReSPONSEDTO loginReSPONSEDTO = new loginReSPONSEDTO();

            Department department=new Department();
            department.setId(dto.getDepartment());
            if(dto.getRoles().size() > 0) {
                List<Role> roleList = new ArrayList<>();
                dto.getRoles().forEach(r -> {

                    Role role = new Role();
                    role.setId(r);
                    roleList.add(role);
                });
                String password = passwordEncoder.encode(dto.getPassword());
                Employee employee = new Employee();
                employee.setRoles(roleList);
                employee.setId(dto.getId());
                employee.setName(dto.getName());
                employee.setEnabled(true);
                employee.setDepartment(department);
                employee.setSalary(dto.getSalary());
                employee.setPassword(password);
                employee.setEmailAddress(dto.getEmailAddress());

                employee =  employeeRepository.save(employee);
                if(Objects.nonNull(employee))
                {
                    String token=   generateToken(employee);
                    loginReSPONSEDTO.setToken(token);
                    return loginReSPONSEDTO;
                }

            }
            return  null;
        }
       catch (Exception e)
       {
           System.out.println(e);
       }
        return  null;
    }

}
