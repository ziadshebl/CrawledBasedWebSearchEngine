package com.webbasedcrawlerapt.WebBasedCrawlerProject;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Repositories.WordRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = WordRepository.class)
public class WebBasedCrawlerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBasedCrawlerProjectApplication.class, args);
        System.out.println("Application context initialized!!!");
		
	}

}

