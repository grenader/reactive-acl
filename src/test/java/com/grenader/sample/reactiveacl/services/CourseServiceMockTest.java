package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Course;
import com.grenader.sample.reactiveacl.repository.CourseRepository;
import com.grenader.sample.reactiveacl.repository.StudentRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseServiceMockTest {

    private SchoolService service;

    private static Course expectedCourse;
    private static String expectedCourseId;

    private final StudentRepository studentRepository = mock(StudentRepository.class);

    private final CourseRepository courseRepository = mock(CourseRepository.class);

    @BeforeEach
    void setUp() {
        expectedCourse = Course.builder().name("Math").description("Math Desc").build();

        when(courseRepository.findById(nullable(String.class))).thenReturn(Optional.of(expectedCourse));

        when(courseRepository.findByName(anyString())).thenReturn(new ArrayList<>());
        when(courseRepository.findByName("Math")).thenReturn(List.of(expectedCourse));

        service = new SchoolService(studentRepository, courseRepository);

        service.addCourse(expectedCourse);

        expectedCourseId = expectedCourse.getId();
        System.out.println("expectedCustomerId = " + expectedCourseId);
    }

    @Test
    void testGetCourseById() {
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
        when(courseRepository.findById(nullable(String.class))).thenReturn(Optional.empty());

        service.deleteCourse(expectedCourseId);
        assertNull(service.getCourseById(expectedCourseId));
    }

}