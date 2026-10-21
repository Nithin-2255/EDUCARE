package com.project.eudocare.registration.service;

import java.io.IOException;
import java.util.Optional;

import com.project.eudocare.registration.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.project.eudocare.registration.dao.dataBase;
import com.project.eudocare.registration.dao.doctor_dao;
import com.project.eudocare.registration.dao.imageDao;
import com.project.eudocare.registration.dao.login_db;
import com.project.eudocare.registration.entity.user;
import com.project.eudocare.registration.entity.patient;
@Service
public class service 
{
    @Autowired 
	private dataBase db;
    @Autowired
    private login_db lg;
    @Autowired
    private imageDao im;
    @Autowired
    private doctor_dao d;
    public int register_DB(patient  p,MultipartFile file) throws IOException
    {
        image img = new image();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setData(file.getBytes());
        p.setImgId(img);
        im.save(img);
    	user log=new user(p.getEmail(),p.getPassword(),"patient");
    	log.setPid(p);
    	db.save(p);
    	lg.save(log);
    	
    	return 1;
    }
    public byte[] getImageById(int id) {
        Optional<image> b=im.findById(id);
         return b.get().getData();
      }
    public String register_doctor(doctor doc,MultipartFile file) throws IOException
    {
    	 image img = new image();
         img.setName(file.getOriginalFilename());
         img.setType(file.getContentType());
         img.setData(file.getBytes());
         doc.setImg(img);
         im.save(img);
         d.save(doc);
         return "doctor_login";
    }

}
