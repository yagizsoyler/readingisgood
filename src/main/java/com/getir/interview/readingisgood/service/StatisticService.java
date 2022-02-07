package com.getir.interview.readingisgood.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.getir.interview.readingisgood.dto.StatisticResponse;
import com.getir.interview.readingisgood.model.BookOrder;
import com.getir.interview.readingisgood.repository.BookOrderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StatisticService {

	BookOrderRepository bookOrderRepository;

	public List<StatisticResponse> getStatistics(long customerId) {
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		List<StatisticResponse> statisticResponses = new ArrayList<>();
		for (int i = 1; i < now.getMonthValue() + 1; i++) {
			List<BookOrder> monthlyOrders = bookOrderRepository.findByCustomerIdAndMonthAndYear(customerId, i, year);
			if(monthlyOrders.size() > 0) {
				statisticResponses.add(getStatisticResponse(i, monthlyOrders));
			}
		}
		
		return statisticResponses;
	}

	private StatisticResponse getStatisticResponse(int i, List<BookOrder> monthlyOrders) {
		return StatisticResponse.builder().month(getMonthName(i))
				.totalOrderCount(monthlyOrders.size())
				.totalBookCount(getTotalBookCount(monthlyOrders))
				.totalPurchasedAmount(getTotalPurchasedAmount(monthlyOrders))
				.build();
	}

	private String getMonthName(int i) {
		return Month.of(i).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}

	private BigDecimal getTotalPurchasedAmount(List<BookOrder> monthlyOrders) {
		return monthlyOrders.stream().map(x -> x.getPurchasedAmount())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private int getTotalBookCount(List<BookOrder> monthlyOrders) {
		return monthlyOrders.stream()
				.mapToInt(o -> o.getOrderDetails().stream().mapToInt(d -> d.getCount()).sum()).sum();
	}

}
