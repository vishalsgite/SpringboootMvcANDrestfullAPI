package com.vishal.springbootwebtutorial.springbootwebtutorial.Services;


import com.vishal.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmpService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {

        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO save(EmployeeEntity employeeEntity) {
        EmployeeEntity employeeEntity1 = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity1,EmployeeDTO.class);
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity>  employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
