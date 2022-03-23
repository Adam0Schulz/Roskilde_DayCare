package com.example.roskilde_daycare;


import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;

public class Queuer extends Child{

    private Timestamp time;

    public Queuer(String firstName, String lastName, Date dateOfBirth, String gender, Parent parent, Timestamp time, String CPR){
        super(firstName, lastName, dateOfBirth, gender, parent, CPR);
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public ArrayList<String> getAttributeArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getFirstName());
        array.add(this.getLastName());
        array.add(this.getTime().toString());
        array.add(this.getDateOfBirth().toString());
        array.add(this.getCPR());
        return array;
    }

    public ArrayList<String> getAllArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getFirstName());
        array.add(this.getLastName());
        array.add(this.getDateOfBirth().toString());
        array.add(this.getGender());
        array.add(this.getParent().getFirstName());
        array.add(this.getParent().getLastName());
        array.add(this.getParent().getZip() + "");
        array.add(this.getParent().getAddress());
        array.add(this.getParent().getCity());
        array.add(this.getParent().getPhoneNumber());
        array.add(this.getParent().getEmail());
        array.add(this.getTime() + "");
        array.add(this.getCPR());
        return array;
    }

    @Override
    public String toString() {
        return getFirstName() + getLastName() + getTime() + getCPR() + getGender() + getDateOfBirth() + getParent();
    }
}
