package com.webbasedcrawlerapt.Endpoints;

import com.webbasedcrawlerapt.Services.UncrawledSiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uncrawledSite/")
public class UncrawledSiteController {
	
    @Autowired
	private UncrawledSiteService uncrawledSiteService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/uncrawledSite/{uncrawledSiteId}")
	public ResponseEntity<?> getUncrawledSite(@PathVariable int uncrawledSiteId) {
		
		return uncrawledSiteService.getUncrawledSiteById(uncrawledSiteId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/uncrawledSite")
	public ResponseEntity<?> addUncrawledSite(@RequestParam(name="account") int uncrawledSiteId, @RequestBody String url) {
		
		return uncrawledSiteService.addUncrawledSite(uncrawledSiteId, url);
	}
}
