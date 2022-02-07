package com.getir.interview.readingisgood;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.getir.interview.readingisgood.dto.CreateBookOrderRequest;
import com.getir.interview.readingisgood.dto.CreateBookRequest;
import com.getir.interview.readingisgood.dto.CreateCustomerRequest;
import com.getir.interview.readingisgood.dto.CreateOrderDetailRequest;
import com.getir.interview.readingisgood.dto.UpdateBookStockRequest;
import com.getir.interview.readingisgood.model.Book;
import com.getir.interview.readingisgood.model.BookOrder;
import com.getir.interview.readingisgood.model.Customer;
import com.getir.interview.readingisgood.model.OrderDetail;

public class TestHelper {

    public static CreateCustomerRequest createCustomerRequestObject(String email) {
        return CreateCustomerRequest.builder().email(email).build();
    }

    public static CreateBookRequest createBookRequestObject(Integer stock) {
        return CreateBookRequest.builder().name("test").stock(stock).price(BigDecimal.TEN).build();
    }

    public static UpdateBookStockRequest updateBookStockRequestObject() {
        return UpdateBookStockRequest.builder().stock(1).build();
    }

    public static CreateOrderDetailRequest createOrderDetailRequestObject() {
        return CreateOrderDetailRequest.builder().bookId(1L).count(1).build();
    }

    public static CreateBookOrderRequest createBookOrderRequestObject() {
        return CreateBookOrderRequest.builder().customerId(1L).detailList(Arrays.asList(createOrderDetailRequestObject())).build();
    }

    public static Book createBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("test");
        book.setPrice(BigDecimal.TEN);
        book.setStock(10);
        return book;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmail("testmail");
        return customer;
    }

    public static List<Customer> createCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(createCustomer());
        return customerList;
    }

    public static BookOrder createBookOrder() {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setId(1L);
        bookOrder.setCustomer(createCustomer());
        bookOrder.setOrderDate(LocalDateTime.of(2022,2,1,0,0));
        bookOrder.setPurchasedAmount(BigDecimal.TEN);
        bookOrder.setOrderDetails(Arrays.asList(createOrderDetail(bookOrder)));
        return bookOrder;
    }

    public static OrderDetail createOrderDetail(BookOrder bookOrder) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(1L);
        orderDetail.setOrder(bookOrder);
        orderDetail.setBook(createBook());
        orderDetail.setCount(1);
        return orderDetail;
    }
}
