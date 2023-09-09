package com.DataProvider.DataProvider.Controller;

import com.DataProvider.DataProvider.DTO.BaseDTO;
import com.DataProvider.DataProvider.DTO.EmployeeRequestDTO;
import com.DataProvider.DataProvider.DTO.EmployeeResponseDTO;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("Employee")

public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @GetMapping("getEmployee")
    public List<EmployeeResponseDTO> getall(){
        List<EmployeeResponseDTO> list = new ArrayList<>();
        list= employeeService.getAll();
        System.out.println("Employee DATA--->"+list.toString());

        return list;
    }

    @PostMapping("AddEmployee")
    public BaseDTO AddEmployee(@RequestBody EmployeeRequestDTO dto){

        return employeeService.add(dto);
    }
    @DeleteMapping("/{id}")
    public BaseDTO delete(@PathVariable Integer id){
        return employeeService.delete(id);
    }
}
