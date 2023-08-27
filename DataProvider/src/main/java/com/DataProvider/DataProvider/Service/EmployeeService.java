package com.DataProvider.DataProvider.Service;

import com.DataProvider.DataProvider.DTO.BaseDTO;
import com.DataProvider.DataProvider.DTO.EmployeeRequestDTO;
import com.DataProvider.DataProvider.DTO.EmployeeResponseDTO;
import com.DataProvider.DataProvider.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAll();
    BaseDTO add(EmployeeRequestDTO dto);
    BaseDTO delete(Integer id);
}
