package com.learnwithifte.SpringDataJpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
		return args->{
			Sort sort = Sort.by("firstName")
					.ascending()
					.and(Sort.by("salary").descending());

			employeeRepository.findAll(sort)
					.forEach(employee -> {
						System.out.println(employee.getFirstName()+"-"+employee.getSalary());
					});
		};
	}

	private static void generateEmployee(EmployeeRepository employeeRepository) {
		Faker faker = new Faker();

		for(int i = 0; i <100; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			Employee employee = new Employee(
					firstName,
					lastName,
					firstName.toLowerCase()+"_"+lastName.toLowerCase()+"@learnwithiftekhar.com",
					LocalDate.of(
							faker.number().numberBetween(2018, 2024),
							faker.number().numberBetween(1, 12),
							faker.number().numberBetween(1, 28)),
					faker.number().randomDouble(2, 1000, 5000)
			);

			employeeRepository.save(employee);
		}
	}
}
