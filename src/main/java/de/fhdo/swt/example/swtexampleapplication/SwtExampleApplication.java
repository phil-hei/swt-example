package de.fhdo.swt.example.swtexampleapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("de.fhdo.swt.example.swtexampleapplication.repository")
@EntityScan("de.fhdo.swt.example.swtexampleapplication.entity")
public class SwtExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwtExampleApplication.class, args);
	}

}
