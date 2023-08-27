package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.BaseDTO;
import com.DataProvider.DataProvider.DTO.EmployeeRequestDTO;
import com.DataProvider.DataProvider.DTO.EmployeeResponseDTO;
import com.DataProvider.DataProvider.Entity.Department;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Entity.Role;
import com.DataProvider.DataProvider.Repository.DepartmentRepository;
import com.DataProvider.DataProvider.Repository.EmployeeRepository;
import com.DataProvider.DataProvider.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    List<Employee> employeeList= new ArrayList<>();
    @Override
    public List<EmployeeResponseDTO> getAll() {
        List<EmployeeResponseDTO> list=new ArrayList<>();
        List<Employee> emp=employeeRepository.getEmployeesByEnabledTrue();
        emp.stream().forEach(e->{
            EmployeeResponseDTO employeeResponseDTO=new EmployeeResponseDTO(e);
            list.add(employeeResponseDTO);
        });

        return list;
    }

    @Override
    public BaseDTO add(EmployeeRequestDTO dto) {
        try{
            BaseDTO baseDTO=new BaseDTO();

                Employee employee=new Employee();

                if(Objects.nonNull(dto.getId()) && dto.getId()!=0)
                {
                    employee.setId(dto.getId());
                }
                List<Role> roleList=new ArrayList<>();
                if(dto.getRoles().size()>0)
                {
                    dto.getRoles().stream().forEach(
                            r->{
                                Role role=new Role();
                                role.setId(r);
                                roleList.add(role);
                            }
                    );
                }


                employee.setRoles(roleList);
                employee.setName(dto.getName());
                employee.setEnabled(dto.isEnabled());
                employee.setSalary(dto.getSalary());
                Department dept=new Department();
                dept.setId(dto.getDepartment());
                employee.setDepartment(dept);
             Employee employee1=   employeeRepository.save(employee);
                baseDTO.responseMessage="Employee Saved Sucessfully";
                return baseDTO;

        }
        catch (Exception e){
            throw e;
        }


    }

    @Override
    public BaseDTO delete(Integer id) {
        BaseDTO baseDTO=new BaseDTO();
      Optional<Employee> employeeOptional=  employeeRepository.findById(id);
      if(employeeOptional.isPresent())
      {
          Employee emp=  employeeOptional.get();
          employeeRepository.delete(emp);
          baseDTO.responseMessage ="Employee Deleted Sucessfully";
          return baseDTO;
      }
        baseDTO.responseMessage ="Employee Not Found";
        return baseDTO;

    }


}
