package com.malek.app.rest.Repository;

import com.malek.app.rest.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Long> {
}
