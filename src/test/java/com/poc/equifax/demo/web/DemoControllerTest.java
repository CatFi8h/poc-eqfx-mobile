package com.poc.equifax.demo.web;

import com.poc.equifax.demo.service.MessageReader;
import com.poc.equifax.demo.web.model.MessagesList;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DemoController.class)
@RunWith(SpringRunner.class)
class DemoControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MessageReader messageReader;

    @Before
    public void before() {
        when(messageReader.getMessages()).thenReturn(new MessagesList());
    }

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
                        .patch("http:// localhost:8080/membercenter/api/v1/messages/1")
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
                        .get("http:// localhost:8080/membercenter/api/v1/messages/unread-count")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}