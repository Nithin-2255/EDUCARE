package com.project.eudocare.registration.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.user;
@Repository

public interface login_db extends JpaRepository<user,Long>
{
	 user findByUsername(String username);
	 user findByUsernameAndPassword(String username,String password);
}
