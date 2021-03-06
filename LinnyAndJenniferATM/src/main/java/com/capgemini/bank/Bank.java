package com.capgemini.bank;

import java.util.ArrayList;
import java.util.Random;

import com.capgemini.account.Account;
import com.capgemini.user.User;

public class Bank {

	private String name;

	private ArrayList<User> users;

	private ArrayList<Account> accounts;

	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}

	public String getNewUserID() {

		//inits
		String id;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;

		// continue looping until we get a unique ID
		do {
			// generate the number 
			id = "";
			for (int c = 0; c < len; c++) {
				id += ((Integer)rng.nextInt(10)).toString();
			}

			//check to make sure it's unique
			nonUnique = false;
			for (User u : this.users) {
				if (id.compareTo(u.getId()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);


		return id; 
	}

	public String getNewAccountId() {
		//inits
		String id;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique;

		// continue looping until we get a unique ID
		do {
			// generate the number 
			id = "";
			for (int c = 0; c < len; c++) {
				id += ((Integer)rng.nextInt(10)).toString();
			}

			//check to make sure it's unique
			nonUnique = false;
			for (Account a : this.accounts) {
				if (id.compareTo(a.getId()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);


		return id; 
	}

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	public User addUser(String firstName, String lastName, String pin) {
		//create user object and add to list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		//create saving account for user
		//add user and bank accounts
		Account newAccount = new Account("Savings", newUser, this);

		newUser.addAccount(newAccount);

		this.accounts.add(newAccount);

		return newUser;
	}

	public User userLogin(String userID, String pin) {
		//search through users
		for(User u: this.users ) {
			if(u.getId().compareTo(userID)==0 && u.validatePin(pin)) {
				return u;
			}
		}
		//if no user or wrong pin
		return null;
	}

	public String getName() {
		return this.name;
	}
}