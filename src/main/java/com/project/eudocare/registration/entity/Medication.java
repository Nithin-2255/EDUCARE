package com.project.eudocare.registration.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medication 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
        private String medicationName;
        @ElementCollection
	    private List<String> timesToConsume;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getMedicationName() {
			return medicationName;
		}
		public void setMedicationName(String medicationName) {
			this.medicationName = medicationName;
		}
		public List<String> getTimesToConsume() {
			return timesToConsume;
		}
		public void setTimesToConsume(List<String> timesToConsume) {
			this.timesToConsume = timesToConsume;
		}

}
