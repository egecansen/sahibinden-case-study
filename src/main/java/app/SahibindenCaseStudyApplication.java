package app;

import app.config.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "app")
@EnableConfigurationProperties(Properties.class)
public class SahibindenCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SahibindenCaseStudyApplication.class, args);
	}

}
