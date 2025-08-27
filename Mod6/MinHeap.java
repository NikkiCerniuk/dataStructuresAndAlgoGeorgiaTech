import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }


    public void add(T data) {
        if (data == null){
            throw new java.lang.IllegalArgumentException("data to add cannot be null");
        } //* @throws java.lang.IllegalArgumentException If the data is null.

//2. check if backing array is full, if it is then double the capacity
        if((size+1) >= backingArray.length){ //compares size of adding an element to the current size of the array
            int newCapacity = backingArray.length *2;
            T[] newBackingArray = (T[]) new Comparable[newCapacity]; //make sure this x2 is correct with the 1 element start for the heap
            int i=0;
            while (i<backingArray.length){
            newBackingArray[i]=backingArray[i];
            i++;   
            }
            backingArray = newBackingArray;
            }

            backingArray[++size] = data;  //adds data at the next element in the array
            percolateUp(size);// call percolateUp method to deal with the swaps 
        }
    


        private void percolateUp(int index){
            if(index<=1){
                return; //return if we reach the root 
            }
            int parentIndex = index/2;
            
            
            if (backingArray[index].compareTo(backingArray[parentIndex])< 0){ //this means if our element we added is less than the parent index
               T temp = backingArray[index]; //make a temp variable for the backing array at index
                backingArray[index] = backingArray[parentIndex]; 
                backingArray[parentIndex] = temp;
                percolateUp(parentIndex); 
            }//simply returns if this if statement evaulates to false and the parent is smaller than the child 
        }

    /*
     */
    public T remove() { // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if(size == 0){ //this means that the backing array is empty which would make it impossible to perform a remove operation
            throw new java.util.NoSuchElementException("cannot perform remove on an array with no elements");
        } 
      
        T removedData = backingArray[1]; //stores a copy of the root in a temp variable
       backingArray[1] = backingArray[size]; //copy the right most leaf into index 1
       backingArray[size] = null; //backingArray[size]=null. Null out the element we removed
       size--; //size --
       percolateDown(1); // input the root into the percolate helper function
       return removedData; //7. return the saved root value

    }

/*

  private void percolateUp(int index){
            if(index<=1){
                return; //return if we reach the root 
            }
            int parentIndex = index/2;
            
            
            if (backingArray[index].compareTo(backingArray[parentIndex])< 0){ //this means if our element we added is less than the parent index
               T temp = backingArray[index]; //make a temp variable for the backing array at index
                backingArray[index] = backingArray[parentIndex]; 
                backingArray[parentIndex] = temp;
                percolateUp(parentIndex); 
            }
        }
2. if returned child is<node then swap node and child. 
3. continue comparing 2 siblings of node then node and child until you reach a leaf or until node< returned child
      
 */
    private void percolateDown(int index){
        int left = index*2;
        if (left > size){ //looks to see if the index we are looking at is a leaf
            return; //if it is a leaf, exit the helper method 
        }

        //here we find the smaller of the two children
        int right = left+1; // right child
        int smallerChild = left; //assumes the smaller child is the left child 
        if (right<= size && (backingArray[right].compareTo(backingArray[left])<0)){
            smallerChild = right; //reassign smallerChild to right if right is smaller
        }

        if(backingArray[smallerChild].compareTo(backingArray[index])<0){ //if child is less than the parent
            T temp = backingArray[index]; //puts parent in temp variable
            backingArray[index] = backingArray[smallerChild]; //puts child in parent
            backingArray[smallerChild] = temp; //puts parent in smaller child using temp
            percolateDown(smallerChild); //recursion with smallerChild now acting as the new parent node
        }

        }
        
    

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */

    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
