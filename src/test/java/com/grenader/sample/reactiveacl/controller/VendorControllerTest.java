package com.grenader.sample.reactiveacl.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDefault_should_return_hardcoded_item() throws Exception {
        this.mockMvc.perform(get("/vendor-item/default")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":\"11\"")))
                .andExpect(content().string(containsString("\"name\":\"Test Vendor Item\"")))
                .andExpect(jsonPath("$.id", is("11")))
                .andExpect(jsonPath("$.name", is("Test Vendor Item")));
    }

    @Test
    public void testPostCreate_should_return_object_id() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/vendor-item")
                .content("{\"name\":\"Vendor Item\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(emptyString())));

    }
}