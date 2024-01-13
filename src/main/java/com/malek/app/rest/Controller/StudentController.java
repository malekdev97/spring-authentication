package com.malek.app.rest.Controller;

import com.malek.app.rest.Model.Student;
import com.malek.app.rest.Repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentRepository studentRepository;

    @GetMapping("/")
    public String hello() {

        return "Hello World";
    }
    @GetMapping("/getall")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("get/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentRepository.findById(id).get();
    }
    /*public Student getStudnet@PathVariable long id) {
        return studentRepository.findById(id).get();
    }*/

    @PostMapping("/save")
    public Student storeStudent(@RequestBody Student student) {
        System.out.println(student.getName());
        //studentRepository.save(student);
        return student;
    }
    @PutMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable long id, Student student) {
        Student currentStudent = studentRepository.findById(id).get();

        currentStudent.setName(student.getName());

        studentRepository.save(currentStudent);

        return "Student updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String destroyStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
        return "Student deleted";
    }

}
