package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import java.io.IOException;
import java.net.URL;

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
