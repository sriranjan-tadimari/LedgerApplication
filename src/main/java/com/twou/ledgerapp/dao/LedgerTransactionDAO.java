package com.twou.ledgerapp.dao;

import java.util.List;

import com.twou.ledgerapp.model.LedgerTransaction;

/*
 * The DAO service layer that defines the interface methods that implement the CRUD operations
 * required by REST calls made in the Ledger application.
 * @see LedgerTransactionDAOImpl.java
 * @author Sriranjan Tadimari
 */
public interface LedgerTransactionDAO {
	
	public List<LedgerTransaction> getAllNonDeleteLedgerTransactions();
	
	public LedgerTransaction getLedgerTransactionById(Long id);
	
	public Long saveLedgerTransaction(LedgerTransaction transaction);
	
	public Boolean updateLedgerTransactionTransValue(Long id);
	
	public Boolean softDeleteLedgerTransaction(Long id);
	
	public Double getSumOfAllNonDeleteLedgerTransactions();
}
