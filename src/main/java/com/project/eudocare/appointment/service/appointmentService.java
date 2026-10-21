package com.project.eudocare.appointment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;

@Service
public class appointmentService 
{
    @Autowired
    private doctor_dao doc;
    @Autowired
    private dataBase pat;
    @Autowired
    private appointmentDao book;
    @Autowired
    private JavaMailSender mailSender;
    
    public Map<String, Object> getInfo(Long docId,Long pId)
    {
    	Map<String,Object> data= new HashMap();
    	data.put("doc",doc.getById(docId));
    	data.put("pat",pat.getById(pId));
    	return data;
    }
    public Map<String,String> booking(Long pid,Long did,String date,String time)
    {
    	patient p= pat.getById(pid);
    	doctor d=doc.getById(did);
        appointment app= new appointment(LocalDate.parse(date),LocalTime.parse(time), d, p);
    	book.save(app);
    	Map<String,String> map= new HashMap<String, String>();
    	map.put("date",date );
    	map.put("time", time);
    	map.put("email",p.getEmail());
    	map.put("address",d.getAddress());
    	map.put("name",p.getPatientName());
    	map.put("dname",d.getName());
    	map.put("pid",""+p.getId());
    	return map;
    }
    public patient sendAppointmentEmail(Map<String,String> map)
    {
    	try
    	{
    	String subject = "Appointment Confirmation";
        String message = "Dear " +map.get("name") + ",\n\n"
                + "Your appointment is confirmed with the "+map.get("dname")+" on "+map.get("date") + " at " + map.get("time") + ".\n\n"
              + "Address : "+map.get("address") + ",\n\nThank you!\nEudocare Team";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("midhunkrishna262001@gmail.com");
        email.setTo(map.get("email"));
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    	return pat.getById(Long.parseLong(map.get("pid")));
    	}
    	catch (Exception e) {
			return pat.getById(Long.parseLong(map.get("pid")));
		}
    }

}
