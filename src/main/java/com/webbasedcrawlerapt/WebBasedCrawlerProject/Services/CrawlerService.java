package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.UncrawledSite;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    @Autowired
	private UncrawledSiteService uncrawledSiteService;


    public ArrayList<String> readSeeds(){
        ArrayList<String> data = new ArrayList<String>() ;
        try {
            File txt = new File("C:/Users/Ziadkamal/Desktop/Senior-1/APT/WebBasedCrawlerProject/WebBasedCrawlerProject/src/seeds.txt");
            Scanner scan;
            
            scan = new Scanner(txt);
            while(scan.hasNextLine()){
                data.add(scan.nextLine());
            }

            scan.close();
           
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
      
      
       
    }

    //TODO: THREADING
    public void startCrawling() {
        try {

            //RUN SEEDS AND ADD IT TO THE DATABASE
            ArrayList<String> seeds = readSeeds();
            for (String x: seeds){
                uncrawledSiteService.addUncrawledSite(x, false);
            }

            String siteUrl = "https://www.geeksforgeeks.org/";
            List<UncrawledSite> temp = uncrawledSiteService.findUncrawledSiteByUrl(siteUrl);
            System.out.println(temp.size());
            Document doc = Jsoup.connect(siteUrl).userAgent("Mozilla").get();

            //(SORTED)WHILE THERE URLS WITH FALSE ISVISITED ATTRIBUTE || REACH LIMIT
                //HANEMSEK AWEL WA7DA
                //NEGEB EL BODY W NESHOF HAN3MEL BEH EH
                //NEGEBE EL URLS
                    //LKOL URL NCALL ROBOTS
                    //LKOL URL NCHECK ENAHA MSH MAWGODA
                    //LW MSH MAWGODA W EL ROBOTS AMAN NEZAWEDHA FEL DATABASE
                //NE2LEB EL ISVISITED NE5ALEHA TRUE    
            
            

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
