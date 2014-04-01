package controllers;

import models.Account;
 
public class Security extends Secure.Security {
    
    static boolean authenticate(String username, String password) {    	
        Account account = Account.find("byEmail", username).first();
        return account != null && account.password.equals(password);
    }    
    
}