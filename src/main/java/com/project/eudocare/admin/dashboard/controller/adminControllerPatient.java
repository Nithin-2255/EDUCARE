package com.project.eudocare.admin.dashboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.project.eudocare.admin.dashboard.service.adminServicePatient;
import com.project.eudocare.registration.entity.patient;

@RequestMapping("admin")
@Controller
public class adminControllerPatient 
{  
	@Autowired
	private adminServicePatient asp;
	
	@GetMapping("patient/delete/{id}")
    public RedirectView deleteDoctor(@PathVariable("id") String id)
    {
    	Map<String,String> map=asp.deletePatient(Long.parseLong(id));
    	asp.sendPatientRemovalEmail(map);
    	return new RedirectView("/admin/patient");
    }

	@GetMapping("patient/getProfileDetails/{id}")
	public String viewDocter(@PathVariable("id") Long id ,Model m)
	{
		m.addAttribute("data",asp.getPatient(id)).addAttribute("list",asp.getPatientinfo(asp.getPatient(id)));
		return "patientShow";
	}
	
	@GetMapping("patient/detail/{pid}")
	public String editPageload(@PathVariable("pid") Long id,Model m)
	{
		m.addAttribute("data",asp.getPatient(id));
		return "detailPatient";
	}
	@PostMapping("patient/edit")
	public RedirectView editPatient(@RequestParam("id") Long id,@RequestParam("name") String name ,@RequestParam("gender") String gender,@RequestParam("age") int age,@RequestParam("bloodgroup") String bg,@RequestParam("location") String loc,@RequestParam("phone") String phno,@RequestParam("address") String add,@RequestParam("relativename") String rname,@RequestParam("relativephone") String rph,@RequestParam("relation") String rela, @RequestParam("email") String email)
			   
	{
		patient p=new patient(name, gender, age, bg, add, loc, phno, rname, rph, rela, email, null);
		p.setId(id);
		asp.updatePatient(p);
		return new RedirectView("/admin/patient");
	}
	

}