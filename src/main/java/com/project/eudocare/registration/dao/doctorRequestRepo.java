package com.project.eudocare.registration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.doctorRequest;
@Repository
public interface doctorRequestRepo extends JpaRepository<doctorRequest,Long>
{
    List<doctorRequest> findByDoctorId(doctor d);
}
