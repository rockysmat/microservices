package com.in28minutes.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@JsonIgnoreProperties(value= {"id","userName"})	//fields will not be returned in response.
//checkout dynamic filtering lecture 25
@ApiModel(description="Info about User object")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Past
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(notes="date of birth must be earlier that current date")
	private Date dob;
	
	@Size(min=2, message="name must be at least 2 characters")
	@ApiModelProperty(notes="name must be at least 2 characters")
	private String userName;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	protected User() {	
	}
	
	public User(Integer id, String userName, Date dob) {
		super();
		this.id = id;
		this.userName = userName;
		this.dob = dob;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, userName=%s, dob=%s]", id, userName, dob);
	}
	
}
