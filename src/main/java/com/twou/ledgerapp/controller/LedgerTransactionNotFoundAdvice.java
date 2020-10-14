package com.twou.ledgerapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.twou.ledgerapp.exception.LedgerTransactionNotFoundException;

/*
 * When an TransactionNotFoundException is thrown, this extra tidbit of 
 * Spring MVC configuration is used to render an HTTP 404
 * @author: Sriranjan Tadimari
 */

@ControllerAdvice
public class LedgerTransactionNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(LedgerTransactionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String transactionNotFoundHandler(LedgerTransactionNotFoundException ex) {
		return ex.getMessage();
	}
}
