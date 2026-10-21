package com.project.eudocare.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctorRequestRepo;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.dao.login_db;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;
import com.project.eudocare.registration.entity.user;
@Service
public class loginService 
{
	@Autowired
	private login_db userdao;
	@Autowired
	private doctor_dao doc;
	@Autowired
	private appointmentDao app;
	@Autowired
	private dataBase p;
	@Autowired
	private doctorRequestRepo dr;
	
	 public user login_check(String user,String pass)
	    {
	    	user ur=userdao.findByUsernameAndPassword(user,pass);
	    	if(ur!=null)
	    	{
	    		return ur;
	    	}
	    	else
	    	{
	    		return null;
	    	}
	    }
	 
	 public List<doctor> doctorDeatial(String loc)
	 {
		 return  doc.findByLocation(loc);
	 }
	 public  List<appointment> getAppointmentdetails(doctor doc)
	 {
		 return app.findByDoctor(doc);
	 }
	 public Map<String, Object> adminData()
	 {
	            Map<String, Object> map=new HashMap<String, Object>();
		    	map.put("pCount",p.count());
		    	map.put("dCount",doc.count());
		    	map.put("aCount",dr.count());
		    	return map;
	 }
	 public List<String> getBylocation()
	 {
		 return doc.findDistinctLocations();
	 }
	 public List<String> getByspecialization()
	 {
		 return doc.findDistinctSpecializations();
	 }
}
