package com.project.eudocare.appointment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.eudocare.appointment.service.appointmentService;
import com.project.eudocare.appointment.service.searchService;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;

@Controller
public class appointmentController 
{
	@Autowired
	private appointmentService as;
	@Autowired
	private searchService s;
	
	@GetMapping("/appointment")
   public ModelAndView getAppointment(@RequestParam("did") Long dId ,@RequestParam("pid") Long pId)
   {
	   Map<String,Object> map=as.getInfo(dId,pId);
	   doctor doc=(doctor) map.get("doc");
	   patient pat=(patient) map.get("pat");
	   return new ModelAndView("appointment").addObject("doc",doc).addObject("pat",pat);
   }
	@PostMapping("/book")
	public ModelAndView bootingAppointment(@RequestParam("pid") String pid,@RequestParam("did") String did,@RequestParam("date") String date,@RequestParam("time") String time)
	{
		Map<String, String> map= as.booking(Long.parseLong(pid),Long.parseLong(did),date,time);
		
		return new ModelAndView("patient_profile").addObject("data",as.sendAppointmentEmail(map));
      
	}
	@GetMapping("/search")
	public String searchDoctors(
	        @RequestParam(required = false,name="location") String location,
	        @RequestParam(required = false, name ="specialty") String specialization,
	        @RequestParam("pid") Long id,
	        Model model) {

	    List<doctor> doctors = s.searchDoctors(location, specialization);
        model.addAttribute("data",s.patientInfo(id));
	    model.addAttribute("doc", doctors);
	    model.addAttribute("loc", s.getBylocation());
	    model.addAttribute("spe", s.getByspecialization());

	    return "patient_profile";
	}

	
}
