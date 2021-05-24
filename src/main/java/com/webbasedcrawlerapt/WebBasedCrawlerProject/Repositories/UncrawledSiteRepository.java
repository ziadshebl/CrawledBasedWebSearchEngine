package com.webbasedcrawlerapt.WebBasedCrawlerProject.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.webbasedcrawlerapt.WebBasedCrawlerProject.Models.UncrawledSite;

@Repository
public interface UncrawledSiteRepository extends JpaRepository<UncrawledSite, Integer> {

    
    public Optional<UncrawledSite> findUncrawledSiteById(int siteId);


    // @Query(value="INSERT INTO (id, url, isVisited)  Uncrawled_Sites  VALUES (:id, :url, :isVisited)", nativeQuery = true)
    // void addUncrawledSite(int id, String url, boolean isVisited);
	


    @Query(value = "SELECT * FROM Uncrawled_sites E where E.url = :urlSent", nativeQuery = true)
    public List<UncrawledSite> findUncrawledSiteByUrl(@Param("urlSent")String url);
 
    @Query(value = "SELECT * FROM Uncrawled_sites E where E.isVisited = false", nativeQuery = true)
    public List<UncrawledSite> findUncrawledSiteByIsNotVisited();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Uncrawled_sites SET isVisited = true WHERE id = :id", nativeQuery= true)
    public void updateIsVisitedById(@Param("id")int id);
    // @Query(value = "SELECT * From boms_engineer as E join boms_resource as R on E.resource_Id=R.Id where R.account_id= :accountId " ,nativeQuery = true)
	// public Page<Engineer> findByAccountId(@Param("accountId")Long accountId, Pageable paging);
	
    // @Query(value="SELECT * FROM boms_engineer as E WHERE concat(firstName,' ',familyName) like %:search%",nativeQuery=true)
	// public Page<Engineer> searchByFirstName(@Param("search")String search , Pageable page);
}




