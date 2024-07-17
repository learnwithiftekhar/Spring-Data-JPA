package com.learnwithifte.SpringDataJpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Employee")
@Table(
        name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "employee_email_unique",
                        columnNames = "email"
                )
        }
)
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 100
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 100
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "hire_date",
            nullable = false
    )
    private LocalDate hireDate;

    @Column(
            name = "salary",
            nullable = false
    )
    private double salary;

    @OneToOne(
            mappedBy = "employee",
            orphanRemoval = true
    )
    private Address address;

    @OneToMany(
            mappedBy = "employee",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(
                    name = "employee_id",
                    foreignKey = @ForeignKey(name = "employee_project_employee_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "project_id",
                    foreignKey = @ForeignKey(name = "employee_project_project_id_fk")
            )
    )
    private Set<Project> projectList = new HashSet<>();

    public Employee(String firstName, String lastName, String email, LocalDate hireDate, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setTasks(Task task) {
            this.tasks.add(task);
            task.setEmployee(this);
    }

    public void removeTask(Task task) {
            this.tasks.remove(task);
            task.setEmployee(null);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void assignProject(Project project) {
        projectList.add(project);
        project.getEmployees().add(this);
    }

    public void removeProject(Project project) {
        projectList.remove(project);
        project.getEmployees().remove(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                '}';
    }
}
