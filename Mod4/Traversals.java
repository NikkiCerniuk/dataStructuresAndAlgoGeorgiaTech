import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {

        List<T> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
        //does 1.node
        // 2. left
        //3. right

        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }


    private void preorderHelper(TreeNode<T> node, List<T> result){
        if (node == null){
            return;
        }
        result.add(node.getData());
        preorderHelper(node.getLeft(), result);
        preorderHelper(node.getRight(), result);
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {

        List<T> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
        //does left
        //does current
        //does right
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }


    private void inOrderHelper(TreeNode<T> node, List<T> result){
        if (node == null){
            return;
        }
        inOrderHelper(node.getLeft(), result);
        result.add(node.getData());
        inOrderHelper(node.getRight(), result);
    }
        

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        postOrderHelper(root,result); //pass in the root and array list
        return result;
        //does left
        //does right
        //does current 
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }


    private void postOrderHelper(TreeNode<T> node, List<T> result){
        if (node == null){
            return;
        }
        postOrderHelper(node.getLeft(), result);
        postOrderHelper(node.getRight(), result);
        result.add(node.getData());
    }
}
