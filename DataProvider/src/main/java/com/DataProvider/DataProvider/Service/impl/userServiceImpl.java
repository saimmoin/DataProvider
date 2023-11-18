package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.BaseDTO;
import com.DataProvider.DataProvider.DTO.loginReSPONSEDTO;
import com.DataProvider.DataProvider.DTO.userRequestDTO;
import com.DataProvider.DataProvider.Entity.*;
import com.DataProvider.DataProvider.Repository.*;
import com.DataProvider.DataProvider.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class userServiceImpl implements userService {

    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userReqository;
    @Autowired
    private SchoolClassRepository schoolClassRepository;
    @Autowired
    private FeesRepository feesRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;
    @Override
    public BaseDTO addStudent(userRequestDTO userRequestDTO) {
        try{
            User user = new User();
            user.setId(userRequestDTO.getId());
            user.setName(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            List<Role> roleList = new ArrayList<>();
            userRequestDTO.getUserRole().stream().forEach(
                    r -> {

                        Optional<Role> roleOptional = roleRepository.findById(r);
                        Role role = roleOptional.get();
                        roleList.add(role);

                    }
            );
            user.setUserRole((Set<Role>) roleList);
            user.setEnabled(true);
            String password = passwordEncoder.encode(userRequestDTO.getPassword());
            user.setPassword(password);
            user.setAddress(userRequestDTO.getAddress());
            user.setDateJoined(userRequestDTO.getDateJoined());
            user.setDeleted(false);
            user.setDesignation(userRequestDTO.getDesignation());
            user.setGender(userRequestDTO.getGender());
           Optional<User> parentOptional =  userReqository.findById(userRequestDTO.getParentId());
            User parent =parentOptional.get();
            user.setParent(false);
            user.setParentId(parent);
            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
            user.setStatus(true);
            user.setRollnumber(userRequestDTO.getRollnumber());
            Optional<schoolClass> sc = schoolClassRepository.findById(userRequestDTO.getStudentClass());
            schoolClass sc2 = sc.get();
            user.setStudentClass(sc2);
            List<Fees> feesList = new ArrayList<>();
            userRequestDTO.getStudentFees().stream().forEach(
                    f -> {
                        Optional<Fees> fc = feesRepository.findById(f);
                        Fees fc2 = fc.get();
                        feesList.add(fc2);
                    }
            );
            user.setStudentFees((Set<Fees>) feesList);
            user.setTeacherSubject(null);

            Optional<UserType> userTypeOptional = userTypeRepository.findById(userRequestDTO.getUserTypeId());
            UserType userType = userTypeOptional.get();
            user.setUserTypeId(userType);
            userReqository.save(user);

//            loginReSPONSEDTO loginReSPONSEDTO = new loginReSPONSEDTO();
//            String token=  AuthService.generateToken(user);
//            loginReSPONSEDTO.setToken(token);
//            responseDTO.setBody(loginReSPONSEDTO);
//            responseDTO.setMessage("Registered Successfully!");
//            responseDTO.setStatusCode(HttpStatus.OK);
//            return responseDTO;
            return null;
//
        }
        catch(Exception e ) {
//            System.out.println(e);
//            BaseDTO baseDTO = new BaseDTO();
//            baseDTO.responseMessage()=e.getMessage();
//            return baseDTO
            return null;

        }
    }

    @Override
    public BaseDTO addTeacher(userRequestDTO user) {
        return null;
    }

    @Override
    public BaseDTO addParent(userRequestDTO user) {
        return null;
    }
}
