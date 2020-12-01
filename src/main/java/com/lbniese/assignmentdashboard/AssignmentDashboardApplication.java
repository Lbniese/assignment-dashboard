package com.lbniese.assignmentdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class AssignmentDashboardApplication {

    public static void main(String[] args) {

		SpringApplication.run(AssignmentDashboardApplication.class, args);

		/*
        SpringApplication springApplication = new SpringApplication(AssignmentDashboardApplication.class);

        springApplication.addListeners(new ApplicationPidFileWriter());

        springApplication.run(args);
		*/


    }

}
