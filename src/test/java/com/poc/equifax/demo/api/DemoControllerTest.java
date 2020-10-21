package com.poc.equifax.demo.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DemoController.class)
@RunWith(SpringRunner.class)
class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldPassGetMembers() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("http:// localhost:8080/membercenter/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPassPutMarkMessageAsRead() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .put("http:// localhost:8080/membercenter/api/v1/messages/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPassGetMessageAsPDF() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("http:// localhost:8080/membercenter/api/v1/messages/1/pdf")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldPassGetMessagesCount() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("http:// localhost:8080/membercenter/api/v1/messages/unread")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}