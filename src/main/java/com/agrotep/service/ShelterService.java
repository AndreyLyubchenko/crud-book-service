package com.agrotep.service;

import com.agrotep.dto.BookFullResponse;
import com.agrotep.dto.BookRequest;

import java.util.List;

public interface ShelterService {

    List<BookFullResponse> getAll();

    Long create(BookRequest bookRequest);

    BookFullResponse getBook(long id);

    BookFullResponse update(long id, BookRequest bookRequest);

    void delete(long id);

    List<BookFullResponse> getAllSortedByName();


}
