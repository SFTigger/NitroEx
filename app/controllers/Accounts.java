package controllers;

import play.mvc.Controller;

import models.Account;

public class Accounts extends Controller {

    public static void index() {
        render();
    }

    public static void create(String email, String password) throws Throwable{
    	Account account = Account.find("byEmail", email).first();
    	
    	if(account == null){
	    	Account a = new Account(email, password);
	    	a.save();
    	}
    	
    	redirect("/documents");
    }
}
