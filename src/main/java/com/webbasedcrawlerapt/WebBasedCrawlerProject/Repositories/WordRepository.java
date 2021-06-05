
package com.webbasedcrawlerapt.WebBasedCrawlerProject.Repositories;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface WordRepository extends CrudRepository<Word, String> {}