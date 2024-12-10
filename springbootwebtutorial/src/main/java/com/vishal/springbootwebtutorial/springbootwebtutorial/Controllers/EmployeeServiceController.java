package com.vishal.springbootwebtutorial.springbootwebtutorial.Controllers;

import com.vishal.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Services.EmpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/EmployeeServiceController")
public class EmployeeServiceController {

    private final EmpService empService;


    public EmployeeServiceController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping(path = "/{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeID") Long id){
        return empService.getEmployeeById(id);
    }

    @PostMapping(path = "/CreateNewEmployee")
    public EmployeeDTO CreateNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return empService.save(employeeEntity);

    }

    @GetMapping(path = "/getAllEmployee")
    public List<EmployeeDTO> getAllEmployee(){
        return empService.findAll();
    }
}
