package com.project.eudocare.registration.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class appointment 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private LocalDate date;
	    private LocalTime time;
	    @ManyToOne
	    private doctor doctor;
	    @ManyToOne
	    private patient patient;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public LocalTime getTime() {
			return time;
		}
		public void setTime(LocalTime time) {
			this.time = time;
		}
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
		public appointment(LocalDate date, LocalTime time, com.project.eudocare.registration.entity.doctor doctor,
				com.project.eudocare.registration.entity.patient patient) {
			super();
			this.date = date;
			this.time = time;
			this.doctor = doctor;
			this.patient = patient;
		}
		public appointment() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
