package com.api.newsmonitor.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.newsmonitor.dtos.ArticleDTO;
import com.api.newsmonitor.models.ArticleModel;
import com.api.newsmonitor.services.ArticleService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/article")
public class ArticleController {

	final ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping
	public ResponseEntity<Page<ArticleModel>> getAllArticles(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(articleService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneArticle(@PathVariable(value = "id") UUID id) {
		Optional<ArticleModel> articleModelOptional = articleService.findById(id);
		if(!articleModelOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
		return ResponseEntity.status(HttpStatus.OK).body(articleModelOptional.get());
	}	
		
	@PostMapping
	public ResponseEntity<Object> saveArticle(@RequestBody @Valid ArticleDTO articleDTO) {
		
		if(articleService.existsByTitle(articleDTO.getTitle())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Article already exists!");
		}
		
		ArticleModel articleModel = new ArticleModel();
		BeanUtils.copyProperties(articleDTO, articleModel);
		articleModel.setDateArticle(LocalDate.now(ZoneId.of("UTC")));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(articleModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteArticle(@PathVariable(value = "id") UUID id) {
		Optional<ArticleModel> articleModelOptional = articleService.findById(id);
		if(!articleModelOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
		articleService.delete(articleModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Article deleted successfully.");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateArticle(@PathVariable(value = "id") UUID id,
													@RequestBody @Valid ArticleDTO articleDTO) {
		Optional<ArticleModel> articleModelOptional = articleService.findById(id);
		if(!articleModelOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
		
		ArticleModel articleModel = articleModelOptional.get();
		BeanUtils.copyProperties(articleDTO, articleModel);
		articleModel.setId(articleModelOptional.get().getId());		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.save(articleModel));
	}	
	
}
