package com.brunadelmouro.microservicemeetup.controllers;

import com.brunadelmouro.microservicemeetup.models.Registration;
import com.brunadelmouro.microservicemeetup.models.dto.registration.RegistrationResponseDTO;
import com.brunadelmouro.microservicemeetup.repositories.RegistrationRepository;
import com.brunadelmouro.microservicemeetup.services.impl.RegistrationServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = {RegistrationController.class})
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    static String REGISTRATION_API = "/api/registrations";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RegistrationServiceImpl registrationService;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should get Registration information")
    public void saveRegistrationTest() throws Exception {

        // cenario
        Registration registration = createValidRegistration();

        Registration savedRegistration = createValidRegistration();


        // execucao
        BDDMockito.given(registrationService.saveRegistration(any(Registration.class))).willReturn(savedRegistration);


        String json  = new ObjectMapper().writeValueAsString(registration);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(REGISTRATION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        //assert
        mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(101))
                .andExpect(jsonPath("name").value(registration.getName()))
                .andExpect(jsonPath("email").value(registration.getEmail()))
                .andExpect(jsonPath("password").value(registration.getPassword()))
                .andExpect(jsonPath("registrationNumber").value(registration.getNumber()));
    }

    @Test
    @DisplayName("Should delete the Registration")
    public void deleteRegistration() throws Exception {

        Registration registration = createValidRegistration();

        BDDMockito.given(registrationService
                        .findRegistrationById(anyInt()))
                .willReturn(registration);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(REGISTRATION_API.concat("/" + 1))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    public Registration createValidRegistration() {
        return new Registration(101, "Vit??ria", "vitoria@gmail.com", "123", "001");
    }

}