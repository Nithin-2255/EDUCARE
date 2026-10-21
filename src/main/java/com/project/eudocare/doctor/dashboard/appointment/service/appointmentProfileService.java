package com.project.eudocare.doctor.dashboard.appointment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.PrescriptionRepo;
import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.dao.login_db;
import com.project.eudocare.registration.entity.Prescription;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;

@Service
public class appointmentProfileService 
{
	@Autowired
    private dataBase p;
	@Autowired
	private appointmentDao ad;
	 @Autowired
	 private JavaMailSender mailSender;
	 @Autowired
	 private PrescriptionRepo preRepo;
	 @Autowired
	 private doctor_dao doc;
	 
	 public doctor getAppointmentdoctor(Long id)
	   {
		   return doc.getById(id); 
	   }
   public patient getAppointmentdetail(Long id)
   {
	   return p.getById(id); 
   }
   public List<Prescription> getPrescription(patient p)
   {
	   return preRepo.findByPatient(p);
   }
   public Map<String, String> deleteAppointment(Long id)
   {
	  appointment app= ad.getById(id);
	  Map<String,String> map= new HashMap<String, String>();
	  map.put("email",app.getPatient().getEmail());
	  map.put("time",app.getTime()+"");
	  map.put("date",app.getDate()+"");
	  map.put("pname",app.getPatient().getPatientName());
	  map.put("dname",app.getDoctor().getName());
	  map.put("dId",app.getDoctor().getId()+"");
	  ad.delete(app);
	  return map;
   }
 
   public String sendCancelemail(Map<String,String> map)
   {
   	String subject = "Appointment Cancaled";
       String message = "Dear " +map.get("pname") + ",\n\n"
               + "I regret to inform you that your scheduled appointment on "+map.get("date")+ " at "+map.get("time") +" has been canceled."+"\n\n"
             + "We sincerely apologize for any inconvenience this may cause. If you would like to reschedule, please feel free use our our website."+ ",\n\nThank you!\nEudocare Team";
       SimpleMailMessage email = new SimpleMailMessage();
       email.setFrom("midhunkrishna262001@gmail.com");
       email.setTo(map.get("email"));
       email.setSubject(subject);
       email.setText(message);
       mailSender.send(email);
       return map.get("dId");
   }
}
