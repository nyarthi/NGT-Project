package com.capgemini.atm;

import java.util.Scanner;

import com.capgemini.bank.Bank;
import com.capgemini.user.User;

public class ATM {

	public static void printUserMenu(User theUser, Scanner sc) {

		//print summary of user accounts
		theUser.printAccountsSummary();

		int choice;

		//user menu
		do {
			System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
			System.out.println(" 1) Show account transaction history");
			System.out.println(" 2) Withdrawal");
			System.out.println(" 3) Deposit");
			System.out.println(" 4) Transfer");
			System.out.println(" 5) Quit");
			System.out.println();
			System.out.println("Enter choice: ");
			choice = sc.nextInt();

			if(choice <1 || choice > 5) {
				System.out.println(" Invalid choice. Please choose 1-5.");
			}
		} while(choice < 1 || choice > 5);

		switch (choice) {
		case 1:
			ATM.showTransHistory(theUser, sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser, sc);
			break;
		case 3:
			ATM.depositFunds(theUser, sc);
			break;
		case 4: 
			ATM.transferFunds(theUser, sc);
			//ADD CURRENCY CONVERSION
		}
		//redisplay menu unless quitting
		if (choice != 5) {
			ATM.printUserMenu(theUser, sc);
		}

	}

	public static void showTransHistory(User theUser, Scanner sc) {
		int theAcct;
		//get account for transaction history view
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + "whose transactions you want to see: ", theUser.numAccounts());
			theAcct = sc.nextInt()-1;
			if(theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");

			}

		}while(theAcct <0 || theAcct >= theUser.numAccounts());

		//print trans history
		theUser.printAcctTransHistory(theAcct);
	}

	public static void transferFunds(User theUser, Scanner sc) {
		// inits
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;

		// get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" +
					"to transfer from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct <0 || fromAcct >= theUser.numAccounts()) {
				System.out.println(" Invalid choice. Please choose 1-5.");

			}
		} while (fromAcct < 0 ||fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);

		//get the account to transfer to 
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" +
					"to transfer to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct <0 || toAcct >= theUser.numAccounts()) {
				System.out.println(" Invalid choice. Please choose 1-5.");	
			}
		} while (toAcct < 0 ||toAcct >= theUser.numAccounts());

		// get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer(max $%.02f): $",
					acctBal);
			amount = sc.nextDouble();
			if (amount <0){
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than\n"+
						"balance of $%.02f.\n", acctBal);
			}

		} while (amount < 0 || amount > acctBal);

		//Finally, do the transfer
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
				"Transfer to account %s", theUser.getAccountID(toAcct)));
		theUser.addAcctTransaction(toAcct, amount, String.format(
				"Transfer to account %s", theUser.getAccountID(fromAcct)));

	}

	public static void withdrawFunds(User theUser, Scanner sc) {


		int fromAcct;
		double amount;
		double acctBal;
		String memo;

		// get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" +
					"to transfer from: ",theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct <0 || fromAcct >= theUser.numAccounts()) {
				System.out.println(" Invalid choice. Please choose 1-5.");

			}
		} while (fromAcct < 0 ||fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);

		// get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer(max $%.02f): $",
					acctBal);
			amount = sc.nextDouble();
			if (amount <0){
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than\n"+
						"balance of $%.02f.\n", acctBal);
			}

		} while (amount < 0 || amount > acctBal);

		// gobble up rest of previous input
		sc.nextLine();

		// get a memo
		System.out.println("Enter a memo: ");
		memo = sc.nextLine();

		// do the withdraw
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
	}

	public static void depositFunds(User theUser, Scanner sc) {

		//inits
		int toAcct;
		double amount;
		double acctBal;
		String memo;

		// get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" +
					"to transfer from: ",theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct <0 || toAcct >= theUser.numAccounts()) {
				System.out.println(" Invalid choice. Please choose 1-5.");

			}
		} while (toAcct < 0 ||toAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(toAcct);

		// get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer(max $%.02f): $",
					acctBal);
			amount = sc.nextDouble();
			if (amount <0){
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than\n"+
						"balance of $%.02f.\n", acctBal);
			}

		} while (amount < 0 || amount > acctBal);

		// gobble up rest of previous input
		sc.nextLine();

		// get a memo
		System.out.println("Enter a memo: ");
		memo = sc.nextLine();

		// do the withdraw
		theUser.addAcctTransaction(toAcct, amount, memo);
	}


	public static User mainMenuPrompt(Bank theBank, Scanner sc) {

		String userId;
		String pin;
		User authUser;

		do {

			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter user ID:");
			userId = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();
			
			
			
		
			//get user object to correspond with ID and pin combo
			authUser = theBank.userLogin(userId, pin);
			if(authUser == null) {
				System.out.println("Incorrect user ID/pin combination. " + "Please try again.");

			}


		}while(authUser == null);

		return authUser;
	}

}
