package com.project.eudocare.login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.eudocare.login.service.loginService;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;
import com.project.eudocare.registration.entity.user;

import jakarta.servlet.http.HttpSession;
@RestController
public class loginController 
{
	@Autowired
	loginService s;
	@PostMapping("/login")
	   public ModelAndView login(@RequestParam("user") String user,@RequestParam("password") String pass ,@RequestParam("role") String role,HttpSession session)
	   {
		  if(role.equals("doctor"))
		  {
			  user p=s.login_check(user, pass);
			  
			if(p!=null&&p.getRole().equals("doctor"))
			  {
				session.setAttribute("data", p.getDid());
				 return new ModelAndView("redirect:/profile");
				  
			  }
			 else if(p!=null&&p.getRole().equals("admin"))
			  {
				 session.setAttribute("data",s.adminData());
				 return new ModelAndView("redirect:/profile");
			  }
			  else
			  {
				  
				  return new ModelAndView("doctor_login").addObject("error","wrong email or password");
			  }
		  }
		  else
		  {
			user p=s.login_check(user, pass);
		  if(p!=null&&p.getRole().equals("patient"))
		  {
			  session.setAttribute("data", p.getPid());
			  return new ModelAndView("redirect:/profile");
		  }
		  else if(p!=null&&p.getRole().equals("admin"))
		  {
			  session.setAttribute("data",s.adminData());
			  return new ModelAndView("redirect:/profile");		  }
		  else
		  {
			  
			  return new ModelAndView("login_patient").addObject("error","wrong email or password");
		  }
		  }		  
	   }
	@GetMapping("/profile")
	public ModelAndView getProfile(HttpSession session)
	{
		if(session.getAttribute("data") instanceof doctor)
		{
		 doctor d=(doctor)session.getAttribute("data");
		 return new ModelAndView("doctorDashboard").addObject("data",d).addObject("list",s.getAppointmentdetails(d));
		}
		else if(session.getAttribute("data") instanceof patient)
		{
			patient p=(patient)session.getAttribute("data");
			return new ModelAndView("patient_profile").addObject("data",p).addObject("doc",s.doctorDeatial(p.getLocation())).addObject("loc",s.getBylocation()).addObject("spe", s.getByspecialization());	
		}
		else
		{
			Map<String,Object> map=(Map<String, Object>) session.getAttribute("data");
			return new ModelAndView("adminDashboard").addObject("count",map);
		}
	}
	@GetMapping("/logout/{role}")
	public ModelAndView logout(HttpSession session,@PathVariable("role") String role)
	{
		session.invalidate();
		if(role.equals("docter"))
		{
			return new ModelAndView("doctor_login");

		}
		else
		{
			return new ModelAndView("login_patient");

		}
	}
}
