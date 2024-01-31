package com.roima.exammanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { ExamManagementApplication.class, Jsr310JpaConverters.class })
public class ExamManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamManagementApplication.class, args);
	}

}
