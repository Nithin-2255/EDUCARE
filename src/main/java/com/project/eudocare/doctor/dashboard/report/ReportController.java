package com.project.eudocare.doctor.dashboard.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.project.eudocare.doctor.dashboard.report.service.ReportService;
import com.project.eudocare.registration.entity.Medication;
import com.project.eudocare.registration.entity.Prescription;

@Controller
@RequestMapping("/patient")
public class ReportController 
{  
	@Autowired
	ReportService reportService;
	
	@GetMapping("/report/{pid}/{did}")
   public String getReportpage(@PathVariable("pid") Long pid,@PathVariable("did") Long did, Model m)
   {
	   m.addAttribute("data",reportService.getDoctor(did));
	   m.addAttribute("pdata",reportService.getpatient(pid));
	   
	   return "report";
   }
	@PostMapping("/save-report")
	public RedirectView saveReport(@ModelAttribute Prescription prescription,@RequestParam("pid") String pid,@RequestParam("did") String did)
	{
		reportService.saveReport(prescription, Long.parseLong(pid), Long.parseLong(did));
		String link="/appointmentShow/"+did;
		return new RedirectView(link);
	}
}
