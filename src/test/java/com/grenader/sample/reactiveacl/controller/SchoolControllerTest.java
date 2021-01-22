package com.grenader.sample.reactiveacl.controller;

import com.grenader.sample.reactiveacl.model.Course;
import com.grenader.sample.reactiveacl.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SchoolControllerTest {

    @Autowired
    private SchoolController controller;

    @Test
    void testGetStudent() {
        final Student originalStudent = Student.builder().firstName("Jason").lastName("Smith").courseIds(List.of("courseId_1", "courseId_2")).build();

        final Student student = controller.postStudent(originalStudent);
        String courseId = student.getId();

        final Student studentFromDB = controller.getStudent(courseId);

        assertEquals(originalStudent.getFirstName(), studentFromDB.getFirstName());
        assertEquals(originalStudent.getLastName(), studentFromDB.getLastName());
        assertThat(originalStudent.getCourseIds(), is(equalTo(studentFromDB.getCourseIds())));
    }

    @Test
    void testGetCourse() {
        final Course firstCourse = Course.builder().name("Geography").description("Geography description").build();
        final Course secondCourse = Course.builder().name("Physics").description("Physics description").build();

        final Course loadedCourse = controller.postCourse(firstCourse);
        controller.postCourse(secondCourse);
        String courseId = loadedCourse.getId();

        final Course courseFromDB = controller.getCourse(courseId);

        assertEquals(firstCourse.getName(), courseFromDB.getName());
        assertEquals(firstCourse.getDescription(), courseFromDB.getDescription());
    }
}