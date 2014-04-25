package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.Set;
import java.util.HashSet;

@Entity
public class Tag extends Model {
	
	@ManyToMany
	Set<Document> documents = new HashSet<Document>();

	public String value;
	
	public Tag(String value){
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Tag){
			Tag other = (Tag)obj;
			
			return value.equals(other.value);
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return value.hashCode();
	}
}