package com.project.eudocare.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.eudocare.admin.service.admindashboardService;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;

@RestController
@RequestMapping("admin")
public class adminDashboardController 
{
   @Autowired
   private admindashboardService ads;
   @GetMapping("doctor")
   public ModelAndView doctorInfo()
   {
	   Map<String, Object> map=ads.getCount();
	   map.put("isPatient",false);
	   map.put("isDocter",true);
	   map.put("isApp",false);
	   List<doctor> list=ads.doctorDetails();
	   return new ModelAndView("adminDashboard").addObject("list",list).addObject("count",map);
   }
   @GetMapping("change")
   public ModelAndView appointmentInfo()
   {
	   Map<String, Object> map=ads.getCount();
	   map.put("isPatient",false);
	   map.put("isDocter",false);
	   map.put("isApp",true);
	   return new ModelAndView("adminDashboard").addObject("list", ads.changeDetails()).addObject("count",map);
   }
   @GetMapping("patient")
   public ModelAndView patientInfo()
   {
	   Map<String, Object> map=ads.getCount();
	   map.put("isPatient",true);
	   map.put("isDocter",false);
	   map.put("isApp",false);
	   return new ModelAndView("adminDashboard").addObject("list", ads.patientDetails()).addObject("count",map);
   }
   
}
