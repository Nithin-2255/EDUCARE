package com.project.eudocare.registration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.Prescription;
import com.project.eudocare.registration.entity.patient;
@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription,Long> 
{     
	
	List<Prescription> findByPatient(patient P);

}
