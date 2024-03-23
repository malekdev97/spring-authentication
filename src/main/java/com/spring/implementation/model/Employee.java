package com.spring.implementation.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Employee {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String name;

    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "job_id")
    private Long jobId;
    private int salary;
    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "department_id")
    private Long departmentId;
}
