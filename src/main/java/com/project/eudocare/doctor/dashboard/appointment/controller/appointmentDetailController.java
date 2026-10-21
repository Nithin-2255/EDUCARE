package com.project.eudocare.doctor.dashboard.appointment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.eudocare.doctor.dashboard.appointment.service.appointmentProfileService;
import com.project.eudocare.registration.entity.patient;


@RestController
@RequestMapping("/appointmentShow")
public class appointmentDetailController 
{   @Autowired
	private appointmentProfileService aps;

	@GetMapping("/getProfileDetails/{id}/{did}")
   public  ModelAndView viewProfile(@PathVariable("id") String id, @PathVariable("did") String did)
   {
		patient p=aps.getAppointmentdetail(Long.parseLong(id));
		
	   return new ModelAndView("profileAppointment").addObject("data",p)
			   .addObject("report",aps.getPrescription(p))
			   .addObject("doc",aps.getAppointmentdoctor(Long.parseLong(did)));
   }
	@GetMapping("/reports/{id}/{did}")
	 public  ModelAndView viewProfilePatient(@PathVariable("id") String id, @PathVariable("did") String did)
	   {
			patient p=aps.getAppointmentdetail(Long.parseLong(id));
			
		   return new ModelAndView("doctor-patientHistory").addObject("data",p)
				   .addObject("report",aps.getPrescription(p))
				   .addObject("doc",aps.getAppointmentdoctor(Long.parseLong(did)));
	   }
		
	   @GetMapping("deleteAppointment/{id}")
	   public  RedirectView deleteApointment(@PathVariable("id") String id)
	   {
		   Map<String, String> map=aps.deleteAppointment(Long.parseLong(id));
		   String link="/appointmentShow/"+aps.sendCancelemail(map);
		   	 return new RedirectView(link);
		}
}
