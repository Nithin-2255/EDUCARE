package com.project.eudocare.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctorRequestRepo;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.entity.History;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.doctorRequest;
import com.project.eudocare.registration.entity.patient;
@Service
public class admindashboardService 
{
	@Autowired
	private dataBase p;
	@Autowired
	private doctor_dao d;
	@Autowired
	private appointmentDao a;
	@Autowired
	private doctorRequestRepo dr;
   public List<patient> patientDetails()
    {
    	List<patient>list=p.findAll();
    	return list;
    }
   public List<doctor> doctorDetails()
    {
    	List<doctor>list=d.findAll();
    	return list;
    }
   public List<doctorRequest> changeDetails()
    {
    	List<doctorRequest>list=dr.findAll();
    	return list;
    }
    public Map<String, Object> getCount()
    {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("pCount",p.count());
    	map.put("dCount",d.count());
    	map.put("aCount",dr.count());
    	return map;
    }
    
   
}
