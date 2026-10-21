package com.project.eudocare.registration.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class patient {

    public patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String gender;
    private int patientAge;
    private String bloodGroup;
    private String address;
    private String location;
    private String phoneNo;
    private String relativeName;
    private String relativePhone;
    private String relation;
    private String email;
    private String password;
    @OneToOne
    private image imgId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public image getImgId() {
		return imgId;
	}
	public void setImgId(image imgId) {
		this.imgId = imgId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativePhone() {
		return relativePhone;
	}
	public void setRelativePhone(String relativePhone) {
		this.relativePhone = relativePhone;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public patient(String patientName, String gender, int patientAge, String bloodGroup, String address,
			String location, String phoneNo, String relativeName, String relativePhone, String relation, String email,
			String password) {
		super();
		this.patientName = patientName;
		this.gender = gender;
		this.patientAge = patientAge;
		this.bloodGroup = bloodGroup;
		this.address = address;
		this.location = location;
		this.phoneNo = phoneNo;
		this.relativeName = relativeName;
		this.relativePhone = relativePhone;
		this.relation = relation;
		this.email = email;
		this.password = password;
	}
    
}
