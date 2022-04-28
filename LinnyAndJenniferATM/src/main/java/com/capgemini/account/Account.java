package com.capgemini.account;

import java.util.ArrayList;

import com.capgemini.bank.Bank;
import com.capgemini.transaction.Transaction;
import com.capgemini.user.User;

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
		this.id = theBank.getNewAccountId();

		// initialize transactions
		this.transactions = new ArrayList<Transaction>();

	}

	public String getId() {
		return id;
	}

	public Object getSummaryLine() {
		//get balance
		double balance = this.getBalance();
		//format summary line depending on if account is overdrawn
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.id, balance, this.name);
		}else {
			return String.format("%s : $(%.02f) : %s", this.id, balance, this.name);
		}
	}

	public double getBalance() {
		double balance = 0;
		for(Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.id);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}

	public void addTransaction(double amount, String memo) {

		// create new transaction object and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
	}

}