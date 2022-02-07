package com.getir.interview.readingisgood.dto;

import java.util.List;

import javax.validation.Valid;
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
public class CreateBookOrderRequest {
	@NotNull(message = ValidationErrorMessages.MISSING_ORDER_CUSTOMER_ID_FIELD)
	private Long customerId;
	@NotNull(message = ValidationErrorMessages.MISSING_ORDER_DETAILS_FIELD)
	@Valid
	private List<CreateOrderDetailRequest> detailList;

}
