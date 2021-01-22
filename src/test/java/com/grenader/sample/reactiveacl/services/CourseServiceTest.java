package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Course;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseServiceTest {

    @Autowired
    private SchoolService service;

    private static Course expectedCourse;
    private static String expectedCourseId;

    @BeforeEach
    void setUp() {
        expectedCourse = Course.builder().name("Math").description("Math Desc").build();
        service.addCourse(expectedCourse);

        expectedCourseId = expectedCourse.getId();
        System.out.println("expectedCustomerId = " + expectedCourseId);
    }

    @Test
    void testGetCourseById_and_delete() {
        final Course course = service.getCourseById(expectedCourseId);
        assertEquals(expectedCourse, course);
    }

    @Test
    void testGetCourseByName() {
        final Course course = service.getCourseByName("Math");
        assertEquals(expectedCourse, course);

        assertNull(service.getCourseByName("random-name"));
    }

    @AfterEach
    void tearDown()
    {
        service.deleteCourse(expectedCourseId);
        assertNull(service.getCourseById(expectedCourseId));
    }

}