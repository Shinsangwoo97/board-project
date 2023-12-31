package com.example.projectboard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
private MockMvc mvc;
public DataRestTest(@Autowired MockMvc mvc){
    this.mvc = mvc;
}
@Test
    void test() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
            .andDo(print());
}
@Test
    void test2() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles/1/articleComments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
            .andDo(print());
}
}
