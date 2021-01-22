package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Student;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {

    @Autowired
    private SchoolService service;

    private static Student expectedStudent;
    private static String expectedCustomerId;
    private static String anotherCustomerId;

    @Test
    @Order(1)
    void setUp() {
        expectedStudent = Student.builder().firstName("John").lastName("Smith").build();
        service.addStudent(expectedStudent);

        service.addStudent(Student.builder().firstName("Mary").lastName("Smith").build());

        expectedCustomerId = expectedStudent.getId();
        System.out.println("expectedCustomerId = " + expectedCustomerId);
    }

    @Test
    @Order(2)
    void testGetCustomerById() {
        final Student student = service.getStudentById(expectedCustomerId);
        assertEquals(expectedStudent, student);
    }

    @Test
    @Order(3)
    void testDeleteCustomer() {
        service.deleteStudent(expectedCustomerId);
        assertNull(service.getStudentById(expectedCustomerId));
    }

    @Test
    @Order(4)
    void testFindByFirstName() {
        final List<Student> student = service.findStudentByFirstName("Mary");

        assertFalse(student.isEmpty());

        anotherCustomerId = student.get(0).getId();
        assertEquals("Smith", student.get(0).getLastName());
    }

    @Test
    @Order(5)
    void tearDown() {
        service.deleteStudent(anotherCustomerId);
        assertNull(service.getStudentById(anotherCustomerId));
    }


}