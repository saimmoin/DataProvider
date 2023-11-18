package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.BaseDTO;
import com.DataProvider.DataProvider.DTO.userRequestDTO;
import org.springframework.stereotype.Service;


@Service
public interface userService {

    BaseDTO addStudent (userRequestDTO user) ;

    BaseDTO addTeacher (userRequestDTO user) ;

    BaseDTO addParent (userRequestDTO user) ;


}
