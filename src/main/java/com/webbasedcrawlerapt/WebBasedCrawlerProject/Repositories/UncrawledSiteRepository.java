package com.webbasedcrawlerapt.WebBasedCrawlerProject.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.UncrawledSite;

@Repository
public interface UncrawledSiteRepository extends JpaRepository<UncrawledSite, Integer> {

    
    public Optional<UncrawledSite> findUncrawledSiteById(int siteId);

    void addUncrawledSite(int siteId, String url);
	
    // @Query(value = "SELECT * From boms_engineer as E join boms_resource as R on E.resource_Id=R.Id where R.account_id= :accountId " ,nativeQuery = true)
	// public Page<Engineer> findByAccountId(@Param("accountId")Long accountId, Pageable paging);
	
    // @Query(value="SELECT * FROM boms_engineer as E WHERE concat(firstName,' ',familyName) like %:search%",nativeQuery=true)
	// public Page<Engineer> searchByFirstName(@Param("search")String search , Pageable page);
}




