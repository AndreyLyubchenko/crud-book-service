package com.agrotep.service.common;

import com.agrotep.dto.BookFullResponse;
import com.agrotep.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapperImpl implements BookMapper{

    @Override
    public BookFullResponse getBookFullResponse(Book book) {
        return BookFullResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .bookDate(book.getBookDate())
                .build();
    }
}
