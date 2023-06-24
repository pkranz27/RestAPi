package com.Pk.Udemy.User;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity(name="user_details")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
		
	@Size(min=2, message = "Name should have at least 2 letters")
	//@JsonProperty("user_name")
	private String name;
	
	@Past(message ="Birth Date should be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	protected User() {
		
	}
	
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
