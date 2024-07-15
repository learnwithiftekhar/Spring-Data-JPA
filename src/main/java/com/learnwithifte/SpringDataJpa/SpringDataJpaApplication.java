package com.learnwithifte.SpringDataJpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			EmployeeRepository employeeRepository,
			AddressRepository addressRepository) {
		return args->{
			Faker faker = new Faker();

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

			employee.setTasks( new Task("Record A video"));
			employee.setTasks(new Task("do some coding"));
			employee.setTasks(new Task("Drink Coffee"));

			employeeRepository.save(employee);



//
//			Address address = new Address(
//					faker.address().streetAddress(),
//					faker.address().city(),
//					faker.address().state(),
//					faker.address().zipCode(),
//					employee
//			);
//
//			addressRepository.save(address);

//			addressRepository.findAll()
//					.forEach(System.out::println);

//			employeeRepository.findById(206L)
//							.ifPresent(System.out::println);
//			addressRepository.findById(1L)
//					.ifPresent(System.out::println);
//
//			employeeRepository.deleteById(206L);

		};
	}

	private static void pagination(EmployeeRepository employeeRepository) {
		PageRequest pageReq = PageRequest.of(
				0,
				10,
				Sort.by("id").ascending());
		employeeRepository.findAll(pageReq)
				.forEach(employee -> {
					System.out.println(employee);
				});
	}

	private static void sorting(EmployeeRepository employeeRepository) {
		Sort sort = Sort.by("firstName")
				.ascending()
				.and(Sort.by("salary").descending());

		employeeRepository.findAll(sort)
				.forEach(employee -> {
					System.out.println(employee.getFirstName()+" "+employee.getSalary());
				});
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
