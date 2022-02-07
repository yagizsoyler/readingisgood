package com.getir.interview.readingisgood.exception;

@SuppressWarnings("serial")
public class NegativeBookStockException extends RuntimeException {
	public NegativeBookStockException(String message) {
		super(message);
	}
}
