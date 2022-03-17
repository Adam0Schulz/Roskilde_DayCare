package com.example.roskilde_daycare;

import java.sql.Date;

public class Attendee extends Child {

    private String group;

    public Attendee(String firstName, String lastName,  Date dateOfBirth, String gender, Parent parent, String group, String CPR) {
        super(firstName, lastName, dateOfBirth, gender, parent, CPR);
        this.group = group;
    }
}