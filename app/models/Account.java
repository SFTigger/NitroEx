package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Account extends Model {

    public String email;
    public String password;

    public Account(String email, String password){
    	this.email = email;
    	this.password = password;
    }
}