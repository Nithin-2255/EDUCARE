package com.project.eudocare.doctor.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.eudocare.doctor.dashboard.service.dashboardService;
import com.project.eudocare.registration.entity.History;
import com.project.eudocare.registration.entity.doctorRequest;

@Controller
public class dashboardController 
{
	@Autowired
	private dashboardService ds;
	@RequestMapping("/appointmentShow/{id}")
    public  ModelAndView appointmentInfo(@PathVariable("id") String id)
    {
		
		return new ModelAndView("appointmentShow").addObject("list",ds.getAppointment(id));
    }
	@GetMapping("/change-request/{id}")
	public String changePage(@PathVariable("id") String id,Model m)
	{
		m.addAttribute("data",ds.getData(Long.parseLong(id)));
		return "changeReq";
	}
	@GetMapping("/patient/{id}")
	public String patientShow(@PathVariable("id") String id,Model m)
	{
		System.out.println(id);
		List<History> li=ds.listOfPatient(Long.parseLong(id));
		if(li!=null)
		{
		m.addAttribute("list",li );
		return "doctor-patientShow";
		}
		else
		{
			return null;
		}
	}
	@PostMapping("/change-submit")
	public String submitRequest(@RequestParam("id") String id,@RequestParam("changeReason")String change)
	{
		ds.saveChange(Long.parseLong(id),change);
		return "doctor_login";
	}
}
