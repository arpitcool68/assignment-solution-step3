package com.stackroute.newz.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.newz.model.News;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping
	public ResponseEntity<List<News>> getAllNews() {
		List<News> newsList = newsService.getAllNews();
		if (!newsList.isEmpty()) {
			return new ResponseEntity<>(newsList, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{newsId}")
	public ResponseEntity<News> getNewsById(@PathVariable Integer newsId) {
		try {
			News news;
			if (null != newsId) {
				news = newsService.getNews(newsId);
				if (null != news) {
					return new ResponseEntity<>(news, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (NewsNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<News> add(@RequestBody News news) {
		try {
			News savedNewsObj = newsService.addNews(news);
			return new ResponseEntity<>(savedNewsObj, HttpStatus.CREATED);
		} catch (NewsAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping("{newsId}")
	public ResponseEntity<News> update(@RequestBody News news, @PathVariable Integer newsId) {
		try {
			News existingNews = newsService.getNews(newsId);
			BeanUtils.copyProperties(news, existingNews);
			return new ResponseEntity<>(newsService.updateNews(existingNews), HttpStatus.OK);
		} catch (NewsNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{newsId}")
	public ResponseEntity<News> delete(@PathVariable Integer newsId) {
		try {
			News existingNews = newsService.getNews(newsId);
			newsService.deleteNews(existingNews.getNewsId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NewsNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
