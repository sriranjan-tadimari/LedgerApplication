package com.twou.ledgerapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twou.ledgerapp.dao.LedgerTransactionDAO;
import com.twou.ledgerapp.exception.LedgerTransactionNotFoundException;
import com.twou.ledgerapp.model.LedgerTransaction;

/*
 * Controller class that handles RESTful requests to the application. 
 * The class methods are called for the following matching URL
 *    http://<server:port>/ledger/<sub uri>
 * This class implements the REST operations GET, PUT, POST and DELETE 
 * that are applicable to the Ledger Application.
 *  GET endpoint that returns a specific transaction by Id
    POST endpoint that creates a single transaction
	PUT endpoint that updates the transaction_value of a specific transaction determined by transaction Id
	DELETE endpoint that “soft-deletes” a transaction (see soft-delete details below)
	GET endpoint that returns all transactions from the database that are not soft-deleted
	GET endpoint that returns the sum of all transaction values for transactions that are not soft-deleted

 */
@RestController
@RequestMapping("/ledger")
public class LedgerTransactionController {

	/*
	 * Represents the DAO. The implementation of the service layer interface is injected by the 
	 * framework upon startup.
	 */
	
	private final LedgerTransactionDAO transRepository;

	public LedgerTransactionController(LedgerTransactionDAO repository) {
		this.transRepository = repository;
	}
	
	/*
	 * Implements GET method to return the Transaction record object for the id. If the record is not found
	 * throws LedgerTransactionNotFoundException exception.Calls the service layer API implemented in 
	 * LedgerTransactionDAOImpl.java
	 * Example CURL Request : 
	 * 			curl -X GET http://localhost:8080/ledger/transactions/1 <-- <id> 
	 * @param id	ID of the transaction record in the database table ledger passed as @PathVariable
	 * @returns The the domain object LedgerTransaction for the Transaction ID OR the response 
	 * 			"Could not find transaction with id: <id>"
	 * @throws	LedgerTransactionNotFoundException
	 * @see LedgerTransactionNotFoundException.java
	 * @see LedgerTransaction.java
	 * @see LedgerTransactionDAOImpl.java
	 */
	@GetMapping("/transactions/{id}")
	public LedgerTransaction getTransactionById(@PathVariable Long id) throws LedgerTransactionNotFoundException{
		try {
			LedgerTransaction transaction = transRepository.getLedgerTransactionById(id);
			return transaction;
		}
		catch(Exception e) {
			throw new LedgerTransactionNotFoundException(id);
		}	
	}

	/*
	 * Implements GET method to returns all the transaction records from the 'ledger' table for which the 
	 * soft delete flag is false. Calls the service layer API implemented in LedgerTransactionDAOImpl.java
	 * Example CURL Request: 
	 * 			curl -X GET http://localhost:8080/ledger/transactions
	 * @returns Transaction records from the 'ledger' database table mapped to list of domain object 
	 * LedgerTransaction or Empty list if no records are found.
	 * @see LedgerTransaction.java
	 * @see LedgerTransactionDAOImpl.java
	 */
	
	@GetMapping("/transactions")
	public List<LedgerTransaction> getAllNonDeleteLedgerTransactions() {
		return transRepository.getAllNonDeleteLedgerTransactions();
	}

	/*
	 * Implements POST method to create a new ledger transaction record based on the LedgerTransaction 
	 * domain object passed as RequestBody parameter.An underlying converter converts the json formatted object to
	 * the LedgerTransaction object.
	 * Example CURL : 
	 * 		curl -H "Content-Type: application/json" -X POST  \
	 * 			-d "{\"senderName\":\"Peter\",\"recipientName\":\"Paul\"}" http://localhost:8080/ledger/transactions 
	 * @param ledgerTransaction	New LedgerTransaction object for which transaction record to be created.
	 * @returns The new transaction record converted to the domain object LedgerTransaction.
	 * @see LedgerTransaction.java
	 * @see LedgerTransactionDAOImpl.java
	 */
	
