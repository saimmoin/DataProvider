package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.RegisterRequestDTO;
import com.DataProvider.DataProvider.DTO.ResponseDTO;
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
import org.springframework.http.HttpStatus;
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
    public ResponseDTO login(loginRequestDTO dto) {
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            if(Objects.isNull(dto.getUsername()) && Objects.isNull(dto.getPassword()))
            {
                responseDTO.setMessage("UserName or Password Not Found!");
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                return responseDTO;
            }

         Employee e= employeeRepository.findAllByEmailAddress(dto.getUsername());
         boolean passMatch = passwordEncoder.matches(dto.getPassword(), e.getPassword());
        if(Objects.nonNull(e) && passMatch)
        {
            loginReSPONSEDTO loginReSPONSEDTO =new loginReSPONSEDTO();
         String token=   generateToken(e);
            loginReSPONSEDTO.setToken(token);

           responseDTO.setBody(loginReSPONSEDTO);
           responseDTO.setMessage("Login Sucessfully");
           responseDTO.setStatusCode(HttpStatus.OK);
            return responseDTO;
        }
            responseDTO.setMessage("Login Failed!");
            responseDTO.setStatusCode(HttpStatus.CONFLICT);
           return responseDTO;
        }
        catch(Exception e)
        {
            System.out.println(e);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseDTO;

        }

    }

    public String generateToken(Employee e)
    {
        String token=   jwtService.generateToken(e);

        return token;
    }

    @Override
    public ResponseDTO register(RegisterRequestDTO dto) {
        try{
            ResponseDTO responseDTO = new ResponseDTO();

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
                    loginReSPONSEDTO loginReSPONSEDTO = new loginReSPONSEDTO();
                    String token=   generateToken(employee);
                    loginReSPONSEDTO.setToken(token);
                    responseDTO.setBody(loginReSPONSEDTO);
                    responseDTO.setMessage("Registered Successfully!");
                    responseDTO.setStatusCode(HttpStatus.OK);
                    return responseDTO;
                }
                responseDTO.setMessage("Employee Not Saved Successfully!");
                responseDTO.setStatusCode(HttpStatus.CONFLICT);
                return responseDTO;

            }
            responseDTO.setMessage("Roles Not found!");
            responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
            return responseDTO;
        }
       catch (Exception e)
       {
           ResponseDTO responseDTO = new ResponseDTO();
           System.out.println(e);
           responseDTO.setMessage("Registration Failed!"+e.getMessage());
           responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
           return responseDTO;
       }

    }

}
