package com.getir.interview.readingisgood.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class BookOrderResponse {
	private Long id;
	private CustomerResponse customerResponse;
	private BigDecimal purchasedAmount;
	private LocalDateTime orderDate;
	private List<OrderDetailResponse> orderDetails;
}
