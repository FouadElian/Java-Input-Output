package a3_secondTry;

//-----------------------------------
// Assignment 3
// Written by: Fouad Elian 40273045
//-----------------------------------

class NoSuchElement extends Exception{
	public NoSuchElement(String b) {
		super(b);
	}
}

public class myLinkedList {

    private Node head;
    private Node tail;
    private int counter;
    
    public int getCounter() {
    	return this.counter;
    }
    
    public myLinkedList() {
    	this.head=null;
    	this.counter=0;
    }
    /**
     * Adds a new node with the specified Vocab object as its data at the beginning of the linked list.
     * If the linked list is empty, the new node becomes both the head and tail of the list.
     * If the linked list is not empty, the new node becomes the head of the list and the previous head becomes its successor.
     * 
     * @param obj The Vocab object to be added as the data of the new node.
     */
    public void addHead(Vocab obj) {
    	  Node temp = head;
    	  head = new Node(obj, null, head);
    	  if (tail == null) {
    	        tail = head;
    	        } 
    	  else {
    	            temp.previous = head;
    	        }

    	        counter++;
    	    
    }
    /**
    * Adds a new node with the specified Vocab object as its data at the end of the linked list.
    * If the linked list is empty, the new node becomes both the head and tail of the list.
    * If the linked list is not empty, the new node is appended after the current tail node and becomes the new tail.
    * 
    * @param obj The Vocab object to be added as the data of the new node.
    */
    public void addEnd(Vocab obj) {
    	if(head==null) {
    		 addHead(obj);
    	}
    	else {
    		Node pos = head;
    		while(pos.next!= null) {
    			pos = pos.next;
    		}
    		tail = new Node(obj,null,null);
    		tail.previous=pos;
    		pos.next=tail;
    		counter++;
    	}
    }
    /**
     * Inserts a new node with the specified Vocab object as its data after the node with the reference Vocab object.
     * If the reference Vocab object is not found in the linked list, a NoSuchElement exception is thrown.
     * 
     * @param referenceValue The Vocab object after which the new node will be inserted.
     * @param value The Vocab object to be inserted into the linked list.
     * @throws NoSuchElement If the reference Vocab object does not exist in the linked list.
     */
    public void addAfter(Vocab referenceValue, Vocab value) {

        Node position = head;

        while (position != null && !position.data.equals(referenceValue)) {
            position = position.next;
        }
        try {
        if (position == null) { // value does not exist
            throw new NoSuchElement("There is no such Topic");
        } else {
        	Node new_node = new Node(value,null,null);
        	new_node.previous=position;
        	new_node.next=position.next;
            position.next = new_node;
            counter++;
        }
        }
        catch(NoSuchElement e) {
        	System.out.println("You tried to place a topic after a non existent topic");
        }

    }
    /**
     * Removes the first node (head) from the linked list.
     * If the linked list is not empty, the head is updated to point to the next node,
     * and the counter is decremented to reflect the removal of a node.
     * If the linked list is empty, a message is printed indicating that the list has no head.
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
     * Removes the node containing the specified Vocab object from the linked list.
     * If the linked list is empty, a message is printed indicating that it is empty.
     * If the node containing the specified Vocab object is the head node, it is removed using the removeHead method.
     * If the node containing the specified Vocab object is found, it is removed from the linked list.
     * 
     * @param obj The Vocab object to be removed from the linked list.
     */
    public void remove(Vocab obj) {
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
    		if(pos.next==null) {
    			pos.previous.next=null;
    			pos.previous=null;
    			pos=null;
    		
    		}
    		else {
    			pos.previous.next=pos.next;
    			pos.next.previous=pos.previous;
    			pos=null;
    		}
    		counter--;
    	}
    	}
    }
    /**
     * Inserts a new node containing the specified Vocab object before the node with the reference Vocab object.
     * If the linked list is empty, a message is printed indicating that it is empty and the insertion cannot be performed.
     * If the reference Vocab object is found at the head of the list, the new node becomes the new head.
     * If the reference Vocab object is found in the list, the new node is inserted before it.
     * 
     * @param obj_ref The Vocab object before which the new node will be inserted.
     * @param obj The Vocab object to be inserted into the linked list.
     */
    public void addBefore(Vocab obj_ref, Vocab obj) {
        if (head == null) {
            System.out.println("List is empty. Cannot add before.");
            return;
        }

        // If obj_ref is the head of the list, insert new node as the new head
        if (head.data.equals(obj_ref)) {
            addHead(obj);
            return;
        }

        Node position = head;
        while (position != null && !position.data.equals(obj_ref)) {
            position = position.next;
        }

        if (position == null) {
            System.out.println("There is no such value to add before.");
        } else {
            Node newNode = new Node(obj,null,null);
            newNode.next=position;
            newNode.previous=position.previous;
            position.previous.next=newNode;
            position.previous=newNode;
            counter++;
        }
    }

    /**
     * Displays the content of the linked list, which includes the topic of each Vocab object stored in the nodes.
     * If the linked list is empty, a message is printed indicating that the list is empty.
     * Each node's topic is printed along with its position in the list.
     */
    public void display() {
    	int counter=1;
        if (head == null) {
            System.out.println("Your list is empty.");
        } else {
            Node position = head;
            while (position != null) {
                System.out.println(counter+ " " + position.data.getTopic() +"\n");

                position = position.next;
                counter++;

            }
        }
    }
    /**
     * Retrieves the Vocab object stored at the specified index in the linked list.
     * If the linked list is empty or the index is negative, null is returned.
     * Otherwise, the method traverses the linked list until it reaches the node at the specified index,
     * and then returns the Vocab object stored in that node.
     * 
     * @param index The index of the node whose Vocab object is to be retrieved.
     * @return The Vocab object stored at the specified index in the linked list, or null if the index is out of bounds.
     */
    public Vocab getNodeAtIndex(int index) {
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
    
    
    
    private class Node {

        
        private Vocab data;
        private Node next;
        private Node previous;
       

        public Node(Vocab obj2,Node prev,Node next) {
            this.data = obj2;
            this.next=next;
            this.previous=prev;
        }
    }
}

