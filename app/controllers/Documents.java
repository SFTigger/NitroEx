package controllers;

import play.mvc.Controller;
import play.mvc.With;

import java.io.File;

import models.Account;
import models.Document;

import java.io.IOException;

@With(Secure.class)
public class Documents extends Controller {

    public static void index() {
    	String username = Security.connected();
    	Account account = Account.find("byEmail", username).first();
    	
        render(account);
    }
    
    public static void create(File file) throws IOException {
    	String username = Security.connected();
    	Account account = Account.find("byEmail", username).first();
    	
    	Document document = new Document(account, file);
    	document.save();
    	
        render(account, document);	
    }
    
    public static void confirm(long id, String tags) {
    	Document document = Document.findById(id);
    	if(document != null){
    		document.resetTags(tags);
    		document.save();
    	}
    	
    	redirect("/documents");
    }
}