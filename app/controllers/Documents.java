package controllers;

import play.mvc.Controller;
import play.mvc.With;

import models.Account;

@With(Secure.class)
public class Documents extends Controller {

    public static void index() {
    	String username = Security.connected();
    	Account account = Account.find("byEmail", username).first();
    	
        render(account);
    }
}