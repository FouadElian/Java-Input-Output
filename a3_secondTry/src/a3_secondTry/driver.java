
package a3_secondTry;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/*
 * this program is used by a professor for a library of vocab words.
 * The professor will be able to get the library from a file that he already has the information on, or he can just start from the beginning.
 * The professor can add different topics, and different words.
 * words are related to the topics.
 * Each topic has it's own set of words.
 * Professor can go throught all the topics to find where a specific word is , and can find all the words starting with a given letter.
 */

public class driver {
	
	
	/**
	 * Saves the contents of a linked list to a file.
	 * 
	 * @param saves The linked list containing the data to be saved.
	 * @param file The name of the file to which the data will be saved.
	 */
	
	public static void Save_file(myLinkedList saves,String file) {
		PrintWriter outputstream=null;
		try{
			 outputstream = new PrintWriter(new FileOutputStream(file),true);
			for(int i=0;i<saves.getCounter();i++) {
				if(i==0) {
				outputstream.write("#"+saves.getNodeAtIndex(i).getTopic()+"\n");
				}
				else {
					outputstream.write("\n#"+saves.getNodeAtIndex(i).getTopic()+"\n");
				}
				for(int j=0;j<saves.getNodeAtIndex(i).getWord().getCounter();j++) {
					outputstream.write(saves.getNodeAtIndex(i).getWord().getNodeAtIndex(j)+"\n");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		outputstream.close();
	}
	
	/**
	 * Retrieves the contents of a file and returns it as a formatted string.
	 * Each line of the file is appended to the result string along with its line number.
	 * If the file does not exist or is empty, a message indicating the absence of files is returned.
	 * 
	 * @param filename The name of the file to retrieve the contents from.
	 * @return A string containing the contents of the file, formatted with line numbers.
	 */
	public static String file_getter(String filename) {
		Scanner file_getter=null;
		String menu ="";
		int counter =0;
		try {
			file_getter = new Scanner(new FileInputStream(filename));
			while(file_getter.hasNextLine()) {
				menu+=counter+" " + file_getter.nextLine()+"\n";
				counter++;
			}
			if(counter==0) {
				menu+="There is no files created";
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		return menu;
		
	}
	
	
	
	
	
	
	/**
	 * Formats a list of strings into a grid with a fixed number of words per line,
	 * where each word is left-padded with spaces to match the length of the longest word.
	 * 
	 * @param letters The list of strings to be formatted into a grid.
	 * @return A string containing the formatted grid of words.
	 */
	
	public static String letter_shower(ArrayList<String> letters) {
		  String result = "";
		    int maxLength = 0;

		    // Find the maximum length of the words
		    for (String word : letters) {
		        maxLength = Math.max(maxLength, word.length());
		    }

		    int counter = 1;
		    for (String word : letters) {
		        // Pad the word with spaces to match the maximum length
		        String paddedWord = String.format("%-" + maxLength + "s", word);
		        result += counter + ": " + paddedWord + "\t";

		        // Add a newline after every 4th word
		        if (counter % 4 == 0) {
		            result += "\n";
		        }

		        counter++;
		    }

		    // Remove the trailing newline if there are not exactly 4 words per line
		    if ((counter - 1) % 4 != 0) {
		        result += "\n";
		    }

		    return result;
	}
	/**
	 * Creates a new Vocab object with the specified type and list of words.
	 * 
	 * @param type The type of Vocab.
	 * @param words The linked list of words belonging to the Vocab.
	 * @return A Vocab object initialized with the specified type and list of words.
	 */
	public static Vocab Vocab_obj(String type, wordLinked words) {
	    Vocab obj = new Vocab(type, words);
	    return obj;
	}
	
	/**
	 * Writes a string to a specified file, appending it to the end of the file.
	 * 
	 * @param nameFileAdd The string to be written to the file.
	 */
	public static void Write_to_file(String nameFileAdd) {
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileOutputStream("file_inventory.txt", true), true);
			out.write(nameFileAdd+"\n");
			
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		out.close();
	}
	
	/**
	 * Checks if a specified topic exists in the linked list.
	 * 
	 * @param list The linked list to search for the topic.
	 * @param topic The topic to search for.
	 * @return true if the topic is found in the linked list, false otherwise.
	 */
	public static boolean Isin(myLinkedList list, String topic) {
	    boolean checker = false;
	    String normalizedTopic = topic.replaceAll("\\s+", ""); // Remove spaces from the input topic
	    for (int i = 0; i < list.getCounter(); i++) {
	        String currentTopic = list.getNodeAtIndex(i).getTopic().replaceAll("\\s+", ""); // Remove spaces from the current topic
	        if (normalizedTopic.equals(currentTopic)) {
	            checker = true;
	            break; // Exit the loop if a match is found
	        }
	    }
	    return checker;
	}

	
	
	
	/**
	 * Reads data from a file and constructs a linked list of Vocab objects.
	 * Each Vocab object consists of a type (identified by lines starting with '#') and a list of words.
	 * 
	 * @param file The name of the file to read data from.
	 * @return A linked list containing Vocab objects constructed from the data in the file.
	 */
	
	public static myLinkedList read_file(String file) {
		Scanner reader =null;
		String type="";
		myLinkedList obj_array = new myLinkedList();
		int counter=0;
		Vocab obj = null;
		wordLinked words = new wordLinked();
		
		try {
			reader= new Scanner(new FileInputStream(file));
			while(reader.hasNextLine()) {
				String final2 =reader.nextLine();
				if(!final2.isEmpty() && final2.charAt(0)=='#'){
					type=final2;
					
				}
				else if(!final2.isEmpty()) {
				words.add(final2);
				
				}
				
				else if(final2.isEmpty() || !reader.hasNextLine()) {
					if(counter==0) {
						
						obj_array.addHead(Vocab_obj(type,words));
						counter++;
					}
					else if(counter==1) {
						obj_array.addEnd(Vocab_obj(type,words)); 
						
						counter++;
					}
					else if(counter==2) {
						
					obj_array.addEnd(Vocab_obj(type,words));
					
						counter++;
					}
					else if(counter==3) {
						
						obj_array.addEnd(Vocab_obj(type,words));
						
						counter++;
							
					}
				
						
					
					
					words=new wordLinked();
				}
				
					
					
				}
			
			obj_array.addEnd(Vocab_obj(type,words));
			
			System.out.println("Done Loading");
			
			
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		return obj_array;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int counter=0;
		boolean checker =true;
		myLinkedList topics = new myLinkedList();
		String modify ="------------------------"
				+ "\n        Modify Topics Menu\n"
				+ "------------------------\n"
				+ "a add a word\n"
				+ "r remove a word\n"
				+ "c change a word\n"
				+ "0 Exit"
				+ "\nEnter your choice: ";
		String pick ="------------------------"
				+ "\n        Pick a topic\n"
				+ "------------------------\n";
		String main_menu="---------------------------------"
				+ "\n        Vocabulary Center\n"
				+ "---------------------------------"
				+ "\n 1 browse a topic"
				+ "\n 2 insert a new topic before another one"
				+ "\n 3 insert a new topice after another one"
				+ "\n 4 remove a topic"
				+ "\n 5 modify a topic"
				+ "\n 6 search topics for a word"
				+ "\n 7 load from a file"
				+ "\n 8 show all words starting with a given letter"
				+ "\n 9 save to file"
				+ "\n 0 exit"
				+ "\n---------------------------"
				+ "\n Enter your choice: ";
		
		System.out.println("Weclome to Fouad's Vocab Library ");
		
		while(checker ) {
		
			System.out.print(main_menu);
			int input1 = input.nextInt();
			if(input1==1) {
				boolean checker5=true;
				while(checker5) {
				System.out.println(pick);
				topics.display();
				System.out.println("0 Exit");
				System.out.println("-------------------------");
				System.out.print("Enter your choice: ");
				int input2=input.nextInt();
				if(input2==0) {
					checker5=false;
				}
				else if(input2>topics.getCounter()) {
					System.out.println("Option out of range, Please Try Again!");
				}
				else {
				System.out.println("Topic: " + topics.getNodeAtIndex(input2-1).getTopic());
				System.out.println(topics.getNodeAtIndex(input2-1).getWord().display());
				checker5=false;
				}
				
				}
			}
			else if(input1==2) {
				wordLinked words_new = new wordLinked();
				System.out.println(pick);
				topics.display();
				System.out.println("0 Exit");
				System.out.println("-------------------------");
				if(topics.getCounter()==0) {
					System.out.println("Your topics seem empty, start by adding the Topic: ");
					input.nextLine();
					String topic_name = input.nextLine();
					if(topic_name.equals("0")) {
						
					}
					else {
					System.out.println("Enter a word - to quit press Enter: ");
					String words_name = input.nextLine();
					while(!words_name.isEmpty()) {
						words_new.add(words_name);
						words_name = input.nextLine();
						
					}
					Vocab obj = Vocab_obj(topic_name,words_new);
					topics.addHead(obj);
					}
				}
				else {
				System.out.print("Enter your choice: ");
				int input2_0=input.nextInt();
				System.out.println("Enter a topic name: ");
				input.nextLine();
				String topic_name = input.nextLine();
				if(Isin(topics,topic_name)) {
					System.out.println("Topic name already exists please enter a new one: ");
					topic_name=input.nextLine();
				}
				System.out.println("Enter a word - to quit press Enter: ");
				String words_name = input.nextLine();
				while(!words_name.isEmpty()) {
					words_new.add(words_name);
					words_name = input.nextLine();
					
				}
				Vocab obj = Vocab_obj(topic_name,words_new);
				topics.addBefore(topics.getNodeAtIndex(input2_0-1), obj);
				words_new= new wordLinked();
				}
				}
			else if(input1==3) {
				wordLinked words_new2 = new wordLinked();
				System.out.println(pick);
				topics.display();
				System.out.println("0 Exit");
				System.out.println("-------------------------");
				if(topics.getCounter()==0) {
					System.out.println("Your topics seem empty, start by adding the Topic: ");
					input.nextLine();
					String topic_name = input.nextLine();
					if(topic_name.equals("0")) {
						
					}
					else {
					System.out.println("Enter a word - to quit press Enter: ");
					String words_name = input.nextLine();
					while(!words_name.isEmpty()) {
						words_new2.add(words_name);
						words_name = input.nextLine();
						
					}
					Vocab obj = Vocab_obj(topic_name,words_new2);
					topics.addHead(obj);
					}
				}
				else {
				System.out.print("Enter your choice: ");
				int input3=input.nextInt();
				System.out.println("Enter a topic name: ");
				input.nextLine();
				String topic_name2 = input.nextLine();
				if(Isin(topics,topic_name2)) {
					System.out.println("Topic name already exists please enter a new one: ");
					topic_name2=input.nextLine();
				}
				System.out.println("Enter a word - to quit press Enter: ");
				String words_name2 = input.nextLine();
				while(!words_name2.isEmpty()) {
					words_new2.add(words_name2);
					words_name2 = input.nextLine();
				}
				
				Vocab obj = Vocab_obj(topic_name2,words_new2);
				topics.addAfter(topics.getNodeAtIndex(input3-1), obj);
				words_new2= new wordLinked();
				}
			}
			else if(input1==4) {
				System.out.println(pick);
				topics.display();
				System.out.println("0 Exit");
				System.out.println("-------------------------");
				System.out.print("Enter your choice: ");
				int input4=input.nextInt();
				topics.remove(topics.getNodeAtIndex(input4-1));
			}
			else if(input1==5) {
				System.out.println(pick);
				topics.display();
				System.out.println("0 Exit");
				System.out.println("-------------------------");
				System.out.print("Enter your choice: ");
				int input5=input.nextInt();
				System.out.println(modify);
				 input.nextLine();
				String input5_0 = input.nextLine();
				if(input5_0.equals("a")) {
					System.out.println("Type a word and press Enter, or press Enter to end input");
					String words_add = input.nextLine();
					topics.getNodeAtIndex(input5-1).addWord(words_add);
				}
				else if(input5_0.equals("r")) {
					System.out.println("Enter a word: ");
					String words_add2 = input.nextLine();
					topics.getNodeAtIndex(input5-1).removeWord(words_add2);
				}
				else if(input5_0.equals("c")) {
					System.out.println(topics.getNodeAtIndex(input5-1).getWord().display());
					System.out.println("Enter the the word you want to replace (Not the number): ");
					String input5_1 = input.nextLine();
					System.out.println("Enter your new word: ");
					String word_replace = input.nextLine();
					topics.getNodeAtIndex(input5-1).replaceWord(input5_1, word_replace);
				}
				
			}
			
			else if (input1 == 6) {
			    boolean searchCompleted = false;
			    String topics_finder="";
			    while (!searchCompleted) {
			    	
			        System.out.print("Enter the word: ");
			        input.nextLine();
			        String word_search = input.nextLine();
			        for (int i = 0; i < topics.getCounter(); i++) {
			            if(topics.getNodeAtIndex(i).isIn(word_search)) {
			            	searchCompleted = true;
			            	topics_finder+=topics.getNodeAtIndex(i).getTopic()+", ";
			                
			            }
			        }
			        if (!searchCompleted) {
			            System.out.println(word_search + " is not available in any of the " + topics.getCounter() + " topics");
			            searchCompleted =true;
			        }
			        else {
			        	System.out.println(word_search + " is in the following topic(s): " + topics_finder.substring(0, topics_finder.length()-2));
			        }
			    }
			}
			
			else if(input1==7){
				System.out.println("Enter the name of the file: ");
				input.nextLine();
				String input7=input.nextLine();
				topics = read_file(input7);
					
				
			}
			else if(input1==8) {
				System.out.println("Please enter a given letter: ");
				String letter = input.next();
				char letter_char = letter.charAt(0);
				
				ArrayList<String> letters = new ArrayList<String>();
				
				 for (int i = 0; i < topics.getCounter(); i++) {
			           for(int j =0;j<topics.getNodeAtIndex(i).getWord().getCounter();j++) {
			            	if(topics.getNodeAtIndex(i).getWord().getNodeAtIndex(j).charAt(0)== letter_char && !letters.contains(topics.getNodeAtIndex(i).getWord().getNodeAtIndex(j))) {
			            		letters.add(topics.getNodeAtIndex(i).getWord().getNodeAtIndex(j));
			            	}
			            	}
			            }
				 letters.sort(null);
				 System.out.println("these are the following words that starts witht the letter: "+letter_char);
				 System.out.println(letter_shower(letters));
				 
			        }
			else if(input1==9) {
			
				System.out.println("---------------------------------------"
						+ "\n              Files Created Menu"
						+ "\n---------------------------------------");
						
				
				System.out.println(file_getter("file_inventory.txt"));
				System.out.println("---------------------------------------");
				
				if(file_getter("file_inventory.txt").equals("There is no files created")) {
					System.out.println("Please enter the name of the file you want to add to(put _ if you want to use multiple words): ");
					String file_name = input.next();
					Write_to_file(file_name);
					Save_file(topics,file_name);
				}
				else {
					System.out.println("What would you like to do ? "
							+ "\n1-Save to a New file"
							+ "\n2-Save to an existing file ");
					int input9_0=input.nextInt();
					if(input9_0==1) {
						System.out.println("Please enter the name of the file you want to add to(put _ if you want to use multiple words): ");
						String file_name2=input.next();
						Write_to_file(file_name2);
						Save_file(topics,file_name2);
					}
					else if(input9_0==2) {
						System.out.println("Please enter the name of the file you want to save to: ");
						String file_name3=input.next();
						Save_file(topics,file_name3);
					}
					
					
				}
				
				
				
			
				
				
				
					
			}
			else if(input1==0) {
				checker=false;
				
			}
			
			
			}
		System.out.println("Thank you for using our library");
		System.exit(0);
		
	
	}
		
		
		
		
		
		
		
		
		
	

}
