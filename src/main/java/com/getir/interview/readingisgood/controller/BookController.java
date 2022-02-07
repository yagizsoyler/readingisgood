package com.getir.interview.readingisgood.controller;

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

import com.getir.interview.readingisgood.dto.BookResponse;
import com.getir.interview.readingisgood.dto.CreateBookRequest;
import com.getir.interview.readingisgood.dto.UpdateBookStockRequest;
import com.getir.interview.readingisgood.mapper.DTOMapper;
import com.getir.interview.readingisgood.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
	
	private BookService bookService;
	private DTOMapper dtoMapper;

	@PostMapping()
    @ResponseBody
    @Operation(summary = "Add new book")
    public ResponseEntity<BookResponse> create(@Valid @RequestBody CreateBookRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoMapper.mapBookToDTO(bookService.create(request)));
    }
    
    @GetMapping("{id}")
    @Operation(summary = "Get a book by id")
    public ResponseEntity<BookResponse> get(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapBookToDTO(bookService.getById(id)));
    }
	
    @PostMapping("{id}/updateStock")
    @Operation(summary = "Update book stock by id")
    public ResponseEntity<BookResponse> update(@Valid @RequestBody UpdateBookStockRequest request, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.mapBookToDTO(bookService.updateBookStock(request, id)));
    }
}
