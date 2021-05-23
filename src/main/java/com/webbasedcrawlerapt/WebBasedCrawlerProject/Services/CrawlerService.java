package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    @Autowired
	private UncrawledSiteService uncrawledSiteService;

    public void getLinks(String siteUrl) {
        try {
            Document doc = Jsoup.connect(siteUrl).userAgent("Mozilla").get();
            Elements links = doc.select("a");

            if (links.isEmpty()) {
                return;
            }
            int index = 0;
            for (org.jsoup.nodes.Element link : links) {
                String foundUrl = link.attr("abs:href").toLowerCase();
                System.out.println(foundUrl);
                uncrawledSiteService.addUncrawledSite(index, foundUrl);
                index +=1; 
                // if( isSuitable(foundUrl) && ( !hs.contains(foundUrl) ) ) {
                //     hs.add(foundUrl);
                //     crawl(foundUrl);
                // }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
