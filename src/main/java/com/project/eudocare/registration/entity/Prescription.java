package com.project.eudocare.registration.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Prescription 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @ManyToOne
	    private patient patient;
	    @ManyToOne
	    private doctor Doctor;
	    private String medicalReport;
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Medication> medications = new ArrayList<>();
        public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getMedicalReport() {
			return medicalReport;
		}
		public void setMedicalReport(String medicalReport) {
			this.medicalReport = medicalReport;
		}
		public List<Medication> getMedications() {
			return medications;
		}
		public void setMedications(List<Medication> medications) {
			this.medications = medications;
		}
		
		public doctor getDoctor() {
			return Doctor;
		}
		public void setDoctor(doctor doctor) {
			Doctor = doctor;
		}
		
		public patient getPatient() {
			return patient;
		}
		public void setPatient(patient patient) {
			this.patient = patient;
		}
		public Prescription(com.project.eudocare.registration.entity.patient patient, doctor doctor,
				String medicalReport, List<Medication> medications) {
			super();
			this.patient = patient;
			Doctor = doctor;
			this.medicalReport = medicalReport;
			this.medications = medications;
		}
		public Prescription() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
	   
}
