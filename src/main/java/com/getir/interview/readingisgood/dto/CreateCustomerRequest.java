package com.getir.interview.readingisgood.dto;



import javax.validation.constraints.NotEmpty;

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
public class CreateCustomerRequest {
	@NotEmpty(message = ValidationErrorMessages.MISSING_CUSTOMER_EMAIL_FIELD)
	private String email;
}
