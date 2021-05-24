package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
// import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.UncrawledSite;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {

    @Autowired
	private UncrawledSiteService uncrawledSiteService;


    public ArrayList<String> readSeeds(){
        ArrayList<String> data = new ArrayList<String>() ;
        try {
            File txt = new File("D:/CCE/CCE SENIOR 1/APT/projectV4/CrawledBasedWebSearchEngine/src/seeds.txt");
            Scanner scan;
            
            scan = new Scanner(txt);
            while(scan.hasNextLine()){
                data.add(scan.nextLine());
            }

            scan.close();
           
        } catch (FileNotFoundException e) {
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

            int count = seeds.size();
            List<UncrawledSite> sites = uncrawledSiteService.findUncrawledSiteByIsNotVisited();
          
            //LW MAWSELTESH LEL LIMIT BTA3Y W LESA 3ANDY 7AGAT NOT VISITED
            while (count!=50 && sites.size()!=0){
                String siteUrl = sites.get(0).getUrl();

                //TODO: CHECK ROBOTS HENA //(no need to check robots for seeds, any other url will be checked befor inserting in in DB)//
                //ALTER TABLE uncrawled_sites MODIFY url VARCHAR(1000) ; //run this query in DB//
                Document doc = Jsoup.connect(siteUrl).userAgent("Mozilla").get();
                Elements links = doc.select("a");
                
                links.forEach((link)->{
                    String urlString = link.attr("abs:href");
                    boolean checkRobotsTxt = checkRobots(urlString);
                    System.out.println(urlString+"      //       "+checkRobotsTxt);
                    if (checkRobotsTxt)
                    {
                    URL url;
                    try {
                        url = new URL(urlString);
                        //CHECK EL PROTOCOL 3LSHAN LW FEH MAILTO: BADAL HTTPS AW HTTP
                        if (url.getProtocol().equals("https") || url.getProtocol().equals("http")){
                           
                            //AT2AKED ENO MSH MAWGODA FEL DATABASE ALREADY
                            List<UncrawledSite> temp = uncrawledSiteService.findUncrawledSiteByUrl(urlString);
                            if(temp.size()==0){
                                //System.out.println(urlString);
                                //ADD IT FEL DATABASE
                                uncrawledSiteService.addUncrawledSite(urlString, false);
                            }
                        }
                    } catch (MalformedURLException e) {
                        System.out.println(e.getMessage());
                    }   
                }
                });
                //NE2LEB EL ISVISITED W NEZAWED EL COUNT
                uncrawledSiteService.updateIsVisitedById(sites.get(0).getId());
                count+=1;

                sites = uncrawledSiteService.findUncrawledSiteByIsNotVisited();
            }


            //(SORTED)WHILE THERE URLS WITH FALSE ISVISITED ATTRIBUTE || REACH LIMIT
                //HANEMSEK AWEL WA7DA
                //NEGEB EL BODY W NESHOF HAN3MEL BEH EH
                //NEGEBE EL URLS
                    //LKOL URL NCALL ROBOTS
                    //LKOL URL NCHECK ENAHA MSH MAWGODA
                    //LW MSH MAWGODA W EL ROBOTS AMAN NEZAWEDHA FEL DATABASE
                //NE2LEB EL ISVISITED NE5ALEHA TRUE    
            
            
        }catch(MalformedURLException ex){
            System.out.println(ex.getMessage());
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean checkRobots(String url) {
        try {
            URL urlObj = null;
            urlObj = new URL(url);
            String path = urlObj.getPath();
            if(path.contains("#"))
                return false;
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
                    {
                        return false;
                    }
                        
                    if(!path.equals("/"))
                    {
                        if((robotTextArray[i]).contains(path))
                        {
                            return false;
                        }
                    }
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
            return false;

        }
        return true;

    }
}
