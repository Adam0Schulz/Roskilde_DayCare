package com.example.roskilde_daycare;

import java.util.ArrayList;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public String getCPR() {
        return CPR;
    }

    public String getPassword() {
        return password;
    }

    private String password;

    public Employee(String firstName, String lastName, String email, String address, int zip, String city, String phoneNumber, float salary, String CPR, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.CPR = CPR;
        this.password = password;
    }

    public ArrayList<String> getAllAttributeArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Name:\n" + this.getFirstName() + " " + this.getLastName());
        array.add("Email:\n" + this.getEmail());
        array.add("Phone:\n" + this.getPhoneNumber());
        array.add("Address:\n" + this.getAddress());
        array.add("Zip, City:\n" + this.getZip() + ", " + this.getCity());
        array.add("Salary:\n" + this.getSalary() + "");
        array.add("CPR:\n" + this.getCPR());
        return array;
    }

    public ArrayList<String> getAttributeArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getFirstName());
        array.add(this.getLastName());
        array.add(this.getPhoneNumber());
        array.add(this.getEmail());
        array.add(this.getSalary() + "");
        return array;
    }
}
