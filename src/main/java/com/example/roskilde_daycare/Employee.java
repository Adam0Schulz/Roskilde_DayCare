package com.example.roskilde_daycare;

public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int zip;
    private String city;
    private String phoneNumber;
    private float salary;
    private String CPR;

    public Employee(String firstName, String lastName, String email, String address, int zip, String city, String phoneNumber, float salary, String CPR) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.CPR = CPR;
    }
}
