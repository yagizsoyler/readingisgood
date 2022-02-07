package com.getir.interview.readingisgood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.getir.interview.readingisgood.dto.CreateCustomerRequest;
import com.getir.interview.readingisgood.dto.CustomerResponse;
import com.getir.interview.readingisgood.dto.BookOrderResponse;
import com.getir.interview.readingisgood.mapper.DTOMapper;
import com.getir.interview.readingisgood.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
	
	private CustomerService customerService;
	private DTOMapper dtoMapper;
	
	@PostMapping()
    @ResponseBody
    @Operation(summary = "Add new customer")
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.mapCustomerToDTO(customerService.create(request)));
    }
	
	@GetMapping("{id}/orders")
    @Operation(summary = "Get customer orders by id")
    public ResponseEntity<List<BookOrderResponse>> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapOrderListToDTO(customerService.getById(id).getBookOrders()));
    }
}
