package com.vishal.springbootwebtutorial.springbootwebtutorial.Controllers;


import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/ParentPathEMPLOYEES")
public class EmployeeController {


    @GetMapping(path = "/myMessage")
    public String myMessage(){
        return "Hello , this is my mapping request for GET";
    }

    @GetMapping(path = "/getEmp/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
        return new EmployeeDTO(employeeID ,
                "VISHAL","VSHL@GMAIL.COM",26,
                LocalDate.of(2024,8,5),true);
    }

    @GetMapping(path = "/getEmpByAddingNameParaInAbove/{employeeID}")
    public EmployeeDTO getEmployeeByIDWithName(@PathVariable(name = "employeeID") Long ID){
        return new EmployeeDTO(ID ,
                "VISHAL","VSHL@GMAIL.COM",26,
                LocalDate.of(2024,8,5),true);
    }

   // @GetMapping(path = "/employee")
    @GetMapping
    public String getAllemployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi filter by age is : "+age+" "+sortBy;


    }

    @GetMapping(path = "/getAllemployeesByMyAgePara")
    public String getAllemployeesByMyAgePara(@RequestParam(required = false,name = "MyAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi filter by age is : "+age+" "+sortBy;


    }
}
