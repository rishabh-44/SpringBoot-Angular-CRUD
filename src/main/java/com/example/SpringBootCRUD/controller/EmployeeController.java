package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.model.Employee;
import com.example.SpringBootCRUD.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

   @GetMapping("/employee")
    public List<Employee> getEmployees()
   {
       List<Employee> employeeList = employeeService.getEmployees();
       return employeeList;
   }
   @GetMapping("/employee/{city}")
    public ResponseEntity getEmployeesByCity(@PathVariable String city)
   {
        return ResponseEntity.ok(employeeService.getEmployeesByCity(city));
   }
//   @GetMapping("/employee/{age}")
//    public ResponseEntity getEmployeesByAge(@PathVariable String age)
//   {
//        return ResponseEntity.ok(employeeService.getAllByAge(age));
//   }
   @PostMapping("/addEmployee")
    public String saveEmployee(@RequestBody Employee employee)
   {
       return employeeService.saveEmployee(employee);
   }
   @PutMapping("/putEmployee")
    public String putEmployee(@RequestBody Employee employee){
        return employeeService.putEmployee(employee);
   }
   @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(@RequestBody Employee employee){
        return employeeService.deleteEmployee(employee);
   }
}
