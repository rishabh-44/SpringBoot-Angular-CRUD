package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.exception.ResourceNotFoundException;
import com.example.SpringBootCRUD.model.Employee;
import com.example.SpringBootCRUD.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }


    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }


    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeService.getEmployeeById(id);

        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        employee.setCity(employeeDetails.getCity());

        Employee updatedEmployee = employeeService.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);

        employeeService.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }










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
