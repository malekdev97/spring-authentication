package com.malek.app.rest.Repository;

import com.malek.app.rest.Model.Student;
import com.malek.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

}
