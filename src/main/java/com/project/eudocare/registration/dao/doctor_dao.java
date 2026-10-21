package com.project.eudocare.registration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.doctor;
@Repository
public interface doctor_dao extends JpaRepository<doctor, Long> 
{
	@Query("SELECT DISTINCT d.location FROM doctor d")
	List<String> findDistinctLocations();
	
	@Query("SELECT DISTINCT d.specialization FROM doctor d")
	List<String> findDistinctSpecializations();


     List<doctor> findByLocation(String location);
     
     List<doctor> findBySpecialization(String specialization);

     List<doctor> findByLocationAndSpecialization(String location, String specialization);
}
