package com.getir.interview.readingisgood.service;

import org.springframework.stereotype.Service;

import com.getir.interview.readingisgood.constants.ErrorMessages;
import com.getir.interview.readingisgood.dto.CreateCustomerRequest;
import com.getir.interview.readingisgood.exception.EmailAlreadyExistsException;
import com.getir.interview.readingisgood.exception.EntityNotFoundException;
import com.getir.interview.readingisgood.model.Customer;
import com.getir.interview.readingisgood.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public Customer getById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Customer.class, "id", id.toString()));
	}

	public Customer create(CreateCustomerRequest request) {
		if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXISTS_FOR_CUSTOMER.getErrorMessage());
		}
		Customer customer = new Customer();
		customer.setEmail(request.getEmail());
		return customerRepository.save(customer);
	}
}
