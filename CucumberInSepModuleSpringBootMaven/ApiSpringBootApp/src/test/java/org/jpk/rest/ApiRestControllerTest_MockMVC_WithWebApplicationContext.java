package org.jpk.rest;


import org.jpk.service.Adder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiRestController.class)
public class ApiRestControllerTest_MockMVC_WithWebApplicationContext {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private Adder adder;

    @Test
    public void addTwoNumbersAndReturnCorrectResult() throws Exception {

        given(adder.addTwoNumbers(3, 7)).willReturn(10);

        mvc.perform(post("/api/add")
                        .param("a", "3")
                        .param("b", "7")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));

        verify(adder, times(1)).addTwoNumbers(3, 7);
    }
}
