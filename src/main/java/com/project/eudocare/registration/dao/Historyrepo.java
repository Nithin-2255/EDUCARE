package com.project.eudocare.registration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.History;
import com.project.eudocare.registration.entity.doctor;
@Repository
public interface Historyrepo extends JpaRepository<History,Long> 
{
     List<History> findByDoctor(doctor d);
}
