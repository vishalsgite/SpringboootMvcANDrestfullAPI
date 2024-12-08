package com.vishal.springbootwebtutorial.springbootwebtutorial.Repository;

import com.vishal.springbootwebtutorial.springbootwebtutorial.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity ,Long> {

}
