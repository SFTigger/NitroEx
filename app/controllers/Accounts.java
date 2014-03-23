package controllers;

import play.mvc.Controller;

public class Accounts extends Controller {

    public static void index() {
        render();
    }

    public static void create(String email, String password){
    	Account a = new Account(email, password);
    	a.save();
    	
    	// TOOD -- forward onto account view page
    }
}
