package com.getir.interview.readingisgood.constants;

public enum ErrorMessages {
	EMAIL_ALREADY_EXISTS_FOR_CUSTOMER ("Email already exists for another customer. Please enter another email!"),
	CUSTOMER_NOT_FOUND ("Customer not found with given id!"),
	BOOK_NOT_FOUND ("Book not found with given id!"),
	BOOK_ORDER_NOT_FOUND ("Book Order not found with given id!"),
	BOOK_STOCK_CANNOT_BE_LESS_THAN_ZERO ("Book stock cannot be less than zero!"),
	ORDER_DETAIL_COUNT_CANNOT_BE_LESS_THAN_ONE ("Order Detail count cannot be less than 1!"),
	ORDER_DETAIL_INSUFFICIENT_BOOK_STOCK ("Insufficient book stock!"),
	INVALID_ENTRIES("Invalid entries");
	
	private String errorMessage;

    ErrorMessages( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage( )
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }
}