	@PostMapping("/transactions")
	public LedgerTransaction newTransaction(@RequestBody LedgerTransaction newTransaction) {
		Long id = transRepository.saveLedgerTransaction(newTransaction);
		return transRepository.getLedgerTransactionById(id);
	}
	
	/*
	 * Implements PUT method to update the transaction value of the transaction record with a randomly generated
	 * double value to simulate the user updated the transaction value.If the record is not found
	 * throws LedgerTransactionNotFoundException exception.Calls the service layer API implemented in 
	 * LedgerTransactionDAOImpl.java
	 * Example CURL Request : 
	 * 			curl -X PUT http://localhost:8080/ledger/transactions/2 <-- <id> 
	 * @param id	ID of the transaction record in the database table ledger passed as @PathVariable
	 * @returns The the domain object LedgerTransaction for the Transaction ID OR the response 
	 * 			"Could not find transaction with id: <id>"
	 * @throws	LedgerTransactionNotFoundException
	 * @see LedgerTransactionNotFoundException.java
	 * @see LedgerTransaction.java
	 * @see LedgerTransactionDAOImpl.java
	 */

	@PutMapping("/transactions/{id}")
	public LedgerTransaction updateTransaction(@PathVariable Long id) throws LedgerTransactionNotFoundException{
		try {
			LedgerTransaction trans = transRepository.getLedgerTransactionById(id);
			if(transRepository.updateLedgerTransactionTransValue(id)) {
				return transRepository.getLedgerTransactionById(id);
			}
			return trans;
		}
		catch(Exception e) {
			throw new LedgerTransactionNotFoundException(id);
		}		
	}

	/*
	 * Implements DELETE method to soft delete the transaction i.e the record is not actually deleted
	 * but the soft delete flag is set to true and will not be visible when the user called /transactions or 
	 * /transacted_value_aggregate.If the record is not found throws LedgerTransactionNotFoundException 
	 * exception.Calls the service layer API implemented in LedgerTransactionDAOImpl.java
	 * Example CURL Request : 
	 * 			curl -X DELETE http://localhost:8080/ledger/transactions/3 <-- <id> 
	 * @param id	ID of the transaction record in the database table ledger passed as @PathVariable
	 * @returns 	ResponseEntity<?> object with Message "Transaction record with id <id> is deleted" OR the response 
	 * 				"Could not find transaction with id: <id>"
	 * @throws	LedgerTransactionNotFoundException
	 * @see LedgerTransactionNotFoundException.java
	 * @see LedgerTransaction.java
	 * @see LedgerTransactionDAOImpl.java
	 */

	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<?> softDeleteTransactionById(@PathVariable Long id) throws LedgerTransactionNotFoundException{
		try {
			transRepository.getLedgerTransactionById(id);
			transRepository.softDeleteLedgerTransaction(id);
			return new ResponseEntity<>("Transaction record with id " + id + " is deleted.",HttpStatus.OK);
		}
		catch(Exception e) {
			throw new LedgerTransactionNotFoundException(id);
		}		
	}
	
	/*
	 * Implements GET method to calculated the sum of all transacted values in the table which has NOT been 
	 * soft deleted.Calls the service layer API implemented in LedgerTransactionDAOImpl.java
	 * Example CURL Request : 
	 * 			curl -X GET http://localhost:8080/ledger/transactions/sum-transacted 
	 * @returns 	ResponseEntity<?> object with Message "Aggregated value of transacted value not soft deleted is xxx.xx"
	 * @see LedgerTransactionDAOImpl.java
	 */

	@GetMapping("/transactions/sum-transacted")
	public ResponseEntity<?> getAllNonDeleteLedgerTransactionsTransValSum() {
		Double sumTransactedValue = transRepository.getSumOfAllNonDeleteLedgerTransactions();
		return new ResponseEntity<>("Aggregated value of transacted value not soft deleted is " + sumTransactedValue,HttpStatus.OK);
	}
}
