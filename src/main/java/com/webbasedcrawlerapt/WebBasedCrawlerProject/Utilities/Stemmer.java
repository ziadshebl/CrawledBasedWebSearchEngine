package com.webbasedcrawlerapt.WebBasedCrawlerProject.Utilities;
import opennlp.tools.stemmer.PorterStemmer;
class Stemmer{
public static void main(String[] args) {
    String wordList[] = 
    { "draft", "drafted", "drafting", "drafts", 
        "drafty", "draftsman" };
PorterStemmer porterStemmer = new PorterStemmer();
for (String word : wordList) {
    String stem = porterStemmer.stem(word);
    System.out.println("The stem of " + word + " is " + stem);
}
        }
}