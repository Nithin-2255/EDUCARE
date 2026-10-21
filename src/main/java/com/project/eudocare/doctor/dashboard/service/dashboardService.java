package com.project.eudocare.doctor.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.Historyrepo;
import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.doctorRequestRepo;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.entity.History;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.doctorRequest;

@Service
public class dashboardService 
{
   @Autowired
   private appointmentDao app;
   @Autowired
   private doctor_dao doc;
   @Autowired
   private doctorRequestRepo dr;
   @Autowired
   private Historyrepo hist;
   
   public List<appointment> getAppointment(String id)
   {
	   doctor d= doc.getById(Long.parseLong(id));
	   List<appointment> list=app.findByDoctor(d);
	   return list;
   }
   public void saveChange(Long id,String change)
   {
	   doctorRequest docq= new doctorRequest(doc.getById(id), change);
	   dr.save(docq);
   }
   public doctor getData(Long id)
   {
	   return doc.getById(id);
   }
   public List<History> listOfPatient(Long id)
   {
	   doctor d=doc.findById(id).get();
	   System.out.println(d.getName());
	   if(d!=null)
	   {
	   return hist.findByDoctor(d);
	   }
	   else
	   {
		   return null;
	   }
   }
}
