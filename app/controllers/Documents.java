package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Security.class)
public class Documents extends Controller {

    public static void index() {
    	String user = Security.connected();
    	
    	renderArgs.put("user", user);
        render();
    }
}