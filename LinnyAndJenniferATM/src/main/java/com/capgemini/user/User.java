package com.capgemini.user;

import java.util.ArrayList;

import com.capgemini.account.Account;
import com.capgemini.bank.Bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {

	/**
	 * The first name of the user.
	 */
	private String firstName;

	/**The last name of the user.*
	 * 
	 */
	private String lastName;
	/**
	 * The ID number of the user.
	 * 
	 */
	private String id;

	/**
	 * The MD5 hash of the user's pin number.
	 */
	private byte pinHash[];

	/**
	 * The list of accounts for this user.
	 * 
	 */

	public ArrayList<Account> accounts;

	/**
	 * Create a new user
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @param theBank
	 */

	public User(String firstName, String lastName, String pin, Bank theBank) {

		// set user's name

		this.firstName = firstName;
		this.lastName = lastName;

		// store the pin's MD5 hash, rather than the original value, for
		// security reason

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}

		// get a new, unique universal ID for the user
		this.id = theBank.getNewUserID();

		// create empty list of accounts
		this.accounts = new ArrayList<Account>();

		// print log message 
		System.out.printf("New user %s, %s with ID %s created.\n", lastName,
				firstName, this.id);

		/**Add an account for the user
		 * @param anAcct the account to add
		 */


	}

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);

	}
	public String getId() {
		return id;
	}

	public boolean validatePin(String aPin) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}

		return false;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a<this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
		
	}

	public int numAccounts() {
		return this.accounts.size();
	}

	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
		
	}
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	public String getAccountID(int acctIdx) {
		return this.accounts.get(acctIdx).getId();
	
	}
	
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
		
	}

}