package com.example.SpringBootCRUD.repository;


import com.example.SpringBootCRUD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    We can write custom query as
//    @Query("Select e from Employee e where e.age > :age",nativeQuery = true)

//    @Query("Select e from Employee e where e.age > :age")
//    List<Employee> getAllByAge(String age);

    List<Employee> findAllByCity(String city);

}
