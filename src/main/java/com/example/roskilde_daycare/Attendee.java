package com.example.roskilde_daycare;

import java.sql.Date;
import java.util.ArrayList;

public class Attendee extends Child {

    private String group;

    public Attendee(String firstName, String lastName,  Date dateOfBirth, String gender, Parent parent, String group, String CPR) {
        super(firstName, lastName, dateOfBirth, gender, parent, CPR);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<String> getAttributeArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getFirstName());
        array.add(this.getLastName());
        array.add(this.getGroup());
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
        array.add(this.getGroup());
        array.add(this.getCPR());
        return array;
    }
}