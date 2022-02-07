package com.getir.interview.readingisgood.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class BookResponse {
	private Long id;
	private String name;
	private Integer stock;
	private BigDecimal price;
}
