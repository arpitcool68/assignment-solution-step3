package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;

/*
 * This class is implementing the NewsService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Override
	public News addNews(News news) throws NewsAlreadyExistsException {

		News fetchedNewsObj = newsRepository.getOne(news.getNewsId());
		if (null != fetchedNewsObj) {
			return newsRepository.saveAndFlush(news);
		}else {
			throw new NewsAlreadyExistsException();
		}
	}

	@Override
	public News getNews(int newsId) throws NewsNotExistsException {

		Optional<News> optionalNews = newsRepository.findById(newsId);
		if (!optionalNews.isPresent()) {
			throw new NewsNotExistsException();
		}

		return optionalNews.get();
	}

	@Override
	public List<News> getAllNews() {
		return newsRepository.findAll();
	}

	@Override
	public News updateNews(News news) throws NewsNotExistsException {
		News fetchedNewsObj = newsRepository.getOne(news.getNewsId());
		if(null != fetchedNewsObj) {
			return newsRepository.saveAndFlush(news);
		}else {
			throw new NewsNotExistsException();
		}
	
	}

	@Override
	public void deleteNews(int newsId) throws NewsNotExistsException {
		News fetchedNews = newsRepository.getOne(newsId);
		if (null != fetchedNews) {
			newsRepository.delete(fetchedNews);
			
		}else {
		throw new NewsNotExistsException();
		}
	}

}
