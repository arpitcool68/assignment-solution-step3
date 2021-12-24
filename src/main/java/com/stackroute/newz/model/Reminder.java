package com.stackroute.newz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "reminder")
public class Reminder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reminderId")
	private int reminderId;
	@Column(name = "schedule")
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime schedule;
	@OneToOne(targetEntity = News.class)
	@JsonIgnore
	private News news;
	
	public Reminder(int reminderId, LocalDateTime schedule, News news) {
		super();
		this.reminderId = reminderId;
		this.schedule = schedule;
		this.news = news;
	}

	public Reminder() {

	}

	public int getReminderId() {
		return reminderId;
	}

	public void setReminderId(int reminderId) {
		this.reminderId = reminderId;
	}

	public LocalDateTime getSchedule() {
		return schedule;
	}

	public void setSchedule(LocalDateTime schedule) {
		this.schedule = schedule;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "Reminder [reminderId=" + reminderId + ", schedule=" + schedule + ", news=" + news + "]";
	}

	
}
