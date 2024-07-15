package com.learnwithifte.SpringDataJpa;

import jakarta.persistence.*;



@Entity(name = "Task")
@Table(name = "task")
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "employee_taks_fk"
            )
    )
    private Employee employee;

    public Task(String name, Employee employee) {
        this.name = name;
        this.employee = employee;
    }

    public Task(String name) {
        this.name = name;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employee=" + employee +
                '}';
    }
}
