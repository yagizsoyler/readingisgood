package com.getir.interview.readingisgood.dto;

import javax.validation.constraints.NotNull;

import com.getir.interview.readingisgood.constants.ValidationErrorMessages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookStockRequest {
	@NotNull(message = ValidationErrorMessages.MISSING_BOOK_STOCK_FIELD)
	private Integer stock;

}
