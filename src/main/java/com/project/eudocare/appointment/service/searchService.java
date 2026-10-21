package com.project.eudocare.appointment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;

@Service
public class searchService
{
	@Autowired
	private doctor_dao doctorRepository;
	@Autowired
	private dataBase p;
	
	public List<doctor> searchDoctors(String location, String specialization) {
	    boolean hasLocation = location != null && !location.isBlank();
	    boolean hasSpecialization = specialization != null && !specialization.isBlank();

	    if (hasLocation && hasSpecialization) {
	        return doctorRepository.findByLocationAndSpecialization(location, specialization);
	    } else if (hasLocation) {
	        return doctorRepository.findByLocation(location);
	    } else if (hasSpecialization) {
	        return doctorRepository.findBySpecialization(specialization);
	    } else {
	        return new ArrayList<>(); // or return all doctors if desired
	    }
	}
	 public List<String> getBylocation()
	 {
		 return doctorRepository.findDistinctLocations();
	 }
	 public List<String> getByspecialization()
	 {
		 return doctorRepository.findDistinctSpecializations();
	 }
	 public patient patientInfo(Long id)
	 {
		 return p.getById(id);
	 }
}
