package ru.diasoft.task.controller;


import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.diasoft.task.entiry.Message;
import ru.diasoft.task.services.MessageService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageService service;

    @Test
    public void getAllTest() throws Exception {
        Message message1 = new Message(1L, "Test message from WebMvcTest 1");
        Message message2 = new Message(2L, "Test message from WebMvcTest 2");
        Message message3 = new Message(3L, "Test message from WebMvcTest 3");
        Message message4 = new Message(4L, "Test message from WebMvcTest 4");

        List<Message> messages = new ArrayList<Message>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);

        given(service.getAll()).willReturn(messages);
        mvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getTest() throws Exception {
        given(service.getOne(ArgumentMatchers.anyLong())).willReturn(new Message(5L,
                "Test message from WebMvcTest 5"));
        mvc.perform((get("/messages/5")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.text", is("Test message from WebMvcTest 5")));
    }

    @Test
    public void postTest() throws Exception {
        given(service.create(ArgumentMatchers.any())).willReturn(new Message(6L,
                "Test message from WebMvcTest 6"));
        mvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"Test message from WebMvcTest 6\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.text", is("Test message from WebMvcTest 6")));
    }

    @Test
    public void putTest() throws Exception {
        given(service.update(ArgumentMatchers.anyLong() ,ArgumentMatchers.any())).willReturn
                (new Message(7L, "Test message from WebMvcTest 7"));
        mvc.perform(put("/messages/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"7\", \"text\":\"Test message from WebMvcTest 7\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(7)))
                .andExpect(jsonPath("$.text", is("Test message from WebMvcTest 7")));
    }

    @Test
    public void deleteTest() throws Exception {
        given(service.delete(1L)).willReturn(true);
        mvc.perform(MockMvcRequestBuilders.delete("/messages/1"))
                .andExpect(status().isOk());
    }
}
