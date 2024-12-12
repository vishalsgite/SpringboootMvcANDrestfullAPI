package com.vishal.springbootwebtutorial.springbootwebtutorial.Controllers;

import com.vishal.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Exception.ResourceNotFoundException;
import com.vishal.springbootwebtutorial.springbootwebtutorial.Services.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/EmployeeServiceController")
public class EmployeeServiceController {

    private final EmpService empService;


    public EmployeeServiceController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeID") Long id){
        Optional<EmployeeDTO> employeeDTO =  empService.getEmployeeById(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found "));
    }

    @PostMapping(path = "/CreateNewEmployee")
    public ResponseEntity<EmployeeDTO> CreateNewEmployee(@RequestBody @Valid EmployeeEntity employeeEntity){
      //  return empService.save(employeeEntity);
        EmployeeDTO savedEmployee = empService.save(employeeEntity);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);


    }

    @GetMapping(path = "/getAllEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
       // return empService.findAll();
        return ResponseEntity.ok(empService.findAll());
    }

    @PutMapping(path = "/UpdateEmployee/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long employeeID){
        return ResponseEntity.ok(empService.updateEmployee(employeeID, employeeDTO));

    }

    @DeleteMapping(path = "/DeleteEmployee/{EmployeeID}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long EmployeeID){
        //  return empService.DeleteEmployee(EmployeeID);
        boolean gotDeleted = empService.DeleteEmployee(EmployeeID);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

     @PatchMapping(path = "/UpdatePartialEmployee/{EmployeeID}")
        public ResponseEntity<EmployeeDTO> updatePartialEmployee(@PathVariable Long EmployeeID,
                                                 @RequestBody Map<String, Object> updates){
          //  return empService.updatePartialEmployee(EmployeeID,updates);
         EmployeeDTO employeeDTO = empService.updatePartialEmployee(EmployeeID,updates);
         if (employeeDTO == null ) return ResponseEntity.notFound().build();
         return ResponseEntity.ok(employeeDTO);
        }

    }

