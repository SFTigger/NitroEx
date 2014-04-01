package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import java.io.UnsupportedEncodingException;

import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import java.util.Collections;

@Entity
public class File extends Model {
	
	@ManyToOne
	public Account account;
	public String name;
	public byte[] content;
	
	@ManyToMany
	public Set<Tag> suggested = new HashSet<Tag>();
	
	@ManyToMany
	public Set<Tag> accepted = new HashSet<Tag>();

	public File(Account account, String name, byte[] content){
		this.account = account;
		this.name = name;
		this.content = content;
		
		account.files.add(this);
	}
	
	void addSuggestion(Tag suggestion){
		suggested.add(suggestion);
		suggestion.suggestedFor.add(this);
	}
	
	public List<Tag> extractSuggestions(){
		try{
			Map<String, Integer> suggestions = new HashMap<String, Integer>();
			
			// this is a bald-faced assumption!
			String rawData = new String(content, "UTF-8");
			String[] tokens = rawData.split(" ");
			for(String token: tokens){				
				token = token.trim();
				token = token.toLowerCase();
				
				System.out.println(token);
				
				if(!token.equals("")){
					Integer count = suggestions.get(token);
					if(count != null){
						suggestions.put(token, count.intValue() + 1);
					}
					else{
						suggestions.put(token, 1);
					}
				}
			}
			
			
			List<Tag> tags = new ArrayList<Tag>();
			for(Map.Entry<String, Integer> entry: suggestions.entrySet()){
				String name = entry.getKey();
				int value = entry.getValue();
				
				if(value > 1){
					Tag tag = Tag.find("byValue", id).first();
					if(tag == null){
						tag = new Tag(name);
						tag.save();
					}
					
					tags.add(tag);
				}
			}
			
			return tags;
		}
		catch(UnsupportedEncodingException e){
			// TODO -- log and complain, loudly. 
			// If UTF-8 is unavailable, indicates a truly serious state of affairs.
			return Collections.EMPTY_LIST;
		}
	}
}