package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.mapper.InternMapperImpl;
import com.stepintoprofession.registration_service.repository.InternRepository;
import com.stepintoprofession.registration_service.repository.ProjectSeasonRepository;
import com.stepintoprofession.registration_service.service.InternService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(InternController.class)
public class InternControllerTest {

    @Autowired
    private MockMvc mvc;
    @SpyBean
    private InternService internService;
    @SpyBean
    private InternMapperImpl internMapper;
    @MockBean
    private InternRepository internRepository;
    @MockBean
    private ProjectSeasonRepository seasonRepository;

    @Test
    public void whenFindAll_thenOk() throws Exception{

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/intern")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.intern").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.intern.id").isNotEmpty());
    }

}
