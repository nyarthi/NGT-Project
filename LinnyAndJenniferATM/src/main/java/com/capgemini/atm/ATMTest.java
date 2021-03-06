package com.capgemini.atm;

import java.util.Scanner;

import com.capgemini.account.Account;
import com.capgemini.bank.Bank;
import com.capgemini.user.User;

public class ATMTest {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Bank theBank = new Bank("Bank Name");
		
		User aUser = theBank.addUser("Jen", "Smart", "1234");
		
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while (true) {
			//stay in login until successful login
			curUser = ATM.mainMenuPrompt(theBank, sc);
			
			ATM.printUserMenu(curUser, sc);
		}

	}

}