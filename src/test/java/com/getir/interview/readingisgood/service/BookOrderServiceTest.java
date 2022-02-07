package com.getir.interview.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.getir.interview.readingisgood.TestHelper;
import com.getir.interview.readingisgood.model.BookOrder;
import com.getir.interview.readingisgood.repository.BookOrderRepository;

@ExtendWith(MockitoExtension.class)
class BookOrderServiceTest {

    @InjectMocks
    private BookOrderService bookOrderService;

    @Mock
    private BookOrderRepository bookOrderRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private BookService bookService;

    @Test
    void getById_givenBookOrder() {
        BookOrder bookOrder = TestHelper.createBookOrder();
        Mockito.when(bookOrderRepository.findById(any())).thenReturn(Optional.of(bookOrder));
        assertEquals(bookOrderService.getById(any()), bookOrder);
    }

    @Test
    void create_givenBookOrder() {
        BookOrder bookOrder = TestHelper.createBookOrder();
        Mockito.when(customerService.getById(any())).thenReturn(TestHelper.createCustomer());
        Mockito.when(bookService.getById(any())).thenReturn(TestHelper.createBook());
        Mockito.when(bookOrderRepository.save(any())).thenReturn(bookOrder);
        assertEquals(bookOrderService.create(TestHelper.createBookOrderRequestObject()), bookOrder);
    }
}