package org.jpk.rest;

import org.jpk.service.Adder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ApiRestControllerTest_MockMVC_StandaloneMode {


    /**
     * Standalone Method is the best for Testing RestControllers logic
     * without focusing on other behavior.
     */

    private MockMvc mvc;

    @Mock
    private Adder adder;

    @InjectMocks
    private ApiRestController apiRestController;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(apiRestController).build();
        when(adder.addTwoNumbers(3, 7)).thenReturn(10);
    }

    @Test
    public void addingTwoNumbers_ShouldReturnCorrectResult() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/add")
                        .param("a", "3")
                        .param("b", "7"))
                .andExpect(status().isOk()) // Expect HTTP 200
                .andExpect(content().string("10")) // Expect the response to be "10"
                .andReturn();

        verify(adder, times(1)).addTwoNumbers(3, 7);
    }
}
