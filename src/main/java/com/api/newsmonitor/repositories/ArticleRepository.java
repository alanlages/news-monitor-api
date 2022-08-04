package com.api.newsmonitor.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.newsmonitor.models.ArticleModel;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, UUID> {

	boolean existsByTitle(String title);
}
