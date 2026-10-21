package com.project.eudocare.admin.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.dao.login_db;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;
import com.project.eudocare.registration.entity.user;

@Service
public class adminService 
{
	@Autowired
	private doctor_dao doc;
	@Autowired
	private login_db log;
	@Autowired
	private appointmentDao ap;
	@Autowired
	private MailSender mailSender;

    public Map<String, String> deleteDoctor(Long id)
    {
    	doctor d=doc.getById(id);
    	Map<String,String> map= new HashMap<String, String>();
    	map.put("name",d.getName());
    	map.put("email",d.getEmail());
    	user u=log.findByUsername(d.getEmail());
    	log.delete(u);
    	List<appointment> app=ap.findByDoctor(d);
        for(appointment li : app)
        {
        	ap.delete(li);
        }
        
    	doc.delete(d);
    	return map;
    }
    public Long verifyServic(Long id)
    {
    	doctor d=doc.getById(id);
    	d.setStatus("approved");
    	user u=new user(d.getEmail(),d.getPassword(),"doctor");
    	u.setDid(d);
    	doc.save(d);
    	log.save(u);
    	return id;
    }
    
    public String sendDocterRemovalEmail(Map<String,String> map)
    {
    	try
    	{

        	String subject = "Account Removal Confirmation";
            String message = "Dear Dr." +map.get("name") + ",\n\n"
                    + "We hope you are doing well"+ ".\n\n"
                  + "This is to inform you that your account has been removed from our system as per the recent update. If you have any questions or require any information regarding this process, please feel free to reach out to us. "+ ",\n\nWarm regards,\nEudocare Team";
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("midhunkrishna262001@gmail.com");
            email.setTo(map.get("email"));
            email.setSubject(subject);
            email.setText(message);
            mailSender.send(email);
        	
    	}
    	catch (Exception e) {
			System.out.println(e);
		}
    	return "";
    }
    public doctor getDocter(Long id)
    {
    	return doc.getById(id);
    }
    public List<appointment> getDocterinfo(doctor d)
    {
    	return ap.findByDoctor(d);
    }
}
