package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class File extends Model {
	
	@ManyToOne
	public Account account;
	public String name;
	public byte[] content;

	public File(Account account, String name, byte[] content){
		this.account = account;
		this.name = name;
		this.content = content;
		
		account.files.add(this);
	}
}