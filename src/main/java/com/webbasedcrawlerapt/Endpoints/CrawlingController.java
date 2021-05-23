package com.webbasedcrawlerapt.Endpoints;

import com.webbasedcrawlerapt.Services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawler")
public class CrawlingController {

    @Autowired
	private CrawlerService crawlerService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> getUncrawledSite() {
        System.out.println("FUNCTION CALLED");
        crawlerService.getLinks("https://www.stackoverflow.com");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
