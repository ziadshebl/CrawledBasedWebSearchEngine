package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;
import java.util.List;
import java.util.Optional;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.UncrawledSite;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Repositories.UncrawledSiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UncrawledSiteService {
    
    @Autowired
    private UncrawledSiteRepository uncrawledSiteRepository;

    public ResponseEntity<?> getUncrawledSiteById(int uncrawledId) {
		
		Optional<UncrawledSite> targetSite = uncrawledSiteRepository.findUncrawledSiteById(uncrawledId);
		
		if(!targetSite.isPresent()) {
			
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(targetSite, HttpStatus.OK);
	}
	
	public ResponseEntity<?> addUncrawledSite(String url, boolean isVisited) {
		
		UncrawledSite newSite = new UncrawledSite();

        newSite.setUrl(url);
		newSite.setIsVisited(isVisited);
		
		// System.ou

		// save resource object
		uncrawledSiteRepository.save(newSite);
		
		return new ResponseEntity<>(newSite, HttpStatus.OK);
		
	}

	public List<UncrawledSite> findUncrawledSiteByUrl(String urlToFind){

		List<UncrawledSite> sites = uncrawledSiteRepository.findUncrawledSiteByUrl(urlToFind);
		return sites;
	}

	public List<UncrawledSite> findUncrawledSiteByIsNotVisited(){
		List<UncrawledSite> sites = uncrawledSiteRepository.findUncrawledSiteByIsNotVisited();
		return sites;
	}

	public void updateIsVisitedById(int id){
		uncrawledSiteRepository.updateIsVisitedById(id);
	}
}
