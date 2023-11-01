package com.agrotep.service;

import com.agrotep.dto.BookFullResponse;
import com.agrotep.dto.BookRequest;
import com.agrotep.model.Book;
import com.agrotep.repository.ShelterRepository;
import com.agrotep.service.common.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    final ShelterRepository shelterRepository;

    final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookFullResponse> getAll() {
        List<Book> books = shelterRepository.findAll();

        return books.stream()
                .map(bookMapper::getBookFullResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long create( BookRequest bookRequest) {

        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setBookDate(bookRequest.getBookDate());

       return shelterRepository.save(book).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public BookFullResponse getBook(long id) {

        Optional<Book> book = shelterRepository.findById(id);

       return book.map(bookMapper::getBookFullResponse)
                .orElseThrow(() -> new EntityNotFoundException("Book not found for id: " + id));


    }

    @Override
    @Transactional
    public BookFullResponse update(long id, BookRequest bookRequest) {
        Optional<Book> bookOptional = shelterRepository.findById(id);

        Book book = bookOptional.orElseThrow(() -> new EntityNotFoundException("Book not found for id: " + id));

        book.setName(bookRequest.getName());
        book.setBookDate(bookRequest.getBookDate());

        Book updatedBook = shelterRepository.save(book);

        return bookMapper.getBookFullResponse(updatedBook);
    }

    @Override
    @Transactional
    public void delete(long id) {

        Optional<Book> bookOptional = shelterRepository.findById(id);

        Book book = bookOptional.orElseThrow(() -> new EntityNotFoundException("Book not found for id: " + id));

        shelterRepository.deleteById(book.getId());
    }
}
