package com.project.eudocare.registration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;
@Repository
public interface appointmentDao extends JpaRepository<appointment, Long>
{
    List<appointment> findByDoctor(doctor doc);
    List<appointment> findByPatient(patient p);
    appointment findByDoctorAndPatient(doctor d,patient p);
}
