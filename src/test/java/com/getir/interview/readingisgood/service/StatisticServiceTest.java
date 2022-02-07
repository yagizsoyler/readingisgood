package com.getir.interview.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.getir.interview.readingisgood.TestHelper;
import com.getir.interview.readingisgood.repository.BookOrderRepository;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

	@InjectMocks
	private StatisticService statisticService;

	@Mock
	private BookOrderRepository bookOrderRepository;

	@Test
	void getStatistics_givenBookOrder() {
        Mockito.when(bookOrderRepository.findByCustomerIdAndMonthAndYear(1L, 1, 2022))
                .thenReturn(new ArrayList<>());
		Mockito.when(bookOrderRepository.findByCustomerIdAndMonthAndYear(1L, 2, 2022))
				.thenReturn(Arrays.asList(TestHelper.createBookOrder()));
        assertEquals(statisticService.getStatistics(1L).size(), 1);
	}
}