package com.getir.interview.readingisgood.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
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
public class CreateBookRequest {
	@NotEmpty(message = ValidationErrorMessages.MISSING_BOOK_NAME_FIELD)
	private String name;
	@NotNull(message = ValidationErrorMessages.MISSING_BOOK_STOCK_FIELD)
	private Integer stock;
	@NotNull(message = ValidationErrorMessages.MISSING_BOOK_PRICE_FIELD)
	private BigDecimal price;
}
