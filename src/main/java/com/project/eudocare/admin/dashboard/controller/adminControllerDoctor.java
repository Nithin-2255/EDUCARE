package com.project.eudocare.admin.dashboard.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.project.eudocare.admin.dashboard.service.adminServiceDoctor;
import com.project.eudocare.registration.entity.doctor;
@Controller
@RequestMapping("/admin")
public class adminControllerDoctor 
{
	@Autowired
	adminServiceDoctor asd;
	
	@GetMapping("/doctor/detail/{did}")
	public String editPageload(@PathVariable("did") Long id,Model m)
	{
		m.addAttribute("data",asd.getDoctorinfo(id));
		return "detailDoctor";
	}
	@PostMapping("/doctor/edit")
	public RedirectView editDoctor(
	        @RequestParam("id") Long id,
	        @RequestParam("name") String name,
	        @RequestParam("gender") String gender,
	        @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
	        @RequestParam("address") String address,
	        @RequestParam("location") String location,
	        @RequestParam("specialization") String specialization,
	        @RequestParam("licenseNumber") String licenseNumber,
	        @RequestParam("experienceYears") int experienceYears,
	        @RequestParam("phoneNumber") String phoneNumber)
	{
		doctor doc=new doctor(name, null, phoneNumber, specialization, licenseNumber, experienceYears, address, gender, location, dateOfBirth, null,null);
		doc.setId(id);
		asd.updateDoctor(doc);
		return new RedirectView("/admin/change");
	}
}
