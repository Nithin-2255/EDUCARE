package com.project.eudocare.registration.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.eudocare.registration.entity.user;
import com.project.eudocare.registration.entity.doctor;
import com.project.eudocare.registration.entity.patient;
import com.project.eudocare.registration.service.service;

@Controller
public class controller 
{
   @Autowired	
    private service s;
   @PostMapping("/reg")
   public ModelAndView register(@RequestParam("patientname") String name ,@RequestParam("gender") String gender,@RequestParam("patientage") int age,@RequestParam("bloodgroup") String bg,@RequestParam("location") String loc,@RequestParam("phoneno") String phno,@RequestParam("address") String add,@RequestParam("relativename") String rname,@RequestParam("relativephone") String rph,@RequestParam("relation") String rela, @RequestParam("email") String email,@RequestParam("password")String pass
		   ,@RequestParam("file") MultipartFile file) throws IOException
   {
	   patient p=new patient(name, gender, age, bg, add, loc, phno, rname, rph, rela, email, pass);
	   s.register_DB(p,file);
       return new ModelAndView("login_patient");
   }
   
  
     @GetMapping("download/{id}")
	 public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) 
     { 
	     byte[] imageData = s.getImageById(id);
	     if (imageData != null) {
	         return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }
	 @PostMapping("/docreg")
	 public String doctor_register(@RequestParam("name") String name,
	            @RequestParam("dateOfBirth")LocalDate dateOfBirth,
	            @RequestParam("gender") String gender,
	            @RequestParam("specialization") String specialization,
	            @RequestParam("licenseNumber") String licenseNumber,
	            @RequestParam("experienceYears") int experienceYears,
	            @RequestParam("phoneNumber") String phoneNumber,
	            @RequestParam("email") String email,
	            @RequestParam("address") String address,
	            @RequestParam("location") String location,
	            @RequestParam("confirmPassword") String confirmPassword,
	            @RequestParam("img") MultipartFile file) throws IOException
	 {
	    doctor doc=new doctor(name, email, phoneNumber, specialization, licenseNumber, experienceYears, address, gender, location, dateOfBirth,confirmPassword,"pending");
		 return s.register_doctor(doc,file);
	 }
	 @GetMapping("/forgetpage")
	 public ModelAndView forgetPassword()
	 {
		 Map<String,String> map=new HashMap<String, String>();
		 map.put("email","true");
		 map.put("otp",null);
		 return new ModelAndView("forgetPassword").addObject("data",map);
	 }  
}
