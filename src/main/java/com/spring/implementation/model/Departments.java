package com.spring.implementation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Departments {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    Long departmentId;


	@Column(name = "department_name")
    String departmentName;


	@Column(name = "location_id")
    Long locationId;
}
