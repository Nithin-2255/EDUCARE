package com.project.eudocare.registration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.patient;
@Repository
public interface dataBase extends JpaRepository<patient,Long> {

}
