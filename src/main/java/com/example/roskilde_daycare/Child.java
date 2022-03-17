package com.example.roskilde_daycare;

import java.sql.Date;

public abstract class Child {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Parent parent;
    private String CPR;

    public Child (String firstName, String lastName, Date dateOfBirth, String gender, Parent parent, String CPR) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.parent = parent;
        this.CPR = CPR;
    }

}