package com.project.eudocare.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class doctorRequest 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne
    private doctor doctorId;
    private String changeRequest;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public doctor getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(doctor doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
	public doctorRequest(doctor doctorId, String changeRequest) {
		super();
		this.doctorId = doctorId;
		this.changeRequest = changeRequest;
	}
	public String getChangeRequest() {
		return changeRequest;
	}
	public void setChangeRequest(String changeRequest) {
		this.changeRequest = changeRequest;
	}
	public doctorRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
