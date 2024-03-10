package com.softel.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "micro_users")
public class User {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "Name")
	private String name;

	@Column(name = "EmailID")
	private String emailId;

	@Column(name = "About")
	private String about;

	public User() {
		super();
	}

	public User(String id, String name, String emailId, String about) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.about = about;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", about=" + about + "]";
	}

}
