package com.getir.interview.readingisgood.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.getir.interview.readingisgood.dto.CreateBookOrderRequest;
import com.getir.interview.readingisgood.dto.BookOrderResponse;
import com.getir.interview.readingisgood.mapper.DTOMapper;
import com.getir.interview.readingisgood.service.BookOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bookorders")
@AllArgsConstructor
public class BookOrderController {

	private BookOrderService bookOrderService;
	private DTOMapper dtoMapper;

	@PostMapping()
	@ResponseBody
	@Operation(summary = "Add new book order")
	public ResponseEntity<BookOrderResponse> create(@Valid @RequestBody CreateBookOrderRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(dtoMapper.mapBookOrderToDTO(bookOrderService.create(request)));
	}

	@GetMapping("{id}")
	@Operation(summary = "Get a book order by id")
	public ResponseEntity<BookOrderResponse> get(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapBookOrderToDTO(bookOrderService.getById(id)));
	}

	@GetMapping("/get")
	@ResponseBody
	@Operation(summary = "Get book orders between start date and end date")
	public ResponseEntity<List<BookOrderResponse>> getByStartDateEndDate(
			@Parameter(name = "start") @RequestParam String start,
			@Parameter(name = "end") @RequestParam String end) {
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(start, DATEFORMATTER), LocalDateTime.now().toLocalTime());
	    LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(end, DATEFORMATTER), LocalDateTime.now().toLocalTime());
		return ResponseEntity.status(HttpStatus.OK)
				.body(dtoMapper.mapOrderListToDTO(bookOrderService.getByStartDateEndDate(startDate, endDate)));
	}

}
