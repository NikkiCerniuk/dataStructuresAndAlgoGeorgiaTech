import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if(data == null){
            throw new java.lang.IllegalArgumentException("Error: cannot add null data to a tree");
        } 
        root = addHelper(root, data); //root is declared above and data is passed in as a parameter
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }


//helper function here
    private BSTNode <T> addHelper (BSTNode<T> currentNode, T data){
        if (currentNode == null){
            size++;
            return new BSTNode <T> (data); //returns a new node on the tree with the data from the parameter
        }


        int compare = data.compareTo(currentNode.getData());//compare data to add to current node's data
        if(compare < 0){
            currentNode.setLeft(addHelper(currentNode.getLeft(), data));
        }
        else if(compare > 0){
            currentNode.setRight(addHelper(currentNode.getRight(), data));
        }
        return currentNode;

        //compare the current to next and throw back into helper
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     */
    public T remove(T data) {
        if(data == null){ //if data to remove is null throw an exception 
            throw new java.lang.IllegalArgumentException("Error: cannot remove null data from a tree");
        } 
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }


    private BSTNode<T> removeHelper(BSTNode<T> currentNode, T data, BSTNode<T> dummy){ //left param is what current node you are passing in, right param is data you want to remove. double check the return type here
        if (currentNode == null){
            throw new java.util.NoSuchElementException("Error: data does not exist in tree");
        }

        int cmp = data.compareTo(currentNode.getData()); //compare our data we are trying to remove to the current node. helps us traverse

        if(cmp<0){
            currentNode.setLeft(removeHelper(currentNode.getLeft(), data, dummy)); //traverse left
            return currentNode;//go left
        }
        else if(cmp>0){
            currentNode.setRight(removeHelper(currentNode.getRight(), data, dummy)); //traverse right
            return currentNode;//go right
        }else{
            //data we are on equals the data of our current node 
            dummy.setData(currentNode.getData()); //sets the data of the dummy to be equal to the current node bc we have found the node to remove
            size--;
        
        
        //one child case: 
        if (currentNode.getLeft() == null &&
            currentNode.getRight() == null){
                return null;
            }

            //2 child case
        if(currentNode.getLeft() == null){
            return currentNode.getRight();// return right if no left child 
        }
        if(currentNode.getRight() == null){
            return currentNode.getLeft(); //return left if no right child. reconnects things 
        }else{
            BSTNode<T> successorDummy = new BSTNode<>(null); //makes a dummy to hold the successor
            currentNode.setRight(removeSuccessor(currentNode.getRight(), successorDummy));//find the successor
            currentNode.setData(successorDummy.getData());//set data = successor dummy data 
            return currentNode;
        }
    }
    }
        //two child case:


    private BSTNode<T> removeSuccessor(BSTNode<T> node, BSTNode<T> successorDummy){
        if(node.getLeft() == null){
            successorDummy.setData(node.getData());
            return node.getRight(); //return the subtree just under the successor so that we do not lose that data 
        }else{
            node.setLeft(removeSuccessor(node.getLeft(), successorDummy));
            return node;
        }
        }
        //remove successor function here
        //three child case:




    /*to remove  something with two children: 
    1. find the successor
    2. store the successor info in a temp variable 
    3. remove the succcessor from the tree
    4. replace the node to remove data with the data from the successor 
*/




    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }



    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
