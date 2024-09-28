package uk.co.a6software.json_payload_validation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SimpleJsonPayloadController.class)
public class SimpleJsonPayloadControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public SimpleJsonPayloadControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testHandleSimpleJsonPayload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/simple"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("yes this worked"));
    }
}
