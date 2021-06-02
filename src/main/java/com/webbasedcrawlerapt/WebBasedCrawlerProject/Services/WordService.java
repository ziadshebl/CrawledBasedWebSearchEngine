package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Website;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WordService {

    @Autowired
    private MongoOperations mongoOperations;

    public ResponseEntity<?> getAllWebsitesByWord(String word, int pageSize, int pageNum){
        

        List<Word> words = mongoOperations.find( Query.query(Criteria.where("word").is(word)), Word.class, "Indexer");
        if(!words.isEmpty()){
            Word result = words.get(0);
            System.out.println(words.get(0).toString());
            List<Website> websites = result.getWebsites();
            int size = websites.size();
            
            if(pageSize!=0 && pageNum!=0){
                if(pageSize*pageNum+pageSize<size){
                    websites = websites.subList(pageSize*pageNum, pageSize*pageNum+pageSize);
                }else{
                    websites = websites.subList(pageSize*pageNum, size);
                }
            }
           
           
            return new ResponseEntity<>(websites, HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        
        
       
    }

}