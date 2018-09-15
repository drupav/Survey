package com.survey.app.dto;

public class UserData {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String createdBy;
    private String isActive;
    
    public UserData(Long id,String name,String username,String email,String isActive){
    	this.id=id;
    	this.name = name;
    	this.username = username;
    	this.email = email;
    	this.createdBy = createdBy;
    	this.isActive = isActive;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
    
	
	
}