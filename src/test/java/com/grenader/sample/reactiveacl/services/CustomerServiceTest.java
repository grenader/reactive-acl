package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Customer;
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
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    private static Customer expectedCustomer;
    private static String expectedCustomerId;
    private static String anotherCustomerId;

    @Test
    @Order(1)
    void setUp() {
        expectedCustomer = Customer.builder().firstName("John").lastName("Smith").build();
        service.addCustomer(expectedCustomer);

        service.addCustomer(Customer.builder().firstName("Mary").lastName("Smith").build());

        expectedCustomerId = expectedCustomer.getId();
        System.out.println("expectedCustomerId = " + expectedCustomerId);
    }

    @Test
    @Order(2)
    void testGetCustomerById() {
        final Customer customer = service.getCustomerById(expectedCustomerId);
        assertEquals(expectedCustomer, customer);
    }

    @Test
    @Order(3)
    void testDeleteCustomer() {
        service.deleteCustomer(expectedCustomerId);
        assertNull(service.getCustomerById(expectedCustomerId));
    }

    @Test
    @Order(4)
    void testFindByFirstName() {
        final List<Customer> customer = service.findByFirstName("Mary");

        assertFalse(customer.isEmpty());

        anotherCustomerId = customer.get(0).getId();
        assertEquals("Smith", customer.get(0).getLastName());
    }

    @Test
    @Order(5)
    void tearDown() {
        service.deleteCustomer(anotherCustomerId);
        assertNull(service.getCustomerById(anotherCustomerId));
    }


}