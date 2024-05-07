package a3_secondTry;

//-----------------------------------
// Assignment 3
// Written by: Fouad Elian 40273045
//-----------------------------------




public class wordLinked {

	 private Node head;
	 private int counter;

	    
	    public int getCounter() {
	    	return this.counter;
	    }
	    
	    public wordLinked() {
	    	this.head=null;
	    	this.counter=0;
	    }
	   
	    public void addHead(String obj) {
	    	head = new Node(obj,head);
	    	counter++;
	    }
	    
	    
	    /**
	     * Compares two strings in a case-insensitive manner.
	     * If the length of word1 is greater than word2, it takes a substring of word1 with the same length as word2,
	     * converts both substrings to lowercase, and compares them lexicographically.
	     * If the length of word2 is greater than word1, it takes a substring of word2 with the same length as word1,
	     * converts both substrings to lowercase, and compares them lexicographically.
	     * If the lengths of the two words are equal, it converts both words to lowercase and compares them lexicographically.
	     *
	     * @param word1 the first string to be compared
	     * @param word2 the second string to be compared
	     * @return true if word1 comes before word2 in lexicographical order (case-insensitive), false otherwise
	     */
	    public boolean compared(String word1, String word2) {
	    	String taker="";
	    	if(word1.length()>word2.length()) {
	    		taker=word1.substring(0, word2.length()).toLowerCase();
	    		if(taker.compareTo(word2.toLowerCase())>0) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    	else if(word2.length()>word1.length()){
	    		taker=word2.substring(0, word1.length()).toLowerCase();
	    		if(word1.toLowerCase().compareTo(taker)>0) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    	else {
	    		if(word1.toLowerCase().compareTo(word2.toLowerCase())>0) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    	
	    	
	    	
	    	
	    	
	    }
	    
	    /**
	     * Adds a new node containing the specified object to the linked list.
	     * If the list is empty, the new node becomes the head of the list.
	     * If the specified String comes before the current head's data in lexicographical order (case-insensitive),
	     * the new node is inserted before the head.
	     * Otherwise, the method traverses the list to find the correct position to insert the new node,
	     * maintaining alphabetical order (case-insensitive).
	     *
	     * @param obj the object to be added to the linked list
	     */
	    
	    public void add(String obj) {
	    	if(head==null) {
	    		 addHead(obj);
	    	}
	    	else if(obj.toLowerCase().compareTo(head.data.toLowerCase())<0) {
	    		addBefore(head.data,obj);
	    	}
	    	else {
	    		Node current;
	    		Node pos = head;
	    		while(pos.next != null && compared(obj,pos.next.data)) {
	    			current=pos;
	    			pos = pos.next;
	    		}
	    		
	    		if(pos.next==null) {
	    		Node next_value = new Node(obj,null);
	    		pos.next=next_value;
	    		counter++;
	    		}
	    		else {
	    			Node next_value = new Node(obj,null);
	    			next_value.next=pos.next;
	    			pos.next=next_value;
	    			counter++;
	    		}
	    	}
	    }
	    /**
	     * Inserts a new node containing the specified value after the node with the reference value.
	     * If the reference value does not exist in the linked list, no insertion is performed.
	     * 
	     * @param referenceValue The value after which the new node will be inserted.
	     * @param value The value to be inserted into the linked list.
	     */
	    public void addAfter(String referenceValue, String value) {

	        Node position = head;

	        while (position != null && !position.data.equals(referenceValue)) {
	            position = position.next;
	        }

	        if (position == null) { // value does not exist
	            System.out.println("Reference value does not exist");
	        	return;
	        } else {
	            Node n = new Node(value, position.next);
	            position.next = n;
	            counter++;
	        }

	    }
	    /**
	     * Removes the head node from the linked list.
	     * If the linked list is empty, it prints a message indicating that it has no head.
	     */
	    
	    public void removeHead() {
	    
	    	if (head != null) {
	            Node temp = head;
	            head = head.next;
	            counter--;
	            temp.next = null;
	           
	        } else {
	            System.out.println("The LinkedList is empty, it has no head ");
	        }
	       
	     
	    }
	    
	    /**
	     * Removes the last node from the linked list.
	     * If the linked list is empty, it prints a message indicating that it is empty.
	     * If the linked list contains only one node, the removeHead method is called to remove it.
	     */
	    public void removeLast() {
	        if (head == null) {
	        	System.out.println("the LinkedList is empty");
	        } else if (head.next == null) {
	           removeHead();
	        } else {
	            Node position = head;
	            while (position.next.next != null) {
	                position = position.next;
	            }
	            Node temp = position.next;
	            position.next = null;
	            counter--;
	            
	        }
	    }
	    /**
	     * Removes the first occurrence of the specified object from the linked list.
	     * If the linked list is empty, it prints a message indicating that it is empty.
	     * If the specified object is found at the head of the linked list, the removeHead method is called to remove it.
	     * If the specified object is found elsewhere in the linked list, it is removed from the list.
	     * If the specified object is not found in the linked list, a message indicating its absence is printed.
	     * 
	     * @param obj The object to be removed from the linked list.
	     */
	    public void remove(String obj) {
	    	if(head==null) {
	    		System.out.println("the LinkedList is empty");
	    	}
	    	if(head.data.equals(obj)) {
	    		removeHead();
	    	}
	    	else {
	    	Node pos = head;
	    	Node current=pos;
	    	while(pos!=null && !pos.data.equals(obj)) {
	    		current=pos;
	    		pos = pos.next;
	    		
	    	}
	    	
	    	if(pos == null) {
	    		System.out.println("There is no element with that value");
	    	}
	    	else {
	    		current.next=pos.next;
	    		pos=null;
	    		counter--;
	    	}
	    	}
	    }
	    /**
	     * Inserts a new node containing the specified object before the node with the reference object.
	     * If the linked list is empty, it prints a message indicating that it is empty and cannot add before.
	     * If the specified reference object is found at the head of the linked list, the addHead method is called to insert the new node.
	     * If the specified reference object is found elsewhere in the linked list, the new node is inserted before it.
	     * If the specified reference object is not found in the linked list, a message indicating its absence is printed.
	     * 
	     * @param obj_ref The object before which the new node will be inserted.
	     * @param obj The object to be inserted into the linked list.
	     */ 
	    public void addBefore(String obj_ref, String obj) {
	        if (head == null) {
	            System.out.println("List is empty. Cannot add before.");
	            return;
	        }

	        
	        if (head.data.equals(obj_ref)) {
	            addHead(obj);
	            return;
	        }

	        Node prev = null;
	        Node current = head;
	        while (current != null && !current.data.equals(obj_ref)) {
	            prev = current;
	            current = current.next;
	        }

	        if (current == null) {
	            System.out.println("There is no such value to add before.");
	        } else {
	            Node newNode = new Node(obj, current);
	            if (prev != null) {
	                prev.next = newNode;
	            } else {
	                head = newNode; 
	            }
	            counter++;
	        }
	    }

	    /**
	     * Displays the elements of the linked list in a formatted manner.
	     * Each element is padded with spaces to match the length of the longest element.
	     * Elements are displayed in rows of four, with a newline after every fourth element.
	     * 
	     * @return A string containing the formatted representation of the linked list elements.
	     */
	    public String display() {
	    	 String result = "";
	 	    int maxLength = 0;

	 	    // Find the maximum length of the words
	 	    for (int i=0;i<this.counter;i++) {
	 	    	
	 	        maxLength = Math.max(maxLength, this.getNodeAtIndex(i).length());
	 	    }

	 	    int counter2 = 1;
	 	    for (int i=0;i<this.counter;i++) {
	 	        
	 	        String paddedWord = String.format("%-" + maxLength + "s",this.getNodeAtIndex(i) );
	 	        result += counter2 + ": " + paddedWord + "\t";

	 	        
	 	        if (counter2 % 4 == 0) {
	 	            result += "\n";
	 	        }

	 	        counter2++;
	 	    }

	 	    
	 	    if ((counter2 - 1) % 4 != 0) {
	 	        result += "\n";
	 	    }

	 	    return result;
	    }
	    
	    /**
	     * Retrieves the words of the node at the specified index in the linked list.
	     * If the linked list is empty or the index is negative, null is returned.
	     * 
	     * @param index The index of the node to retrieve.
	     * @return The data of the node at the specified index, or null if the index is out of bounds or the linked list is empty.
	     */
	    
	    public String getNodeAtIndex(int index) {
	        if (head == null || index < 0) {
	            return null;
	        }

	        Node current = head;
	        int currentIndex = 0;

	        while (current != null) {
	            if (currentIndex == index) {
	            	return current.data; 
	            }
	            current = current.next;
	            currentIndex++;
	        }

	       
	        return null;
	    }
	    
	    /**
	     * Replaces the data of the first occurrence of the specified object in the linked list with the specified new value.
	     * 
	     * @param obj The object whose data is to be replaced.
	     * @param changed The new value to set for the specified object.
	     */
	    public void set(String obj,String changed) {
	    	
	    	Node current = head;
	    	Node previous =null;
	    	while(!current.data.equals(obj)) {
	    		previous=current;
	    		current = current.next;
	    	}
	    	Node n_new = new Node(changed,current.next);
	    	previous.next=n_new;
	    }

  private class Node {
        
        private String data;
        private Node next;
       

        public Node(String word,Node next) {
            this.data = word;
            this.next=next;
        }
    }
}
