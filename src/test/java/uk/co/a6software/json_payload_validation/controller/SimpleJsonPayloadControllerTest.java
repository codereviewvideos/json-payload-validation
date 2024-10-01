package uk.co.a6software.json_payload_validation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.co.a6software.json_payload_validation.dto.SimplePayloadDto;

@WebMvcTest(SimpleJsonPayloadController.class)
public class SimpleJsonPayloadControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public SimpleJsonPayloadControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testHandleNoPayload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/no-payload"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("yes this worked"));
    }

    @Test
    public void testHandleSimpleJsonPayload_withInvalidPayload() throws Exception {
        SimplePayloadDto invalidDto = new SimplePayloadDto(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/simple-payload")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().string("{\"text\":\"Text must be provided\"}"));
    }

    @Test
    public void testHandleSimpleJsonPayload_withValidPayload() throws Exception {
        SimplePayloadDto validDto = new SimplePayloadDto("Some text");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/simple-payload")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validDto));

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("yes this also worked"));
    }
}
