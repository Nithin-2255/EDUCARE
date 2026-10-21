package com.project.eudocare.admin.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.project.eudocare.admin.dashboard.service.adminService;
@RequestMapping("admin")
@RestController
public class adminController 
{
	@Autowired
	private adminService as;
	@GetMapping("doctor/delete/{id}")
    public RedirectView deleteDoctor(@PathVariable("id") String id)
    {
    	Map<String,String> map=as.deleteDoctor(Long.parseLong(id));
    	as.sendDocterRemovalEmail(map);
    	return new RedirectView("/admin/doctor");
    }
	@GetMapping("doctor/verify/{id}")
	public RedirectView accpectDoctor(@PathVariable("id") String id)
	{
		as.verifyServic(Long.parseLong(id));
		return new RedirectView("/admin/doctor/getProfileDetails/"+id);
	}
	
	@GetMapping("doctor/getProfileDetails/{id}")
	public ModelAndView viewDocter(@PathVariable("id") Long id)
	{
		return new ModelAndView("doctorShow").addObject("data",as.getDocter(id)).addObject("list",as.getDocterinfo(as.getDocter(id)));
	}
}
