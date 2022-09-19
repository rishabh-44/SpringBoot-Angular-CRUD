package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.exception.ResourceNotFoundException;
import com.example.SpringBootCRUD.model.Employee;
import com.example.SpringBootCRUD.repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        employee.setCity(employeeDetails.getCity());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employeeRepository.delete(employee);
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
