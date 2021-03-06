package com.capgemini.atm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	public String getNewUserID() {
		
		//inits
		String id;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		// continue looping until we get a unique ID
		do {
			// generate the number 
			id = "";
			for (int c = 0; c < len; c++) {
				id += ((Integer)rng.nextInt(10)).toString();
			}
		} while(nonUnique);
			
			
		return id; 
	}
	
	public String getNewAccountId() {
		
	}
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
		
		
	}
}
