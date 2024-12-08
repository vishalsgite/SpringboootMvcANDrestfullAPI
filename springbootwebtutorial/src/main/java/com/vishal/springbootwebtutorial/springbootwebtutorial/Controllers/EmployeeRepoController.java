package com.vishal.springbootwebtutorial.springbootwebtutorial.Controllers;

import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/EmployeeRepoController")
public class EmployeeRepoController {

    private final EmployeeRepository employeeRepository;

    public EmployeeRepoController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping(path = "/createNewEmployee")
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    @GetMapping(path = "/getEmployeeByID/{EmployeeID}")
    public EmployeeEntity getEmployeeByID(@PathVariable Long EmployeeID){
           return employeeRepository.findById(EmployeeID).orElse(null);
    }

    @GetMapping(path = "/getAllEmployee")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }
}
