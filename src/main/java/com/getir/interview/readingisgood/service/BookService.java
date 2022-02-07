package com.getir.interview.readingisgood.service;

import org.springframework.stereotype.Service;

import com.getir.interview.readingisgood.constants.ErrorMessages;
import com.getir.interview.readingisgood.dto.CreateBookRequest;
import com.getir.interview.readingisgood.dto.UpdateBookStockRequest;
import com.getir.interview.readingisgood.exception.EntityNotFoundException;
import com.getir.interview.readingisgood.exception.NegativeBookStockException;
import com.getir.interview.readingisgood.model.Book;
import com.getir.interview.readingisgood.repository.BookRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookService {

	private BookRepository bookRepository;
	
	public Book create(CreateBookRequest request) {
		int stock = request.getStock();
		validateBookStock(stock);
		Book book = new Book();
		book.setName(request.getName());
		book.setStock(stock);
		book.setPrice(request.getPrice());
		return bookRepository.save(book);
	}
	
	public Book getById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Book.class, "id", id.toString()));
	}
	
	public Book updateBookStock(UpdateBookStockRequest request, Long id) {
		int stock = request.getStock();
		validateBookStock(stock);
		Book book = getById(id);
		book.setStock(stock);
		return bookRepository.save(book);
	}

	private void validateBookStock(int stock) {
		if(stock < 0) {
			throw new NegativeBookStockException(ErrorMessages.BOOK_STOCK_CANNOT_BE_LESS_THAN_ZERO.getErrorMessage());
		}
	}
}
