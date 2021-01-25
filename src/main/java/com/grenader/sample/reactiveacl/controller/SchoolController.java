package com.grenader.sample.reactiveacl.controller;


import com.grenader.sample.reactiveacl.model.Course;
import com.grenader.sample.reactiveacl.model.Student;
import com.grenader.sample.reactiveacl.services.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SchoolController {

    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @Operation(summary = "Get a Student object by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    @GetMapping("/student/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable("id") String studentId) {
        return service.getStudentById(studentId);
    }

    @Operation(summary = "Create a new Student object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The student is created",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Student data",
                    content = @Content)})
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