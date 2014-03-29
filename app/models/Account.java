package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;

@Entity
public class Account extends Model {

    public String email;
    public String password;
    
    @OneToMany(mappedBy="account")
    public Set<File>files = new HashSet<>();

    public Account(String email, String password){
    	this.email = email;
    	this.password = password;
    }
}