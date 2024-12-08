package com.vishal.springbootwebtutorial.springbootwebtutorial.Controllers;


import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
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
}
