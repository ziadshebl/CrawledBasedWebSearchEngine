package com.webbasedcrawlerapt.WebBasedCrawlerProject.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "UNCRAWLED_SITES")
public class UncrawledSite {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id; 

    @NonNull
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
