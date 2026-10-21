package com.project.eudocare.registration.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.OTPRepository;
import com.project.eudocare.registration.dao.login_db;
import com.project.eudocare.registration.entity.OTP;
import com.project.eudocare.registration.entity.user;

@Service
public class passwordService 
{
   @Autowired
   private login_db u;
   @Autowired
   private OTPRepository o;
   
   public String emailCheck(String email)
   {
	   user User=u.findByUsername(email);
	   if(User!=null)
	   {
		   return "true";
	   }
	   else
	   {
		   return "invalided email! enter valid mail id";
	   }
   }
   public String otpCheck(String email, String otp,String newpassword)
   {
	   OTP otpEntity=o.findByEmailAndOtp(email,otp);
	   	if(otpEntity == null || otpEntity.getExpiryTime().isBefore(LocalDateTime.now()))
	   	{
	   		return null;
	   	}
	   	else
	   	{
	   		user User =u.findByUsername(email);
	   		User.setPassword(newpassword);
	   		String role=User.getRole();
	   		u.save(User);
	   		o.delete(otpEntity);
	   		if(role.equals("docter"))
	   		{
	   			return "doctor_login";
	   		}
	   		else
	   		{
	   			return "login_patient";
	   		}
	   		
	   	}
   }
}
