package com.jayway.robot.controller;

import com.jayway.robot.service.RobotService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RobotControllerTest {
    @Value("${robot.api.url.template}")
    private String apiUrlBase;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RobotService robotServiceMock;

    /**
     * Test moving a SQUARE room at position (1,2)
     */
    @Test
    public void moveExample1() throws Exception {
        when(robotServiceMock.move(any(), any(), anyInt(), anyInt())).thenReturn("11N");

        mockMvc.perform(post(apiUrlBase + "/move/?command=HGHGGHGHG&room=SQUARE&xCurrentPosition=1&yCurrentPosition=2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("11N"));
    }

    /**
     * Test moving inside a CIRCLE room at position (0,0)
     */
    @Test
    public void moveExample2() throws Exception {
        when(robotServiceMock.move(any(), any(), anyInt(), anyInt())).thenReturn("3-1Ö");

        mockMvc.perform(post(apiUrlBase + "/move/?command=RRFLFFLRF&room=CIRCLE&xCurrentPosition=0&yCurrentPosition=0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("3-1Ö")));
    }

    /**
     * Test RUNTIME Error (invalid position)
     */
    @Test
    public void moveRUNTIME_ERROR_InvalidPosition() throws Exception {
        when(robotServiceMock.move(any(), any(), anyInt(), anyInt())).thenReturn("11N");

        mockMvc.perform(post(apiUrlBase + "/move/?command=HGHGGHGHG&room=SQUARE&xCurrentPosition=-1&yCurrentPosition=2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode", is("RUNTIME_ERROR")))
                .andExpect(jsonPath("$.message", is("move.xCurrentPosition: must be greater than or equal to 0")));
    }

    /**
     * Test RUNTIME Error (invalid room)
     */
    @Test
    public void moveRUNTIME_ERROR_InvalidRoom() throws Exception {
        when(robotServiceMock.move(any(), any(), anyInt(), anyInt())).thenReturn("11N");

        mockMvc.perform(post(apiUrlBase + "/move/?command=HGHGGHGHG&room=&xCurrentPosition=1&yCurrentPosition=2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorCode", is("RUNTIME_ERROR")))
                .andExpect(jsonPath("$.message", is("move.room: must not be blank")));
    }
}