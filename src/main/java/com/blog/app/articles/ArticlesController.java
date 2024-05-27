package com.blog.app.articles;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.users.UserEntity;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

	@GetMapping("")
	String getArticles() {
		return "get all articles";
	}
	
	@GetMapping("/{id}")
	String getArticleById(@PathVariable("id") String id) {
		return "get article with id: " + id;
	}
	
	@PostMapping("")
	String createArticle(@AuthenticationPrincipal UserEntity user) {
		return "create article called by " + user.getUserName();
	}
	
}
