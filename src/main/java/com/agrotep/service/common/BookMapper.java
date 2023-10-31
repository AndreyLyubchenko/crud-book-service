package com.agrotep.service.common;

import com.agrotep.dto.BookFullResponse;
import com.agrotep.model.Book;

public interface BookMapper {

    BookFullResponse getBookFullResponse(Book book);
}
