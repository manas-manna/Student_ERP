create database student;
use student;
--  ADMIN table
CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Bill table
CREATE TABLE bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    amount DOUBLE NOT NULL,
    due_date DATE,
    deadline DATE
);

 -- Student table
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

 -- Credit balance table
 CREATE TABLE credit_balance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    balance DOUBLE NOT NULL DEFAULT 0.00
);

 -- Student bills table
CREATE TABLE student_bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    bill_id BIGINT NOT NULL
);

 -- Student Payment
 CREATE TABLE student_payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    description VARCHAR(255),
    amount DOUBLE NOT NULL,
    payment_date DATE NOT NULL,
    bill_id BIGINT NOT NULL
);
