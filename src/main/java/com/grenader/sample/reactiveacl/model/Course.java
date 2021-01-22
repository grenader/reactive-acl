package com.grenader.sample.reactiveacl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    public String id;

    public String name;
    public String description;
    public List<String> subjects;

}
