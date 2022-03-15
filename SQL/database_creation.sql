DROP DATABASE IF EXISTS Daycare;

CREATE DATABASE IF NOT EXISTS Daycare;

USE Daycare;

CREATE TABLE IF NOT EXISTS Parents (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(1000),
    last_name varchar(1000),
    email_address varchar(255),
    address varchar(100),
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
    gender char(1) CHECK ( gender = 'M' OR gender = 'F'),
    FOREIGN KEY (parent_ID) REFERENCES Parents(ID)
);

CREATE TABLE IF NOT EXISTS Employees (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(1000),
    last_name varchar(1000),
    email_address varchar(255),
    address varchar(100),
    zip_code int(4),
    city varchar(100),
    phone_number varchar(15),
    salary float(6)
);

CREATE TABLE IF NOT EXISTS Waiting_list (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    Child_ID int(7),
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Child_ID) REFERENCES Children(ID)
);

CREATE TABLE IF NOT EXISTS Classes (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    number_of_kids int(10),
    teacher_1_ID int(7),
    teacher_2_ID int(7),
    teacher_3_ID int(7),
    teacher_4_ID int(7)
);

CREATE TABLE IF NOT EXISTS Attendees (
    child_ID int(7),
    group_ID int(7),
    FOREIGN KEY (child_ID) REFERENCES Children(ID),
    FOREIGN KEY (group_ID) REFERENCES Classes(ID)
);

CREATE TABLE IF NOT EXISTS Schedule (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    active boolean
);

CREATE TABLE IF NOT EXISTS employee_assigment (
    ID int(7) AUTO_INCREMENT PRIMARY KEY,
    schedule_ID int(7),
    employee_ID int(7),
    group_ID int(7),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (schedule_ID) REFERENCES Schedule(ID),
    FOREIGN KEY (employee_ID) REFERENCES Employees(ID),
    FOREIGN KEY (group_ID) REFERENCES Classes(ID)
);
