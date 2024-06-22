package com.learnwithifte.SpringDataJpa;

import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "Select e from Employee e where e.email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);


    @Query(value = "select e from Employee e where upper(e.lastName) = upper(:lastname ) ")
    List<Employee> findEmployeeByLastNameIgnoreCase( @Param("lastname") String lastName);

    @Query(value = "select e from Employee e where upper(e.firstName) = upper(?1) and upper(e.lastName) = upper(?2) ")
    List<Employee> findEmployeeByFirstNameIgnoreCaseAndLastNameIgnoreCase(
            String firstName,
            String lastName);

    @Query(
            value = "select * from employee where upper(last_name) = upper(:lastName) and salary > :salary",
            nativeQuery = true
    )
    List<Employee> findEmployeeByLastNameEqualsIgnoreCaseAndSalaryGreaterThan(
            @Param("lastName") String lastName,  @Param("salary") double salary);

    @Transactional
    @Modifying
    @Query(value = "delete from Employee e where e.email = :email")
    void deleteEmployeeByEmail(@Param("email") String email);
}
