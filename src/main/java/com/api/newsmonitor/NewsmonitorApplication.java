package com.api.newsmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NewsmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsmonitorApplication.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "<h2>Article</h2><br /><a href=\"/article\">article</a>";
	}

}
