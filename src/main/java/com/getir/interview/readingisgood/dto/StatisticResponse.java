package com.getir.interview.readingisgood.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class StatisticResponse {
	private String month;
	private int totalOrderCount;
	private int totalBookCount;
	private BigDecimal totalPurchasedAmount;
}
