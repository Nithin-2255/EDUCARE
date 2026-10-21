package com.project.eudocare.registration.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.OTPRepository;
import com.project.eudocare.registration.entity.OTP;

@Service
public class OTPService 
{
  @Autowired
  private JavaMailSender mailSender;
  @Autowired 
  private OTPRepository otpRepository;
  
  public String generateOtp(String email)
  {
	  String otp=generateRandomOtp();
	  OTP otpEntity= new OTP(email, otp, LocalDateTime.now().plusMinutes(5));
	  otpRepository.save(otpEntity);
	  sendOtpEmail(email, otp);
	  return otp;
  }
  
  private String generateRandomOtp()
  {
	  SecureRandom random= new SecureRandom();
	  return String.format("%06d",random.nextInt(999999));
  }
  private void sendOtpEmail(String email,String otp)
  {
	  try {
	  SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("midhunkrishna262001@gmail.com");
      message.setTo(email);
      message.setSubject("Password Change OTP");
      message.setText("Your OTP for password change is: "+otp+ ",\n\nThank you!\nEudocare Team");
      mailSender.send(message);
  }
	  catch (Exception e) {
		System.out.println(otp);
	}
}}
