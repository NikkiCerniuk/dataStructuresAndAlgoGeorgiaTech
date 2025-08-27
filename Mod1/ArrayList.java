import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     * 
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }// initalizes the array to 9 elements but they are all null to start 

    /**
     * Adds the data to the front of the list.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) { //(DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new java.lang.IllegalArgumentException("added data is null"); //Dont need an import for the java.lang package
        }
        resizeCheck();//call helper function for null and resize
        for (int j = size-1; j >=0;j--){ //starts at the back of the list
        backingArray[j + 1] =  backingArray[j]; //shifts elements one space to the right
        }

        backingArray[0] = data;
        size++;
    }//should run in O(n)

    /**
     * Adds the data to the back of the list.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) { //(DO NOT MODIFY METHOD HEADER)!
        if(data == null){
            throw new java.lang.IllegalArgumentException("added data is null"); //Dont need an import for the java.lang package
        }
        resizeCheck();//call helper function for null and resize
        backingArray[size] = data; //adds the data to the backing array at size
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */

    private void resizeCheck(){
    if(size == backingArray.length){
        T[] newBackingArray = (T[]) new Object[backingArray.length*2]; //new object w double the capacity
        for (int i=0;i<size;i++){
            newBackingArray[i] = backingArray[i]; // transfers the items to backing array
        }
        backingArray = newBackingArray; //now our backing array points to newBackingArray
    }
    }

    public T removeFromFront(){ //(DO NOT MODIFY METHOD HEADER)!
        if (size == 0){
            throw new NoSuchElementException("Cannot remove from an empty list."); //@throws java.util.NoSuchElementException if the list is empty
        }
        T removedElement = backingArray[0]; //stores the data at element zero
       
        for(int i= 0; i < size-1; i++){
            backingArray[i] = backingArray[i+1]; //shifts elements left. 5th element and putting it in the4th for instance
        }

        backingArray[size-1] = null; //end of array = null
        size--;
        return removedElement;

    }

    /**
     * Removes and returns the last data of the list.
     *
     * Do not shrink the backing array.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     */
    public T removeFromBack() { //(DO NOT MODIFY METHOD HEADER)!
        if (size == 0){
            throw new NoSuchElementException("Cannot remove from an empty list."); //@throws java.util.NoSuchElementException if the list is empty
        }
        T removedElement = backingArray[size - 1]; //puts last element here
        backingArray[size-1] = null; // this helps with data collection. Changes where the object is pointing, not the object itself 
        size--;
        return removedElement; //returns removed element 
    } //should run in constant time***

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
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
