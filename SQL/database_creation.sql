DROP DATABASE IF EXISTS Daycare;

CREATE DATABASE IF NOT EXISTS Daycare;

USE Daycare;

CREATE TABLE IF NOT EXISTS Parents (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(1000),
    last_name varchar(1000),
    email_address varchar(255),
    street varchar(100),
    zip_code int(4),
    city varchar(100),
    phone_number varchar(15)
    );

CREATE TABLE IF NOT EXISTS Children (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(1000),
    last_name varchar(1000),
    parent_ID int(7),
    date_of_birth date,
    CPR varchar(10) NOT NULL,
    gender char(1) CHECK ( gender = 'M' OR gender = 'F'),
    FOREIGN KEY (parent_ID) REFERENCES Parents(ID)
    );

CREATE TABLE IF NOT EXISTS Employees (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(1000),
    last_name varchar(1000),
    email_address varchar(255),
    street varchar(100),
    zip_code int(4),
    city varchar(100),
    phone_number varchar(15),
    salary float(6),
    CPR varchar(10) NOT NULL,
    password varchar(255)
    );

CREATE TABLE IF NOT EXISTS Waiting_list (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    Child_ID int(7),
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Child_ID)
        REFERENCES Children(ID)
        ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS Classes (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    number_of_kids int(10)
    );

CREATE TABLE IF NOT EXISTS Attendees (
    child_ID int(7),
    group_ID int(7),
    FOREIGN KEY (child_ID) REFERENCES Children(ID) ON DELETE CASCADE,
    FOREIGN KEY (group_ID) REFERENCES Classes(ID)
    );

CREATE TABLE IF NOT EXISTS Schedule (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    active boolean
    );

CREATE TABLE IF NOT EXISTS Employee_assigment (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    schedule_ID int(7),
    employee_ID int(7),
    group_ID int(7),
    start_time TIME,
    end_time TIME,
    day char(2)  CHECK ( day = 'MO' OR day = 'TU' OR day = 'WE' OR day = 'TH' OR day = 'FR'),
    FOREIGN KEY (schedule_ID) REFERENCES Schedule(ID),
    FOREIGN KEY (employee_ID) REFERENCES Employees(ID),
    FOREIGN KEY (group_ID) REFERENCES Classes(ID)
    );

