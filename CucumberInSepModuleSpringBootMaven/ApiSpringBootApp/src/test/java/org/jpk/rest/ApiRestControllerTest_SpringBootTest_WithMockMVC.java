package org.jpk.rest;

import org.jpk.service.Adder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiRestControllerTest_SpringBootTest_WithMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Adder adder;

    @Test
    public void test1() throws Exception {
        given(adder.addTwoNumbers(3, 7)).willReturn(10);

        MockHttpServletResponse response = mockMvc.perform(
                        post("/api/add")
                                .param("a", "3")
                                .param("b", "7")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(String.valueOf(10));
    }

}
