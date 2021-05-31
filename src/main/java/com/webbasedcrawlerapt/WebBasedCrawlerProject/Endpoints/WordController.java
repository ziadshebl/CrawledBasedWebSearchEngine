package com.webbasedcrawlerapt.WebBasedCrawlerProject.Endpoints;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import opennlp.tools.stemmer.PorterStemmer;


@RestController
public class WordController {

    private WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    
    @RequestMapping(path="/{word}")
    public ResponseEntity<?> getAllReservations(@PathVariable("word") String word) {

        //word = word.toLowerCase();
        String stemmedWord = Stemmer.stem(word);
        return wordService.getAllWebsitesByWord(stemmedWord);       

    }

}

class Stemmer{
    public static String stem(String word){
        PorterStemmer porterStemmer = new PorterStemmer();
        String stem = porterStemmer.stem(word);

        return stem;
    }
}
