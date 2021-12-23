package com.stackroute.newz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "newsId")
	private Integer newsId;
	@Column(name = "title")
	private String title;
	@Column(name = "author")
	private String author;
	@Column(name = "description")
	private String description;
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "publishedAt")
	private LocalDateTime publishedAt;
	@Column(name = "content")
	private String content;
	@Column(name = "url")
	private String url;
	@Column(name = "urlToImage")
	private String urlToImage;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId" , nullable = false)
	private UserProfile user;
	@OneToOne(targetEntity = Reminder.class)
	@JsonIgnore
	private Reminder reminder;

	public News(Integer newsId, String title, String author, String description, LocalDateTime publishedAt,
			String content, String url, String urlToImage, UserProfile user, Reminder reminder) {
		super();
		this.newsId = newsId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.publishedAt = publishedAt;
		this.content = content;
		this.url = url;
		this.urlToImage = urlToImage;
		this.user = user;
		this.reminder = reminder;
	}

	/**
	 * @return the newsID
	 */

	public News() {

	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", publishedAt=" + publishedAt + ", content=" + content + ", url=" + url + ", urlToImage="
				+ urlToImage + ", user=" + user + ", reminder=" + reminder + "]";
	}

}
