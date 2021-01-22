package com.grenader.sample.reactiveacl.controller;


import com.grenader.sample.reactiveacl.model.Course;
import com.grenader.sample.reactiveacl.model.Student;
import com.grenader.sample.reactiveacl.services.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SchoolController {

    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @GetMapping("/student/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable("id") String studentId) {
        return service.getStudentById(studentId);
    }

    @PostMapping("/student")
    @ResponseBody
    public Student postStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping("/course/{id}")
    @ResponseBody
    public Course getCourse(@PathVariable("id") String courseId) {
        return service.getCourseById(courseId);
    }

    @PostMapping("/course")
    @ResponseBody
    public Course postCourse(@RequestBody Course course) {
        return service.addCourse(course);
    }

}