package com.project.eudocare.admin.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.doctorRequestRepo;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.doctorRequest;

@Service
public class adminServiceDoctor 
{
  @Autowired
  doctor_dao doc;
  @Autowired
  doctorRequestRepo dr;
  @Autowired
  private MailSender mailSender;
  public doctor getDoctorinfo(Long id)
  {
	  return doc.getById(id);
  }
  public void updateDoctor(doctor updatedDoctor) {
      doctor existingDoctor = doc.findById(updatedDoctor.getId())
              .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + updatedDoctor.getId()));
      existingDoctor.setName(updatedDoctor.getName());
      existingDoctor.setGender(updatedDoctor.getGender());
      existingDoctor.setDateOfBirth(updatedDoctor.getDateOfBirth());
      existingDoctor.setAddress(updatedDoctor.getAddress());
      existingDoctor.setLocation(updatedDoctor.getLocation());
      existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
      existingDoctor.setLicenseNumber(updatedDoctor.getLicenseNumber());
      existingDoctor.setExperienceYears(updatedDoctor.getExperienceYears());
      existingDoctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
      doc.save(existingDoctor);
      sendRequestProcessedMail(existingDoctor.getEmail(), existingDoctor.getName());
      deleteRequest(existingDoctor);
      
  }
  public void deleteRequest(doctor d)
  {
	  List<doctorRequest> li=dr.findByDoctorId(d);
	  for(doctorRequest dc:li)
	  {
		  dr.delete(dc);
	  }
	  sendRequestProcessedMail(d.getEmail(), d.getName());
  }
  public void sendRequestProcessedMail(String toEmail, String doctorName) {
     try { String subject = "Your Request Has Been Processed";
      String message = "Dear Dr. " + doctorName + ",\n\n" +
                       "Your request has been successfully processed by the admin.\n\n" +
                       "Thank you,\nHospital Admin";

      SimpleMailMessage mail = new SimpleMailMessage();
      mail.setTo(toEmail);
      mail.setSubject(subject);
      mail.setText(message);
      mail.setFrom("midhunmidhunkrishna262001@gmail.com");

      mailSender.send(mail);
     }
     catch (Exception e) {
		System.out.println(e);
	}
  }
}
