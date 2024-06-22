package com.learnwithifte.SpringDataJpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
//			Employee jhon = new Employee(
//					"Jhon",
//					"Doe",
//					"john@learnwithiftekhar.com",
//					LocalDate.of(2022, 1, 1),
//					2000.00
//					);
//
//			employeeRepository.save(jhon);

			Employee jane = new Employee(
					"Jane",
					"Doe",
					"jane@learnwithiftekhar.com",
					LocalDate.of(2023, 1, 1),
					2200.00
					);

			Employee ana = new Employee(
					"Ana",
					"Doe",
					"ana@learnwithiftekhar.com",
					LocalDate.of(2024, 1, 1),
					1900.00
			);



			Employee kabir = new Employee(
					"Kabir",
					"Hossain",
					"kabir@learnwithiftekhar.com",
					LocalDate.of(2024, 1, 1),
					1900.00
			);
//			employeeRepository.save(kabir);
//			employeeRepository.saveAll(List.of(jane, ana));

			System.out.println("No employee count is:" + employeeRepository.count());

			// find employee by id
			employeeRepository.findById(1L)
					.ifPresentOrElse((System.out::println), ()->{
						System.out.println("No employee with id 1 is found");
					});

			employeeRepository.deleteById(1L);

			System.out.println("No employee count after deleting is:" + employeeRepository.count());

			employeeRepository.findById(1L)
					.ifPresentOrElse((System.out::println), ()->{
						System.out.println("No employee with id 1 is found");
					});


			employeeRepository.findAll().forEach(System.out::println);



			// find by email:
			System.out.println("Find by email: jane@learnwithiftekhar.com");
			employeeRepository.findEmployeeByEmail("jane@learnwithiftekhar.com")
					.ifPresentOrElse((System.out::println), ()->{
						System.out.println("No employee found with this email: jane@learnwithiftekhar.com");
					});


			System.out.print("Find employee by lastname");
			employeeRepository.findEmployeeByLastNameIgnoreCase("hossain").forEach(System.out::println);

			System.out.println("Find employee by firstname and lastname");
			employeeRepository.findEmployeeByFirstNameIgnoreCaseAndLastNameIgnoreCase("ana", "doe")
					.forEach(System.out::println);

			System.out.println("Find employee by lastname and salary greater 2000");
			employeeRepository.findEmployeeByLastNameEqualsIgnoreCaseAndSalaryGreaterThan("doe", 2000)
					.forEach(System.out::println);


			// delete the employee by email
			System.out.println("Deleting employee by email: jane@learnwithiftekhar.com");
			System.out.println("No employee count before deleting is:" + employeeRepository.count());

			employeeRepository.deleteEmployeeByEmail("jane@learnwithiftekhar.com");

			System.out.println("No employee count after deleting is:" + employeeRepository.count());

		};
	}
}
