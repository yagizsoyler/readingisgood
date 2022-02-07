package com.getir.interview.readingisgood.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.getir.interview.readingisgood.constants.ValidationErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDetailRequest {
	@NotNull(message = ValidationErrorMessages.MISSING_ORDER_DETAIL_BOOK_ID_FIELD)
	private Long bookId;
	@NotNull(message = ValidationErrorMessages.MISSING_ORDER_DETAIL_COUNT_FIELD)
	@Min(1)
	private Integer count;
}
