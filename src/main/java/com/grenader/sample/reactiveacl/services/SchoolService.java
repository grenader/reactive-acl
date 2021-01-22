package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Course;
import com.grenader.sample.reactiveacl.model.Student;
import com.grenader.sample.reactiveacl.repository.CourseRepository;
import com.grenader.sample.reactiveacl.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public SchoolService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student getStudentById(String studentId) {
        final Optional<Student> studentOptional = studentRepository.findById(studentId);
        return studentOptional.orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> findStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(String courseId) {
        final Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.orElse(null);
    }

    public void deleteCourse(String courseId) {
        courseRepository.deleteById(courseId);
    }

    public Course getCourseByName(String name) {
        final List<Course> courseList = courseRepository.findByName(name);

        return !courseList.isEmpty() ? courseList.get(0) : null;

    }
}
