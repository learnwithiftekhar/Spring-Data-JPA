package com.learnwithifte.SpringDataJpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final EmployeeRepository employeeRepository;

    public HomeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/employees")
    public Page<Employee> employees(@RequestParam(name = "page", defaultValue = "0") String page) {

        Integer pageNumber = Integer.parseInt(page);


        PageRequest pageReq = PageRequest.of(
                pageNumber,
                10,
                Sort.by("id").ascending());
        return employeeRepository.findAll(pageReq);
    }
}
