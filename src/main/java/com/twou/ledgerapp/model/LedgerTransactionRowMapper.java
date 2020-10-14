package com.twou.ledgerapp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * This RowMapper implementation class maps a single record in the 'ledger' database table to
 * a single domain object 'LedgerTransaction' defined in LedgerTransaction.java.
 * @see LedgerTransaction.java
 * @author Sriranjan Tadimari
 */
public class LedgerTransactionRowMapper implements RowMapper<LedgerTransaction>{

	@Override
	public LedgerTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		LedgerTransaction transaction = new LedgerTransaction();
		transaction.setId(rs.getLong("id"));
		transaction.setSenderName(rs.getString("sender"));
		transaction.setRecipientName(rs.getString("recipient"));
		transaction.setSoftDelete(rs.getInt("soft_delete") == 0 ? false : true);
		transaction.setTransactionValue(rs.getDouble("transaction_value"));
		return transaction;
	}
	

}
