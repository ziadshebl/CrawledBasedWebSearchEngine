package com.webbasedcrawlerapt.WebBasedCrawlerProject;
import com.webbasedcrawlerapt.Services.Crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class WebBasedCrawlerProjectApplication {

	@Bean("crawl")
    public Crawler crawlSites() {
		Crawler crawler = new Crawler();
		return crawler;
    }
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WebBasedCrawlerProjectApplication.class, args);
        System.out.println("Application context initialized!!!");
        ctx.getBean("crawl", Crawler.class);
	}


}

