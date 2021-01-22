package com.grenader.sample.reactiveacl.repository;

import com.grenader.sample.reactiveacl.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> findByName(String name);

}
