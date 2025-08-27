import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *remember to reassign head/tail pointers if applicable 
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null){
            throw new java.lang.IllegalArgumentException("null data parameter"); 
        } // throw exception 
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data); //create a new node
        newNode.setNext(head); //sets new node's next reference to the current head
        head= newNode; // head points to new node 
        if (tail == null){
            tail = newNode; //if the list was size zero, it makes tail also equal to the singular new node in the list
        }
        size++;
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }

    /**
     * Adds the element to the back of the list.
     *remember to reassign head/tail pointers if applicable 
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) { //do not modify header
        if (data == null){
            throw new java.lang.IllegalArgumentException("null data parameter");
        } // throw exception
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data); //create a new node
        
        if(tail == null){ //takes care of instnaces where list started as null
            head = newNode;
            tail = newNode;
        }else{
            tail.setNext(newNode); //sets newNodes next reference to current tail
            tail = newNode; //now that new node has been addded to the chain, we can call that the new tail

        }
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {   //(DO NOT MODIFY METHOD HEADER)!
        if (head == null){
            throw new java.util.NoSuchElementException("cannot remove node from empty list");
        }

        T removedData = head.getData(); //old head is now in removedData. double check you can use getData and it wont result in a compiler time error****
        head = head.getNext(); //the next element in the list is now the head

        if (head == null){
            tail = null; //updates tail to null if removing from the list makes the list empty 
        }
        size--;
        return removedData;


    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() { //(DO NOT MODIFY METHOD HEADER)!
        if (head == null){
            throw new java.util.NoSuchElementException("cannot remove node from empty list");
        }

        T removedData;

        if (head == tail){ //only one element in the list in this case
            removedData = head.getData();
            head = null;
            tail = null;
            size --;
        }else{
            SinglyLinkedListNode<T> current = head;
            while(current.getNext() != tail){
                current = current.getNext(); //traverses the linked list
            }

            removedData = tail.getData(); // you can just access this without traversing
            tail = current; //chops off the next element. need to do this via traversing.
            tail.setNext(null); //disconnect last node 
            size--;
        }
        return removedData;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
