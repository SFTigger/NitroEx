package controllers;

import play.mvc.Controller;

public class Home extends Controller {

    public static void index() {
        render("/main.html");
    }
    
    public static void logout() throws Throwable {
    	Secure.logout();
    	render("/main.html");
    }

}