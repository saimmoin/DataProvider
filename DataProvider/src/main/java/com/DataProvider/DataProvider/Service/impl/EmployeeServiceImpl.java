package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.BaseDTO;
import com.DataProvider.DataProvider.DTO.EmployeeRequestDTO;
import com.DataProvider.DataProvider.DTO.EmployeeResponseDTO;
import com.DataProvider.DataProvider.Entity.Department;
import com.DataProvider.DataProvider.Entity.Employee;
import com.DataProvider.DataProvider.Entity.Role;
import com.DataProvider.DataProvider.Repository.DepartmentRepository;
import com.DataProvider.DataProvider.Repository.EmployeeRepository;
import com.DataProvider.DataProvider.Repository.RoleRepository;
import com.DataProvider.DataProvider.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RedisTemplate <Integer,Employee> redisTemplate;
    @Autowired
    RoleRepository roleRepository;
    private static Integer Key=7246;
    List<Employee> employeeList= new ArrayList<>();
    @Override
    public List<EmployeeResponseDTO> getAll() {
        try {
            List<EmployeeResponseDTO> list=new ArrayList<>();

          List<Object> emp =  redisTemplate.opsForHash().values(Key);
          List<Object>emp1= (List<Object>) emp.get(0);

            emp1.stream().forEach(e->{
                EmployeeResponseDTO employeeResponseDTO=new EmployeeResponseDTO((Employee) e);
                list.add(employeeResponseDTO);
            });
            return list;
        } catch(Exception err) {
            System.out.println(err);
            return null;
        }
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
            List<Role> roleList =  roleRepository.findAllByIdIn(dto.getRoles());
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
            Optional<Department>   optionalDepartment=  departmentRepository.findById(dto.getDepartment());
           dept =  optionalDepartment.get();

                employee.setDepartment(dept);
            employee=   employeeRepository.save(employee);
                baseDTO.responseMessage="Employee Saved Sucessfully";
//                redisTemplate.opsForValue().set(Key, employee.getId(), employee);
                redisTemplate.opsForHash().put(Key, employee.getId(), employee);
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
