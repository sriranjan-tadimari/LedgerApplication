package com.twou.ledgerapp.exception;

/*
 * Custom Runtime Exception class that is thrown when the user queries the database via REST 
 * with an id that is not found in the database table.
 * @see LedgerTransactionController.java
 * @see LedgerTransactionNotFoundAdvice
 * @author Sriranjan Tadimari
 */
public class LedgerTransactionNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public LedgerTransactionNotFoundException(Long id) {
	    super("Could not find transaction with id: " + id);
	 }
}
