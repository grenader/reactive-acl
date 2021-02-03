package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Course;
import com.grenader.sample.reactiveacl.repository.CourseRepository;
import com.grenader.sample.reactiveacl.repository.StudentRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@ContextConfiguration(initializers = {CourseServiceTest.Initializer.class})
class CourseServiceTest {

    @Container
    @ClassRule
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
//    final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))

    private SchoolService service;

    private static Course expectedCourse;
    private static String expectedCourseId;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDBContainer.getHost(),
                    "spring.data.mongodb.port=" + mongoDBContainer.getFirstMappedPort()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }


    @BeforeEach
    void setUp() {
        System.out.println("starting ...");
        String address = mongoDBContainer.getHost();
        Integer port = mongoDBContainer.getFirstMappedPort();
        System.out.println("address = " + address);
        System.out.println("port = " + port);
        System.out.println("... started");

        service = new SchoolService(studentRepository, courseRepository);

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