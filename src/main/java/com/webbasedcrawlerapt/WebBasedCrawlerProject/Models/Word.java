package com.webbasedcrawlerapt.WebBasedCrawlerProject.Models;


import java.util.ArrayList;
import org.springframework.data.annotation.Id;


public class Word {  
    @Id  
    private String _id;

    private int df;
    
    private String word;

    private ArrayList<Website> links;


    public Word(String _id, int df, String word, ArrayList<Website> links){
        this._id = _id;
        this.df = df;
        this.word = word;
        this.links = links;
    }
    
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getDf() {
        return df;
    }

    public void setWord(int df) {
        this.df = df;
    }

    public ArrayList<Website> getWebsites() {
        return links;
    }

    public void addWebsite(Website website) {
        this.links.add(website);
    }


    @Override
    public String toString() {
        return String.format(
            "Word[id=%s, word='%s', links='%s']",
            _id, word, links.get(0).url);
    }

}

