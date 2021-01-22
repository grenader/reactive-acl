package com.grenader.sample.reactiveacl.services;

import com.grenader.sample.reactiveacl.model.Customer;
import com.grenader.sample.reactiveacl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer getCustomerById(String customerId) {
        final Optional<Customer> customerOptional = repository.findById(customerId);
        return customerOptional.orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }

    public List<Customer> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }
}
