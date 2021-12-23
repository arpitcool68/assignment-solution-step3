package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.News;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 * 
 * Please note that the default path to use this controller should be "/api/v1/news"
 */

@RestController(value = "/api/v1/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	/*
	 * Define a handler method which will get us all news elements.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If all news found successfully.
	 * 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/news" using HTTP GET
	 * method.
	 */

	@GetMapping
	public ResponseEntity<List<News>> getAllNews() {
		List<News> newsList = newsService.getAllNews();
		if (!newsList.isEmpty()) {
			return new ResponseEntity<List<News>>(newsList, HttpStatus.OK);
		}
		return new ResponseEntity<List<News>>(HttpStatus.NOT_FOUND);
	}

	/*
	 * Define a handler method which will get us the news by a newsId.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the news found successfully. 2. 404(NOT
	 * FOUND) - If the news with specified newsId is not found.
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/news/{newsId}" using HTTP
	 * GET method, where "newsId" should be replaced by a valid newsId without {}
	 */

	@GetMapping("/{newsId}")
	public ResponseEntity<News> getNewsById(@PathVariable Integer newsId) {
		try {
			News news;
			if (null != newsId) {
				news = newsService.getNews(Integer.valueOf(newsId));
				if (null != news) {
					return new ResponseEntity<News>(news, HttpStatus.OK);
				}
			}
			return new ResponseEntity<News>(HttpStatus.NOT_FOUND);

		} catch (NewsNotExistsException e) {
			return new ResponseEntity<News>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Define a handler method which will create a news by reading the Serialized
	 * news object from request body and save the mews in news table in database.
	 * Please note that the newsId has to be unique.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 201(CREATED - In case of successful creation of the
	 * news 2. 409(CONFLICT) - In case of duplicate newsId
	 * 
	 * This handler method should map to the URL "/api/v1/news" using HTTP POST
	 * method".
	 */

	@PostMapping
	public ResponseEntity<News> add(@RequestBody News news) {
		try {
			News savedNewsObj = newsService.addNews(news);
			return new ResponseEntity<News>(savedNewsObj, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException e) {
			return new ResponseEntity<News>(HttpStatus.CONFLICT);
		}
	}

	/*
	 * Define a handler method which will update a specific news by reading the
	 * Serialized object from request body and save the updated news details in a
	 * news table in database and handle NewsNotExistsException as well.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the news is updated successfully. 2.
	 * 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{newsId}" using HTTP
	 * PUT method, where "newsId" should be replaced by a valid newsId without {}
	 */

	@PutMapping("{newsId}")
	public ResponseEntity<News> update(@RequestBody News news) {
		try {

			News updatedNews = newsService.updateNews(news);
			return new ResponseEntity<News>(updatedNews, HttpStatus.OK);
		} catch (NewsNotExistsException e) {
			return new ResponseEntity<News>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Define a handler method which will delete a news from the database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the news deleted successfully. 2.
	 * 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{newsId}" using HTTP
	 * Delete method" where "newsId" should be replaced by a valid newsId without {}
	 */

	@DeleteMapping("{newsId}")
	public ResponseEntity<News> delete(@RequestBody News news) {
		try {

			newsService.deleteNews(news.getNewsId());
			return new ResponseEntity<News>(HttpStatus.OK);
		} catch (NewsNotExistsException e) {
			return new ResponseEntity<News>(HttpStatus.NOT_FOUND);
		}
	}

}
