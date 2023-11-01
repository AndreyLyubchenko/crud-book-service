package com.agrotep.web;

import com.agrotep.dto.BookFullResponse;

import com.agrotep.dto.BookRequest;
import com.agrotep.service.ShelterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ShelterController.class)
public class ShelterControllerTest {

    private final Integer EXPECTED_BOOK_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService;

    @Test
    void books() throws Exception {

        BookFullResponse book = BookFullResponse.builder()
                .name("Good Book")
                .bookDate(new Date()).build();

        List<BookFullResponse> expectedBooks = new ArrayList<>();

        expectedBooks.add(book);

        when(shelterService.getAll())
                .thenReturn(expectedBooks);

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Good Book"));
    }

    @Test
    void book() throws Exception {

        BookFullResponse fullResponse = BookFullResponse.builder()
                .name("BobiDik")
                .bookDate(new Date()).build();

        when(shelterService.getBook(EXPECTED_BOOK_ID))
                .thenReturn(fullResponse);

        mockMvc.perform(get("/api/v1/books/{id}", EXPECTED_BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("BobiDik"));

    }

    @Test
    void createBook() throws Exception {

        BookRequest bookRequest = new BookRequest();
        bookRequest.setName("Dic");
        bookRequest.setBookDate(new Date());

        when(shelterService.create(bookRequest))
                .thenReturn(Long.valueOf(EXPECTED_BOOK_ID));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, Matchers.endsWith("/api/v1/books/" + EXPECTED_BOOK_ID)));

    }

    @Test
    void updateBook() throws Exception {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setName("Dic");
        bookRequest.setBookDate(new Date());

        BookFullResponse fullResponse = BookFullResponse.builder()
                .name("BobiDik")
                .bookDate(new Date()).build();

        when(shelterService.update(EXPECTED_BOOK_ID, bookRequest))
                .thenReturn(fullResponse);

        mockMvc.perform(patch("/api/v1/books/{id}",EXPECTED_BOOK_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("BobiDik"));

    }

    @Test
    void deleteBook() throws Exception {

        doNothing().when(shelterService)
                .delete(EXPECTED_BOOK_ID);

        mockMvc.perform(delete("/api/v1/books/{id}",EXPECTED_BOOK_ID))
                .andExpect(status().isNoContent());

    }
}
