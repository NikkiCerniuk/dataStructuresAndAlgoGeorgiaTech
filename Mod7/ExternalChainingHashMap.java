import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * 
     * make sure no duplicate keys are added
     * 
     * 
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
  
     */
    public V put(K key, V value) {
        if (key == null || value == null){
            throw new java.lang.IllegalArgumentException("key or value to add cannot be null");//* @throws java.lang.IllegalArgumentException If key or value is null
        }

        if (((double) (size + 1)) / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }

        int indexToPlace = Math.abs(key.hashCode() % table.length); //do key mod array length to get the compressed key value 
        ExternalChainingMapEntry<K , V> current = table[indexToPlace];


        //if there is something already there, check if its the same value as the one that we are trying to add    

        while(current !=null){
            if(current.getKey().equals(key)){// check if value we are adding already exists
                V oldValue = current.getValue();  //if it does, store the old
                current.setValue(value);// set the old value to that of the current 
                return oldValue; //if the key was already in the map, return its value.
            }
            current = current.getNext(); // traverses the SLL
        }

        ExternalChainingMapEntry<K , V> entryToAdd = new ExternalChainingMapEntry<>( key, value, table[indexToPlace]);// actually makes a node with the K and V to add

        table[indexToPlace] = entryToAdd; // actually adds the new node to the front of the SLL
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        size++;
        return null; //if the key was not already in the map, return null.
    }


    /*
     * Algorithm for put method: 

     */
    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     * 
     * 
     * if you remove a value and it was hte only thing at that index, then null out the index.
     */
    public V remove(K key) {
        if(key == null){
            throw new IllegalArgumentException("key to remove cannot be null"); // * @throws java.lang.IllegalArgumentException If key is null.

        }



       int index = Math.abs(key.hashCode()%(table.length));//compress the key to the correct index
        ExternalChainingMapEntry<K,V> current = table[index]; //sets current temp variable equal to the head at the index
        ExternalChainingMapEntry<K,V> previous = null; //initalizes previous to null bc there is no node before the head
        while (current !=null){  //traverse the hashmap while current is not null
            if(current.getKey().equals(key)){
                V valueToReturn = current.getValue(); //store the value to return in a variable so we may return it later
            
            if(previous == null){  //if its the head of the node, update the table index to current get next 
                table[index] = current.getNext();

            }else{//if its not the head of the node, connect the previous to current next 

                previous.setNext(current.getNext());
            }
            size--;// //decrement size 
            return valueToReturn;   //return the value associated with the key that was removed
        }
        previous = current;
        current = current.getNext();
    }
        throw new NoSuchElementException("The key was not found in the map.");
    }



    private void resizeBackingTable(int length) {
        ExternalChainingMapEntry<K,V> [] oldTable = table; // store the origional table in a variable called old table 
        table = (ExternalChainingMapEntry<K,V>[]) new ExternalChainingMapEntry[length]; // takes a new backing array of length and assigns it to table 
        size = 0; //intilizes size to zero 
        for (int i = 0; i < oldTable.length; i++){ // traverses the whole old array
            ExternalChainingMapEntry<K,V> current = oldTable[i]; // variable to traverse the table 
            while (current != null){
                putWithoutResize(current.getKey() , current.getValue()); // while current is not null, get the value associated with each key at a certain index and pass it into putWithoutResize
                current = current.getNext(); // goes to next index 
            }
        }
    }


    private void putWithoutResize(K key, V value) {
        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> current = table[index];
    
    
        //traverses the SLL at a specific index 
        while (current != null) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }
    
        //after it traverses the SLL, it creates a node for this and increases the size 
        table[index] = new ExternalChainingMapEntry<>(key, value, table[index]);
        size++;
    }
    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
