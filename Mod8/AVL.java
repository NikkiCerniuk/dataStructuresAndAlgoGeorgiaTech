import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
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
    private AVLNode<T> addHelper (AVLNode<T> currentNode, T data){
        if (currentNode == null){
            size++;
            return new AVLNode<T> (data); //returns a new node on the tree with the data from the parameter
        }


        int compare = data.compareTo(currentNode.getData());//compare data to add to current node's data
        if(compare < 0){
            currentNode.setLeft(addHelper(currentNode.getLeft(), data));
        }
        else if(compare > 0){
            currentNode.setRight(addHelper(currentNode.getRight(), data));
        }else{
            return currentNode;
        }
        return balance(currentNode);

        //compare the current to next and throw back into helper
    }
    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        if(data == null){ //if data to remove is null throw an exception 
            throw new java.lang.IllegalArgumentException("Error: cannot remove null data from a tree");
        } 
        AVLNode<T> dummy = new AVLNode<>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }




    private AVLNode<T> removeHelper(AVLNode<T> currentNode, T data, AVLNode<T> dummy){ //left param is what current node you are passing in, right param is data you want to remove. double check the return type here
        if (currentNode == null){
            throw new java.util.NoSuchElementException("Error: data does not exist in tree");
        }

        int cmp = data.compareTo(currentNode.getData()); //compare our data we are trying to remove to the current node. helps us traverse

        if(cmp<0){
            currentNode.setLeft(removeHelper(currentNode.getLeft(), data, dummy)); //traverse left
            return balance(currentNode);//go left
        }
        else if(cmp>0){
            currentNode.setRight(removeHelper(currentNode.getRight(), data, dummy)); //traverse right
            return balance(currentNode);//go right
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
            AVLNode<T> successorDummy = new AVLNode<>(null); //makes a dummy to hold the successor
            currentNode.setRight(removeSuccessor(currentNode.getRight(), successorDummy));//find the successor
            currentNode.setData(successorDummy.getData());//set data = successor dummy data 
            return balance(currentNode);
        }
    }
    }
        //two child case:


    private AVLNode<T> removeSuccessor(AVLNode<T> node, AVLNode<T> successorDummy){
        if(node.getLeft() == null){
            successorDummy.setData(node.getData());
            return balance(node.getRight()); //return the subtree just under the successor so that we do not lose that data 
        }else{
            node.setLeft(removeSuccessor(node.getLeft(), successorDummy));
            return balance(node);
        }
        }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> currentNode) {

        //updating height
        int leftHeight = (currentNode.getLeft() == null) ? -1 : currentNode.getLeft().getHeight(); //null handling for left child
        int rightHeight = (currentNode.getRight() == null) ? -1 : currentNode.getRight().getHeight();//null handling for right child 

        currentNode.setHeight(Math.max(leftHeight, rightHeight) + 1); //finds the max of the children, adds 1, and makes that the height of the parent node.
        currentNode.setBalanceFactor(leftHeight - rightHeight); //subtracts the right child from the left to get the balance factor of the parent node 
    }


    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        AVLNode<T> newRoot = currentNode.getRight(); //store right node in a temp variable
        currentNode.setRight(newRoot.getLeft()); //right node's left subtree becomes the current node's right subtree 
        newRoot.setLeft(currentNode);//current node becomes right node's left subtree 
        updateHeightAndBF(currentNode); //recalculate balance factor for the left node of the new root 
        updateHeightAndBF(newRoot); // recalculate balance factor for new root
        return newRoot;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {

        AVLNode<T> newRoot = currentNode.getLeft(); 
        currentNode.setLeft(newRoot.getRight()); 
        newRoot.setRight(currentNode);
        updateHeightAndBF(currentNode); 
        updateHeightAndBF(newRoot); 
        return newRoot;
    }


    /**
     * Method that balances out the tree starting at the node passed in.
     * This method should be called in your add() and remove() methods to
     * facilitate rebalancing your tree after an operation.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        /* First, we update the height and balance factor of the current node. */

        if (currentNode == null) {
            return null;
        }

        updateHeightAndBF(currentNode);

        if (currentNode.getBalanceFactor() < -1) {
            if (currentNode.getRight() != null && currentNode.getRight().getBalanceFactor() > 0)/* Condition for a right-left rotation. */ {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( currentNode.getBalanceFactor() > 1 ) {
            if (currentNode.getLeft() != null && currentNode.getLeft().getBalanceFactor() < 0) /* Condition for a left-right rotation. */ {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }
        updateHeightAndBF(currentNode);
        return currentNode;
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
