package com.webbasedcrawlerapt.WebBasedCrawlerProject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


// @SpringBootApplication
@EnableJpaRepositories
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
public class WebBasedCrawlerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBasedCrawlerProjectApplication.class, args);
        System.out.println("Application context initialized!!!");
	}


}

