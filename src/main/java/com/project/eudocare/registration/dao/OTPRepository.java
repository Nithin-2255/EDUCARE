package com.project.eudocare.registration.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.eudocare.registration.entity.OTP;
@Repository
public interface OTPRepository extends JpaRepository<OTP,Long> 
{
  OTP findByEmailAndOtp(String email,String otp);
  OTP findByEmail(String email);
}
