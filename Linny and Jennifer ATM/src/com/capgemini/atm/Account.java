package com.capgemini.atm;

import java.util.ArrayList;

public class Account {
	
	/**
	 * The name of the account.
	 */
	private String name;
	
	/** The account ID number.
	 */
	
	private String id;
	
	/** The User object that owns this account.
	 * 
	 */
	private User holder;
	
	/** The list of transactions for this account.
	
	 */
	private ArrayList<Transaction> transactions;
	
	public Account(String name, User holder, Bank theBank) {
		
		//set the account name and holder
		this.name = name;
		this.holder = holder;
		
		// get new account ID
		this.id = theBank.getNewAccountID();
		
		// initialize transactions
		this.transactions = new ArrayList<Transaction>();
		
		// add to holder and bank lists
		holder.addAccount(this);
		holder.accounts.add(this);
		theBank.addAccount(this);
		
		
	}

}
