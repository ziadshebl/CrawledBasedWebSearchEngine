package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Suggestion;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Website;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Word;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import opennlp.tools.stemmer.PorterStemmer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class WordService {

    @Autowired
    private MongoOperations mongoOperations;

    public ResponseEntity<?> getAllWebsitesByWord(String word, int pageSize, int pageNum){


        MongoClient mongoClient = MongoClients.create("mongodb+srv://rootUser:webcrawler_1@cluster0.gsdmf.mongodb.net/CrawlerAndIndexer?w=majority");
        MongoDatabase mDatabase = mongoClient.getDatabase("CrawlerAndIndexer");
        MongoCollection<Document> suggestionsCollection = mDatabase.getCollection("Suggestions");
        Document updateResult = suggestionsCollection.findOneAndUpdate(Filters.eq("word", word), Updates.inc("frequency", 1));
        if(updateResult==null){
              
            Document myDoc = new Document();
        
            myDoc.put("word", word);
            myDoc.put("frequency", 1);
            suggestionsCollection.insertOne(myDoc);
        }
        

        
        word = Stemmer.stem(word);
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


    public ResponseEntity<?> getSuggestions(String word){
        

        List<Suggestion> suggestions = mongoOperations.find( Query.query(Criteria.where("word").regex('^'+word)), Suggestion.class, "Suggestions");
        
        Collections.sort(suggestions, new Comparator<Suggestion>() {
            @Override
            public int compare(Suggestion lhs, Suggestion rhs) {
                return lhs.frequency > rhs.frequency ? -1 : (lhs.frequency < rhs.frequency) ? 1 : 0;
            }
        });

        if(!suggestions.isEmpty()){   
            return new ResponseEntity<>(suggestions, HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        
        
       
    }

}

class Stemmer{
    public static String stem(String word){
        PorterStemmer porterStemmer = new PorterStemmer();
        String stem = porterStemmer.stem(word);

        return stem;
    }
}