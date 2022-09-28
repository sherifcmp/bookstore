package com.store.book.management.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.book.management.controller.dto.ErrorResponse;
import com.store.book.management.controller.dto.response.AuthorDto;
import com.store.book.management.controller.dto.response.BookDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.store.book.management.exception.ExceptionConstants.AUTHOR_NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MappingJackson2HttpMessageConverter jacksonMessageConverter;

    /** Get author by Id **/
    @Test
    void getAuthorById_ValidRequest_ReturnAuthor() throws Exception {
        AuthorDto authorDto = new AuthorDto(1L, "Lowbacca");

        MvcResult mvcResult = mockMvc.perform(get("/api/authors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper objectMapper = jacksonMessageConverter.getObjectMapper();

        Assertions.assertEquals(authorDto, objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AuthorDto.class));
    }

    @Test
    void getAuthorById_IdNoExist_ReturnNotFoundResponse() throws Exception {
        ErrorResponse errorResponse = new ErrorResponse(AUTHOR_NOT_FOUND);

        MvcResult mvcResult = mockMvc.perform(get("/api/authors/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        ObjectMapper objectMapper = jacksonMessageConverter.getObjectMapper();

        Assertions.assertEquals(errorResponse, objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class));
    }
}
