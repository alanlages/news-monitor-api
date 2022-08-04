package com.api.newsmonitor.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.newsmonitor.models.ArticleModel;
import com.api.newsmonitor.repositories.ArticleRepository;

@Service
public class ArticleService {
	
	final ArticleRepository articleRepositoy;
	
	public ArticleService(ArticleRepository articleRepositoy) {
		this.articleRepositoy = articleRepositoy;
	}

	public Page<ArticleModel> findAll(Pageable pageable) {
		return articleRepositoy.findAll(pageable);
	}
	
	public Optional<ArticleModel> findById(UUID id) {
		return articleRepositoy.findById(id);
	}
	
	@Transactional
	public ArticleModel save(ArticleModel articleModel) {
		return articleRepositoy.save(articleModel);
	}
	
	@Transactional
	public void delete(ArticleModel articleModel) {
		articleRepositoy.delete(articleModel);		
	}
	
	public boolean existsByTitle(String title) {
		return articleRepositoy.existsByTitle(title);
	}
}
