package com.grenader.sample.reactiveacl.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
@Testcontainers
class TestContainerTest {

    // not a standard port is used.
    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10")).withExposedPorts(25037);

    @BeforeEach
    void setUp() {
        System.out.println("starting ...");
        String address = mongoDBContainer.getHost();
        Integer port = mongoDBContainer.getFirstMappedPort();
        System.out.println("address = " + address);
        System.out.println("port = " + port);
        System.out.println("... started");
    }

    @Test
    public void testSimplePutAndGet() {
        System.out.println("mongoDBContainer = " + mongoDBContainer);
    }

}