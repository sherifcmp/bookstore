package com.store.book.management.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.book.management.controller.dto.ErrorResponse;
import com.store.book.management.controller.dto.response.AuthorDto;
import com.store.book.management.controller.dto.response.BookDto;
import com.store.book.management.exception.ExceptionConstants;
import com.store.book.management.repository.model.Author;
import com.store.book.management.repository.model.Book;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.store.book.management.exception.ExceptionConstants.BOOK_NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BooksControllerIT {

    @Autowired
    private MockMvc mockMvc;

    /** Get book by Id **/
    @Test
    void getBookById_ValidRequest_ReturnBook() throws Exception {
        AuthorDto authorDto = new AuthorDto(1L, "Lowbacca");
        BookDto bookDto = new BookDto(1L,"The Twelve Dancing Princesses",
                "a German fairy tale collected by the Brothers Grimm",
                37.5f,null, authorDto);

        MvcResult mvcResult = mockMvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jacksonMessageConverter.getObjectMapper();

        Assertions.assertEquals(bookDto, objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookDto.class));
    }

    @Test
    void getBookById_IdNoExist_ReturnNotFoundResponse() throws Exception {
        ErrorResponse errorResponse = new ErrorResponse(BOOK_NOT_FOUND);

        MvcResult mvcResult = mockMvc.perform(get("/api/books/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jacksonMessageConverter.getObjectMapper();

        Assertions.assertEquals(errorResponse, objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorResponse.class));
    }

}