package com.manasmann.studenterp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class StudentErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentErpApplication.class, args);
    }

}
