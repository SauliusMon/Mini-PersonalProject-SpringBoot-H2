package lt.techin.from.scratch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootMiniProject extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniProject.class, args);
	}
	
}
