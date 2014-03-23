package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Documents extends Controller {

    public static void index() {
    	String user = Security.connected();
    	
        render(user);
    }
}