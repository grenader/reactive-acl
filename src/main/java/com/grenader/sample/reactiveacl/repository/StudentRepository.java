package com.grenader.sample.reactiveacl.repository;

import com.grenader.sample.reactiveacl.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);

}