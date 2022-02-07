package com.getir.interview.readingisgood.exception;

@SuppressWarnings("serial")
public class InsufficentBookStockException extends RuntimeException {
	public InsufficentBookStockException(String message) {
		super(message);
	}
}