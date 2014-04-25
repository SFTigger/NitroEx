package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.*;

import java.io.*;

@Entity
public class Document extends Model {
	
	@ManyToMany
	Set<Tag> tags = new HashSet<Tag>();

	@ManyToOne
	Account owner;
	
	private String name;
	
	public Document(){
		// NO-OP, for Hibernate
	}
	
	public Document(Account owner, File content) throws IOException {
		this.owner = owner;
		
		this.name = content.getName();
		
		Map<String, Integer> tokenCounts = new HashMap<String, Integer>();
		
		BufferedReader in = 
		    new BufferedReader(
		        new InputStreamReader(
		            new BufferedInputStream(
		                new FileInputStream(content))));
		
		String line = in.readLine();
		while(line != null){
			String[] tokens = line.split(" ");
			for(String token: tokens){
				token = token.trim().toLowerCase();
				
				Integer count = tokenCounts.get(token);
				if(count != null){
					tokenCounts.put(token, count + 1);
				}
				else {
					tokenCounts.put(token, 1);
				}
			}
			
			line = in.readLine();
		}
		
		in.close();
		
		for(Map.Entry<String, Integer> entry: tokenCounts.entrySet()){
			String token = entry.getKey();
			int count = entry.getValue();
			
			if(count > 1){
				Tag tag = Tag.find("byValue", token).first();
				if(tag == null){
					tag = new Tag(token);
					tag.save();
				}
				
				tags.add(tag);
				tag.documents.add(this);
			}
		}
	}
	
	public void resetTags(String tagValuesString){
		for(Tag t: tags){
			t.documents.remove(this);
		}
	    tags.clear();
		
		
		String[] tagValues = tagValuesString.split(" ");
		for(String tagValue: tagValues){
			tagValue = tagValue.trim().toLowerCase();
			if(!tagValue.equals("")){
				Tag tag = Tag.find("byValue", tagValue).first();
				if(tag == null){
					tag = new Tag(tagValue);
					tag.save();
				}
				
				tags.add(tag);
				tag.documents.add(this);
			}
		}
	}
}