package a3_secondTry;

//-----------------------------------
// Assignment 3
// Written by: Fouad Elian 40273045
//-----------------------------------


import java.util.ArrayList;

public class Vocab {
	 private String topic; 
	 private wordLinked words;
	 public Vocab() {
		 
	 }
	public Vocab(String topic, wordLinked words) {
		this.topic = topic;
		this.words = words;
	}
	public String toString() {
		String printer =topic+"\n";
		for(int i=0;i<words.getCounter();i++) {
			printer+=words.getNodeAtIndex(i)+"\n";
		}
		return printer;
		
	}
	/**
	 * Returns the wordLinked object.
	 * @return the wordLinked object
	 */
	public wordLinked getWord(){
		return this.words;
	}
	/**
	 * Returns the topic string without the leading '#' character, if present.
	 * If the topic string starts with '#', the '#' character is removed.
	 * Otherwise, returns the topic string as is.
	 * @return the topic string without the leading '#' character, if present
	 */
	public String getTopic() {
		if(this.topic.charAt(0)=='#') {
		String no_hash = this.topic.substring(1, this.topic.length());
		return no_hash;
		}
		else {
			return this.topic;
		}
	}
	
	/**
	 * Checks if the given word is present .
	 * @param word the word to check for presence 
	 * @return true if the word is present, false otherwise
	 */
	public boolean isIn(String word) {
		boolean checker =true;
		for(int i=0;i<this.words.getCounter();i++) {
			if(this.words.getNodeAtIndex(i).equals(word)) {
				checker=false;
				
			}
		}
		if(checker) {
			return false;
		}
		else {
			return true;
		}
		}
	
	/**
	 * Adds a word  if it's not already present.
	 * If the word is already present, prints a message indicating so.
	 * @param word the word to add 
	 */
	public void addWord(String word) {
		if(this.isIn(word)) {
			System.out.println("this word is already available");
		}
		else {
			this.words.add(word);
			// add here a method sort to sort the word after adding it.
		}
		
	}
	
	/**
	 * Removes the specified word .
	 * @param word the word to be removed
	 */
	public void removeWord(String word) {
	    boolean wordFound = false; 
	    
	   
	    for (int i=0;i<this.words.getCounter();i++) {
	        if (this.words.getNodeAtIndex(i).equals(word)) { 
	            this.words.remove(this.words.getNodeAtIndex(i)); 
	            wordFound = true; 
	            break; 
	        }
	    }

	   
	    if (!wordFound) {
	        System.out.println("Sorry, there is no word: " + word);
	    }
	}
	/**
	 * Replaces occurrences of a specified word with a new word 
	 * If the specified word is not found , prints a message indicating so.
	 * @param replace_word the word to be replaced
	 * @param new_word the new word to replace occurrences of the specified word
	 */
	public void replaceWord(String replace_word,String new_word) {
		if (this.isIn(replace_word)) {
            this.words.set(replace_word,new_word);
		}
		else {
			System.out.println("The word you chose to replace is unfortunatly not in the current topic, use option 6 to see in which topic it is ");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
