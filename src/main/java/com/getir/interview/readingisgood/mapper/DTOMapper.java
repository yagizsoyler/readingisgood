package com.getir.interview.readingisgood.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.getir.interview.readingisgood.dto.BookResponse;
import com.getir.interview.readingisgood.dto.CustomerResponse;
import com.getir.interview.readingisgood.dto.OrderDetailResponse;
import com.getir.interview.readingisgood.dto.BookOrderResponse;
import com.getir.interview.readingisgood.model.Book;
import com.getir.interview.readingisgood.model.Customer;
import com.getir.interview.readingisgood.model.BookOrder;
import com.getir.interview.readingisgood.model.OrderDetail;

@Component
public class DTOMapper {

	public BookResponse mapBookToDTO(Book book) {
		return BookResponse.builder()
				.id(book.getId())
				.name(book.getName())
				.stock(book.getStock())
				.price(book.getPrice())
				.build();
	}
	
	public CustomerResponse mapCustomerToDTO(Customer customer) {
		return CustomerResponse.builder()
				.id(customer.getId())
				.email(customer.getEmail())
				.build();
	}
	
	public OrderDetailResponse mapOrderDetailToDTO(OrderDetail orderDetail) {
		return OrderDetailResponse.builder()
				.bookResponse(mapBookToDTO(orderDetail.getBook()))
				.count(orderDetail.getCount())
				.build();
	}
	
	public BookOrderResponse mapBookOrderToDTO(BookOrder bookOrder) {
		return BookOrderResponse.builder()
				.id(bookOrder.getId())
				.customerResponse(mapCustomerToDTO(bookOrder.getCustomer()))
				.purchasedAmount(bookOrder.getPurchasedAmount())
				.orderDate(bookOrder.getOrderDate())
				.orderDetails(mapOrderDetailListToDTO(bookOrder.getOrderDetails()))
				.build();
	}
	
	public List<OrderDetailResponse> mapOrderDetailListToDTO(List<OrderDetail> orderDetails) {
		List<OrderDetailResponse> orderDetailResponseList = new ArrayList<OrderDetailResponse>();
		for(OrderDetail orderDetail : orderDetails) {
			orderDetailResponseList.add(mapOrderDetailToDTO(orderDetail));
		}
		return orderDetailResponseList;
	}
	
	public List<BookOrderResponse> mapOrderListToDTO(List<BookOrder> orderList) {
		List<BookOrderResponse> orderResponseList = new ArrayList<BookOrderResponse>();
		for(BookOrder order : orderList) {
			orderResponseList.add(mapBookOrderToDTO(order));
		}
		return orderResponseList;
	}
}
