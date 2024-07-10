package com.learnwithifte.SpringDataJpa;

import jakarta.persistence.*;

@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "street",
            nullable = false
    )
    private String street;

    @Column(
            name = "city",
            nullable = false
    )
    private String city;


    @Column(
            name = "state",
            nullable = false
    )
    private String state;

    @Column(
            name = "zip_code",
            nullable = false
    )
    private String zipCode;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            columnDefinition = "employee_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "employee_id_fk"
            )
    )
    private Employee employee;

    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public Address(String street, String city, String state, String zipCode, Employee employee) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", employee=" + employee +
                '}';
    }
}
