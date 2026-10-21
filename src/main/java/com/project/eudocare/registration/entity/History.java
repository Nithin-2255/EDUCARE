package com.project.eudocare.registration.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class History 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne
    private Prescription prescription;
	@ManyToOne
	private doctor doctor;
	@ManyToOne
	private patient patient;
    public doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(doctor doctor) {
		this.doctor = doctor;
	}
	public patient getPatient() {
		return patient;
	}
	public void setPatient(patient patient) {
		this.patient = patient;
	}
	private String date;
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public History(Prescription prescription, com.project.eudocare.registration.entity.doctor doctor,
			com.project.eudocare.registration.entity.patient patient, String date) {
		super();
		this.prescription = prescription;
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
	}
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
}
