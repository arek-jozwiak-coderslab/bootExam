package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BootExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootExamApplication.class, args);
	}
}
