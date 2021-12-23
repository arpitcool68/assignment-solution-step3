package com.stackroute.newz.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private String userId;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "contact")
	private String contact;
	@Column(name = "createAt")
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime createAt;
	@OneToMany
	@JsonIgnore
	private List<News> newsList;

	public UserProfile() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", contact="
				+ contact + ", createAt=" + createAt + ", newsList=" + newsList + "]";
	}

	public UserProfile(String userId, String firstName, String lastName, String contact, LocalDateTime createAt,
			List<News> newsList) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.createAt = createAt;
		this.newsList = newsList;
	}

}
