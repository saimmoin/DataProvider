package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.*;
import com.DataProvider.DataProvider.Entity.Department;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Entity.RefreshToken;
import com.DataProvider.DataProvider.Entity.Role;
import com.DataProvider.DataProvider.Repository.EmployeeRepository;
import com.DataProvider.DataProvider.Repository.RefreshTokenRepo;
import com.DataProvider.DataProvider.Service.AuthService;
import com.DataProvider.DataProvider.config.jwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired

    private EmployeeRepository employeeRepository;
    @Autowired

    private jwtService jwtService;
    @Autowired
    RefreshTokenRepo refreshTokenRepo;





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
            //generating refresh token
          RefreshToken rt=  refreshTokenRepo.findByUser(e);
          if(Objects.nonNull(rt) )

          {
              ResponseDTO res =new ResponseDTO();
              if( Objects.nonNull(validateRefreshToken(rt.getToken())))
              {
                  RefreshToken t= validateRefreshToken(rt.getToken());
                   res= updateRefreshTokenExpiry(t);
              }
              else {
                  res = this.createToken(e);

              }
              if(res.getStatusCode().equals(HttpStatus.OK))
              {
                  RefreshToken refreshToken= (RefreshToken) res.getBody();
                  loginReSPONSEDTO.setRefreshToken(refreshToken.getToken());
              }

          }

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

    private ResponseDTO updateRefreshTokenExpiry(RefreshToken refreshToken) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, 6);
        Date updatedDate = calendar.getTime();
        refreshToken.setTokenExpiry(updatedDate);
        refreshToken.setStatus(true);
        refreshToken=  refreshTokenRepo.save(refreshToken);
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK);
        responseDTO.setBody(refreshToken);
        return  responseDTO;

    }

    @Override
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


    @Override
    public ResponseDTO createToken(Employee employee) {
        try{
            ResponseDTO responseDTO = new ResponseDTO();
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setUser(employee);
            Date currentDate = new Date();

            // Create a Calendar instance and set it to the current date and time
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            // Add 6 minutes to the current time
            calendar.add(Calendar.MINUTE, 6);

            // Get the updated date and time
            Date updatedDate = calendar.getTime();
            refreshToken.setTokenExpiry(updatedDate);
            refreshToken.setStatus(true);
            refreshToken=  refreshTokenRepo.save(refreshToken);
            responseDTO.setMessage("Refresh Token generated successfully!");
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setBody(refreshToken);
            return responseDTO;

        }
        catch(Exception e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseDTO;
        }
    }

    @Override
    public RefreshToken validateRefreshToken(String token) {
        try{
            RefreshToken refreshToken = refreshTokenRepo.findByToken(token);
            if(Objects.nonNull(refreshToken)){
                if(  refreshToken.getTokenExpiry().after(new Date())){

                    return refreshToken;
                }
                refreshTokenRepo.delete(refreshToken);
                return null;
            }
            return null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ResponseDTO generateRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        RefreshToken refreshToken = validateRefreshToken(refreshTokenRequestDTO.getRefreshToken());
        if(Objects.nonNull(refreshToken)) {
            Employee employee=    employeeRepository.findAllByEmailAddress(refreshTokenRequestDTO.getUsername());
            refreshToken.setTokenExpiry(new Date(System.currentTimeMillis() *300000));
            refreshToken.setStatus(true);
            refreshToken=  refreshTokenRepo.save(refreshToken);
            String token= this.generateToken(employee);
            loginReSPONSEDTO loginReSPONSEDTO =new loginReSPONSEDTO();
            loginReSPONSEDTO.setRefreshToken(refreshToken.getToken());
            loginReSPONSEDTO.setToken(token);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setBody(loginReSPONSEDTO);
            responseDTO.setMessage("Token Refreshed Successfully!");
            responseDTO.setStatusCode(HttpStatus.OK);
            return responseDTO;

        }
        return null;
    }


}
