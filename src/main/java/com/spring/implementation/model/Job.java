package com.spring.implementation.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name = "job_title")
    String jobTitle;
}
