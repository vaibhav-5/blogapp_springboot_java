package com.blog.app.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.articles.dto.CreateArticleRequest;
import com.blog.app.articles.dto.UpdateArticleRequest;
import com.blog.app.users.UsersRepository;
import com.blog.app.users.UsersService;

@Service
public class ArticlesService {

	@Autowired
	private ArticlesRepository articlesRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Iterable<ArticleEntity> getAllArticles(){
		return articlesRepository.findAll();
	}
	
	public ArticleEntity getArticleBySlug(String slug) {
		var article = articlesRepository.findBySlug(slug);
		if(article == null) {
			throw new ArticleNotFoundException(slug);
		}
		return article;
	}
	
	public ArticleEntity createArticle(CreateArticleRequest articleDto, Long authorId) {
		 var author = usersRepository.findById(authorId).orElseThrow(()-> new UsersService.UserNotFoundException(authorId));
		 return articlesRepository.save(ArticleEntity.builder()
				 .title(articleDto.getTitle())
				 //TODO: Create a proper slugification fn
				 .slug(articleDto.getTitle().toLowerCase().replaceAll("\\s+", "-"))
				 .body(articleDto.getBody())
				 .subtitle(articleDto.getSubtitle())
				 .author(author)
				 .build()
				 );
	}
	
	public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest articleDto) {
		var article = articlesRepository.findById(articleId).orElseThrow(()-> new ArticleNotFoundException(articleId));
		
		if(articleDto.getTitle() != null) {
			article.setTitle(articleDto.getTitle());
			article.setSlug(articleDto.getTitle().toLowerCase().replaceAll("\\s+", "-"));
		}
		
		if(articleDto.getBody() != null) {
			article.setBody(articleDto.getBody());
		}
		
		if(articleDto.getSubtitle() != null) {
			article.setSubtitle(articleDto.getSubtitle());
		}
		
		return articlesRepository.save(article);
	}
	
public static class ArticleNotFoundException extends IllegalArgumentException{
		
		public ArticleNotFoundException(String slug) {
			super("Article with slug " + slug + " not found.");
		}
		
		public ArticleNotFoundException(Long id) {
			super("Article with id " + id + " not found.");
		}
	}
}
