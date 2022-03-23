package com.example.roskilde_daycare;

import java.util.ArrayList;

public class Parent {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int zip;
    private String city;
    private String phoneNumber;
    private int ID;

    public Parent (String firstName, String lastName, String email, String address, int zip, String city, String phoneNumber, int ID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.ID = ID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getAttributeArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Name:\n" + this.getFirstName() + " " + this.getLastName());
        array.add("Email:\n" + this.getEmail());
        array.add("Phone:\n" + this.getPhoneNumber());
        array.add("Address:\n" + this.getAddress());
        array.add("ZIP, City:\n" + this.getZip() + ", " + this.getCity());
        return array;
    }

    @Override
    public String toString() {
        return getFirstName() + getLastName() + getEmail() + getPhoneNumber() + getAddress() + getZip() + getCity();
    }
}
