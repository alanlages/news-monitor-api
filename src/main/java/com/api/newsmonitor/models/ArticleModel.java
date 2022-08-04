package com.api.newsmonitor.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_article")
public class ArticleModel implements Serializable {

	private static final long serialVersionUID = 4752819785309602789L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false)
	private LocalDate dateArticle;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public LocalDate getDateArticle() {
		return dateArticle;
	}
	
	public void setDateArticle(LocalDate dateArticle) {
		this.dateArticle = dateArticle;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
