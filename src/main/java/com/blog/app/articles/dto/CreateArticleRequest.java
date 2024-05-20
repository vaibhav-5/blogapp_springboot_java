package com.blog.app.articles.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {

	@NonNull
	private String title;
	@NonNull
	private String body;
	@Nullable
	private String subtitle;
}
