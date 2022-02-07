package com.getir.interview.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.getir.interview.readingisgood.exception.NegativeBookStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.getir.interview.readingisgood.TestHelper;
import com.getir.interview.readingisgood.dto.CreateBookRequest;
import com.getir.interview.readingisgood.dto.UpdateBookStockRequest;
import com.getir.interview.readingisgood.model.Book;
import com.getir.interview.readingisgood.repository.BookRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void getById_givenBook() {
        Book book = TestHelper.createBook();
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        assertEquals(bookService.getById(any()), book);
    }

    @Test
    void create_NegativeStock_throwException(){
        CreateBookRequest createBookRequest = TestHelper.createBookRequestObject(-1);
        assertThrows(NegativeBookStockException.class, () -> bookService.create(createBookRequest));
    }

    @Test
    void create_givenBook(){
        CreateBookRequest createBookRequest = TestHelper.createBookRequestObject(10);
        Book book = TestHelper.createBook();
        Mockito.when(bookRepository.save(any())).thenReturn(book);
        assertEquals(bookService.create(createBookRequest), book);
    }

    @Test
    void updateBookStock_givenBook(){
        UpdateBookStockRequest updateBookStockRequest = TestHelper.updateBookStockRequestObject();
        Book oldBook = TestHelper.createBook();
        Book newBook = TestHelper.createBook();
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.of(oldBook));
        Mockito.when(bookRepository.save(any())).thenReturn(newBook);
        assertEquals(bookService.updateBookStock(updateBookStockRequest, any()), newBook);
    }
}