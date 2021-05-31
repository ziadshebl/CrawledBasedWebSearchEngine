package com.webbasedcrawlerapt.WebBasedCrawlerProject.Models;

public class Website {
 
    public int TF;
    public int titleFrequency;

    public int plainTextFrequency;

    public int headingsFrequency;

    public String url;

    Website(int TF, int titleFrequency, int plainTextFrequency, int headingsFrequency, String url){
        this.TF = TF;
        this.titleFrequency = titleFrequency;
        this.plainTextFrequency = plainTextFrequency;
        this.headingsFrequency = headingsFrequency;
        this.url = url;
    }

}
