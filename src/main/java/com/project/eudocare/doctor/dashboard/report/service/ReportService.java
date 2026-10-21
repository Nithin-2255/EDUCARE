package com.project.eudocare.doctor.dashboard.report.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.eudocare.registration.dao.Historyrepo;
import com.project.eudocare.registration.dao.PrescriptionRepo;
import com.project.eudocare.registration.dao.appointmentDao;
import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctorRequestRepo;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.entity.History;
import com.project.eudocare.registration.entity.Medication;
import com.project.eudocare.registration.entity.Prescription;
import com.project.eudocare.registration.entity.appointment;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;

@Service
public class ReportService 
{
	@Autowired
    PrescriptionRepo prepo;
	@Autowired
	dataBase db;
	@Autowired
	doctor_dao doc;
	@Autowired
    JavaMailSender mailSender;
	@Autowired
	Historyrepo histo;
	@Autowired
    appointmentDao app;
	
	public patient getpatient(Long id)
	{
		return db.getById(id);
	}
	public doctor getDoctor(Long id)
	{
		return doc.getById(id);
	}
	public String saveReport(Prescription p,Long pid,Long did)
	{
		patient pi=db.findById(pid).orElseThrow(() -> 
		new RuntimeException("patient not found with ID: " + pid));
		doctor d=doc.findById(did).orElseThrow(() ->
		new RuntimeException("Doctor not found with ID: " + did));
		p.setDoctor(d);
		p.setPatient(pi);
		removeAppointment(d, pi);
		History hi=new History(p, d, pi, new Date()+"");
		prepo.save(p);
		histo.save(hi);
		
		sendEmail(pi.getEmail(),p);
		return "saved";
	}
	public String buildReportContent(Prescription prescription) {
	    StringBuilder content = new StringBuilder();

	    content.append("Medical Report\n");
	    content.append("========================\n");
	    content.append("Patient: ").append(prescription.getPatient().getPatientName()).append("\n");
	    content.append("Doctor: ").append(prescription.getDoctor().getName()).append("\n");
	    content.append("Report: ").append(prescription.getMedicalReport()).append("\n\n");

	    content.append("Medications:\n");
	    for (Medication med : prescription.getMedications()) {
	        content.append("- ").append(med.getMedicationName()).append("\n");
	        content.append("  Times to Consume: ");
	        content.append(String.join(", ", med.getTimesToConsume()));
	        content.append("\n");
	    }

	    return content.toString();
	}

	public void sendEmail(String to, Prescription p) 
	{
		try
		{
        SimpleMailMessage message = new SimpleMailMessage();
        String body=buildReportContent(p);
        String subject="Your Medical Prescription";
        message.setFrom("midhunkrishna262001@gmail.com"); 
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
		}
		catch (Exception e) {
			System.out.println(e);
		}
    }
	public void removeAppointment(doctor d,patient p)
	{
		appointment appo=app.findByDoctorAndPatient(d, p);
		app.delete(appo);
	}
	
}
