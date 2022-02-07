package com.getir.interview.readingisgood.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.getir.interview.readingisgood.constants.ErrorMessages;
import com.getir.interview.readingisgood.dto.CreateBookOrderRequest;
import com.getir.interview.readingisgood.dto.CreateOrderDetailRequest;
import com.getir.interview.readingisgood.exception.EntityNotFoundException;
import com.getir.interview.readingisgood.exception.InsufficentBookStockException;
import com.getir.interview.readingisgood.model.Book;
import com.getir.interview.readingisgood.model.BookOrder;
import com.getir.interview.readingisgood.model.OrderDetail;
import com.getir.interview.readingisgood.repository.BookOrderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookOrderService {

	private BookOrderRepository bookOrderRepository;
	private CustomerService customerService;
	private BookService bookService;

	public BookOrder getById(Long id) {
		return bookOrderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(BookOrder.class, "id", id.toString()));
	}

	public List<BookOrder> getByStartDateEndDate(LocalDateTime start, LocalDateTime end) {
		return bookOrderRepository.findByStartDateAndEndDate(start, end);
	}

	public BookOrder create(CreateBookOrderRequest request) {
		BookOrder order = new BookOrder();
		order.setCustomer(customerService.getById(request.getCustomerId()));
		List<OrderDetail> orderDetails = getOrderDetails(request, order);
		order.setOrderDetails(orderDetails);
		order.setPurchasedAmount(getTotalPurchasedAmount(orderDetails));
		order.setOrderDate(LocalDateTime.now());
		return bookOrderRepository.save(order);
	}

	private List<OrderDetail> getOrderDetails(CreateBookOrderRequest request, BookOrder order) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (CreateOrderDetailRequest createOrderDetailRequest : request.getDetailList()) {
			orderDetails.add(getOrderDetail(order, createOrderDetailRequest));
		}
		return orderDetails;
	}

	private OrderDetail getOrderDetail(BookOrder order, CreateOrderDetailRequest createOrderDetailRequest) {
		Book book = bookService.getById(createOrderDetailRequest.getBookId());
		int bookCount = createOrderDetailRequest.getCount();
		validateBookStock(book, bookCount);
		reduceBookStock(book, bookCount);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(book);
		orderDetail.setCount(bookCount);
		orderDetail.setOrder(order);
		return orderDetail;
	}

	private void validateBookStock(Book book, int bookCount) {
		if (book.getStock() < bookCount) {
			throw new InsufficentBookStockException(
					ErrorMessages.ORDER_DETAIL_INSUFFICIENT_BOOK_STOCK.getErrorMessage());
		}
	}

	private void reduceBookStock(Book book, int bookCount) {
		book.setStock(book.getStock() - bookCount);
	}

	private BigDecimal getTotalPurchasedAmount(List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(x -> x.getBook().getPrice().multiply(new BigDecimal(x.getCount())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
