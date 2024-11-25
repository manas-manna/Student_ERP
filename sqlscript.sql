create database student;
use student;

CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE student (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    photograph_path VARCHAR(255),
    cgpa DOUBLE,
    total_credits INT,
    graduation_year INT,
    domain_id BIGINT,
    specialisation_id BIGINT,
    placement_id BIGINT
);

CREATE TABLE bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    amount DOUBLE NOT NULL,
    due_date DATE,
    deadline DATE
);

CREATE TABLE credit_balance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    balance DOUBLE NOT NULL DEFAULT 0.00,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE
);

CREATE TABLE student_bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    bill_id BIGINT NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    CONSTRAINT fk_bill FOREIGN KEY (bill_id) REFERENCES bills(id) ON DELETE CASCADE
);

CREATE TABLE student_payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    description VARCHAR(255),
    amount DOUBLE NOT NULL,
    payment_date DATE NOT NULL,
    bill_id BIGINT NOT NULL,
    CONSTRAINT fk_student_payment_student FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    CONSTRAINT fk_student_payment_bill FOREIGN KEY (bill_id) REFERENCES bills(id) ON DELETE CASCADE
);
