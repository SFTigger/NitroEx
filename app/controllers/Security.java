package controllers;

import models.Account;

public class Security extends Secure.Security {

	public static boolean authentify(String username, String password){
		Account account = Account.find("byEmail", username);
		
		return account != null && account.password.equals(password);
	}
}