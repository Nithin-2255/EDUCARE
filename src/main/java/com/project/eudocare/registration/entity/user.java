package com.project.eudocare.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class user 
{
	

  public user() {
		super();
		// TODO Auto-generated constructor stub
	}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  @OneToOne
  private patient pid;
  @OneToOne
  private doctor did;
  private String role;
public user(String username, String password, String role) {
	super();
	this.username = username;
	this.password = password;
	this.role = role;
}
public doctor getDid() {
	return did;
}
public void setDid(doctor did) {
	this.did = did;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public patient getPid() {
	return pid;
}
public void setPid(patient pid) {
	this.pid = pid;
}
  
}
