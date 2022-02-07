package com.getir.interview.readingisgood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class OrderDetailResponse {
	private BookResponse bookResponse;
	private Integer count;
}
