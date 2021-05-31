package com.webbasedcrawlerapt.WebBasedCrawlerProject.Services;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Website;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Word;
import com.webbasedcrawlerapt.WebBasedCrawlerProject.Repositories.WordRepository;

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

    private WordRepository wordRepository;
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    public WordService(WordRepository reservationRepository) {
        this.wordRepository = reservationRepository;
    }

    public Word saveReservation(Word reservation){
        return wordRepository.save(reservation);
    }

    public ResponseEntity<?> getAllWebsitesByWord(String word){
        
        List<Word> words = mongoOperations.find( Query.query(Criteria.where("word").is(word)), Word.class, "Indexer");
        if(!words.isEmpty()){
            Word result = words.get(0);
            System.out.println(words.get(0).toString());
            List<Website> websites = result.getWebsites();
            return new ResponseEntity<>(websites, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        
        
       
    }

}