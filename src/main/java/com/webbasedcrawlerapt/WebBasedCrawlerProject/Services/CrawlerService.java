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

    public void startCrawling() {
        try {
            String siteUrl = "https://www.stackoverflow.com";
            Document doc = Jsoup.connect(siteUrl).userAgent("Mozilla").get();
            Elements links = doc.select("a");

            if (links.isEmpty()) {
                return;
            }
            int index = 0;
            for (org.jsoup.nodes.Element link : links) {
                String foundUrl = link.attr("abs:href").toLowerCase();
                uncrawledSiteService.addUncrawledSite(index, foundUrl);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
