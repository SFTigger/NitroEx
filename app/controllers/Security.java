package controllers;

import models.Account;

public class Security extends Secure.Security {

	public static boolean authentify(String username, String password){
		
		// TODO -- fuss with uniqueness on email address. 
		
		Account account = Account.find("byEmail", username).first();
		
		return account != null && account.password.equals(password);
	}
}