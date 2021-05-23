package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import java.io.IOException;
import java.net.URL;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.UncrawledSite;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    @Autowired
	private UncrawledSiteService uncrawledSiteService;

    public void startCrawling() {
        try {
            String siteUrl = "https://www.stackoverflow.com";
            ResponseEntity<?> temp = uncrawledSiteService.checkIfSiteExists("https://www.stackoverflow.com");
            // System.out.println(temp.getBody());
            Document doc = Jsoup.connect(siteUrl).userAgent("Mozilla").get();
            //Elements links = doc.select("a");
            // System.out.println(doc.body().text());

            // if (links.isEmpty()) {
            //     return;
            // }
  
            // for (org.jsoup.nodes.Element link : links) {
            //     String foundUrl = link.attr("abs:href").toLowerCase();
            //     uncrawledSiteService.addUncrawledSite(foundUrl, false);
            // }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean checkRobots(String url) {
        try {
            URL urlObj = null;
            urlObj = new URL(url);
            String path = urlObj.getPath();
            //String[] pathArray = path.split("/");
            // String queryParameter = urlObj.getQuery();
            String robotsFileUrl = urlObj.getProtocol() + "://" + urlObj.getHost() + "/robots.txt";
            Document doc = Jsoup.connect(robotsFileUrl).ignoreContentType(true).userAgent("Mozilla").get();
            String robotsText = doc.text();
            int index = robotsText.indexOf("User-Agent: *");
            if(index == -1)
            {
                index = robotsText.indexOf("User-agent: *");
                if(index == -1) return false;
            }
            String sub = robotsText.substring(index);

            String[] robotTextArray = sub.split(" ");
            for(int i =3 ; i< robotTextArray.length ; i+=2)
            {

                if((robotTextArray[i-1]).equals( "Disallow:"))
                {
                    if((robotTextArray[i]).equals( "/")) //All are disallowed
                        return false;

                    if((robotTextArray[i]).contains(path))
                    {
                        return false;
                    }

                // String[] disallowedPath = (robotTextArray[i]).split("/");
                // for(int x =0 ; x< Math.min(disallowedPath.length, pathArray.length) ; x++)
                // {
                //     if((pathArray[x]).equals(disallowedPath[x]))
                //         return false;
                // }
                }
                else if((robotTextArray[i-1]).equals( "Allow:"))
                {
                    //check if it is allowed -> return true
                }
                else
                {
                    break;
                }

            }
        } catch (IOException ex) {

        }
        return true;

    }
}
