package com.vishal.springbootwebtutorial.springbootwebtutorial.Services;


import com.vishal.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Repository.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;


import java.util.*;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmpService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmpService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {

       // Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
      //  return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1 , EmployeeDTO.class));
    return employeeRepository.findById(id).map(employeeEntity -> modelMapper
            .map(employeeEntity,EmployeeDTO.class));
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

    public EmployeeDTO updateEmployee(Long employeeID, EmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeID);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);

    }

    public boolean DeleteEmployee(Long employeeID) {
       boolean exists = employeeRepository.existsById(employeeID);
       if(!exists) return false;
          employeeRepository.deleteById(employeeID);
        return true;
    }

    public EmployeeDTO updatePartialEmployee(Long employeeID, Map<String, Object> updates) {
        boolean exists = employeeRepository.existsById(employeeID);

        if(!exists) return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).get();
        updates.forEach((field,value) -> {
           Field fieldToBeUpdated =  ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);  //because entity class fields are private.need access to it first
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

    }
}
