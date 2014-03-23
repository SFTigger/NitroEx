package controllers;

import play.mvc.Controller;
import play.mvc.With;
import play.mvc.Secure;

@With(Secure.class)
public class Documents extends Controller {

    public static void index() {
        render();
    }


}