/**
 *       Author: Daniel Navarro
 *       Course: Advanced Data Structures
 *         Date: Spring 2019
 * Program Name: BST.java 
 *  Description: This is the BST class containing all methods for traversing the tree.
 */


import java.util.ArrayList; 
import java.util.Stack;


public class BST<E extends Comparable<E>> implements TreeInterface<E> {

    // Data fields
    public TreeNode rootTreeNode = null;
    // Store the number of Nodes in this class variables
    public int size = 0;
    // Store the number of Non Leaf nodes in this class variables
    public int nonleaves;

    public ArrayList<E> inOrderTraversal = new ArrayList<>();
    public ArrayList<E> preOrderTraversal = new ArrayList<>();
    public ArrayList<E> postOrderTraversal = new ArrayList<>();
    public ArrayList<E> bstTraversal = new ArrayList<>();

    // empty constructor
    public BST() {}

    
    // Create a binary tree from an array of objects 
    public BST(E[] Object) {
      for (int i = 0; i < Object.length; i++)
        insert(Object[i]);
    }

    // This inner class is static, because it does not access 
    // any instance members defined in its outer class 
    public static class TreeNode<E extends Comparable <E>> {
    	protected E element;
    	protected TreeNode<E> left;
    	protected TreeNode<E> right;
    	
    	public TreeNode(E e) {
    		element = e;
    	}  	
    }     // end of TreeNode
    
    
    // Looks for an item in the tree
    public boolean search(E e) {
        boolean blResult = false;

        /**
         * TODO: INSERT YOUR CODE HERE
         * 
         * SEARCH FOR THE ITEM PASSED AS PARAMETER AND RETURN TRUE IF FOUND ELSE RETURN
         * FALSE
         */

        TreeNode<E> current = rootTreeNode; // starting from the root
        while (current != null) {
        	if (e.compareTo(current.element) < 0) {
        		current = current.left;  // moving to left subtree
        	}
        	else if (e.compareTo(current.element) > 0) {
        		current = current.right; // moving to right subtree
        	}
        	else  // element matches current element 
        		return true;  // element is found
        }
        return blResult;
    } // end search method
    

    public void insert(E e) {
      /**
         * TODO: INSERT YOUR CODE HERE
         * 
         * INSERT THE ITEM PASSED AS PARAMETER IN THE TREE . HINT: THE NUMBER OF NODE
         * CAN BE UPDATED IN THE "size" VARIABLE
         * 
         */
        if (rootTreeNode == null)
            rootTreeNode = createNewNode (e);  // Create a new Node root if empty
   	    else {
   		    // Locate the parent node
   		    TreeNode<E> parent = null;
            TreeNode<E> current = rootTreeNode; 
       	
       	    while (current != null) 
       		    if (e.compareTo(current.element) < 0) {
       			    parent = current;
                       current = current.left;
                }
       		    else if (e.compareTo(current.element) > 0) {
       			    parent = current;
                       current = current.right;
                }  
       	
       	    // Creating a new Node and attach it to the parent Node
       	    if (e.compareTo(parent.element) < 0) 
       		    parent.left = createNewNode(e);
       	    else 
                   parent.right = createNewNode(e);
            }	
   	    size++;
   	    System.out.println("true");
   	}
    

    TreeNode<E> createNewNode(E e){
    	return new TreeNode<E>(e);
    }
    
    
    public boolean delete(E e) {
        boolean blResult = false;
        /**
         * TODO: INSERT YOUR CODE HERE DELETE THE ITEM PASSED AND REPLACE WITH THE
         * INORDER SUCCESSOR OF THE ELEMENT 
         * RETURN true IF ELEMENT FOUND AND DELETED, ELSE RETURN false.
         * ITEM NOT FOUND HINT: THE NUMBER OF NODE CAN BE UPDATED IN THE "size" VARIABLE 
         * HINT: SEARCH FOR THE ELEMENT TO BE DELETED AND DELETE THE ELEMENT ONCE FOUND
         */ 
        
        // Search the node for deletion and its parent node:
   		TreeNode<E> parent = null;
       	TreeNode<E> current = rootTreeNode; 
       	
      	if (rootTreeNode == null) {
    		return false;    // element is not in the tree
    	}  
   	
    	while(current != null) {
    		if (e.compareTo(current.element) < 0) { // If target value is less than current value go left
    			 parent = current;
    			 current = current.left; 
    		}
    		else if (e.compareTo(current.element) > 0) { // Otherwise go right
    			parent = current;
    			current = current.right;
    		}
    		else
    			break;   // element is in the tree pointed at by current  
    	}
    	if (current == null) 
    		return false;    // element is not in the tree 
    	
    	
    	// CASE: 1 - For Deleting an alement in a BST 
    	// The current node has only one child... let the parent take care of grandchild.
    	// find the parent if the current node and reconnect
    	
    	// Current has no Left Children 
    	if (current.left == null) {  // Target is not in the tree
    		// Connect parent witht the right child of the current node
    		if (parent == null) {
    			rootTreeNode = current.right;
    		}
    		else {
    			if (e.compareTo(parent.element) < 0) 
    				parent.left = current.right;
    			else 
    				parent.right = current.right;
    			}
    		}
    		
            else {
    			// CASE: 2 - the current node has two children
    			// Locate the rigthmost node in the left subtree of the current node
    			// and also its parent
    			
    			TreeNode<E> parentOfRightMost = current;
    	    	TreeNode<E> rightMost = current.left; 
    	    	
    	    	while (rightMost.right != null) {
    	    		parentOfRightMost = rightMost;
    	    		rightMost = rightMost.right;     // Keep going to th right
    	    	}
    	    	
    	    	// Replace the element in current by the element in rightMost
    	    	current.element = rightMost.element;
    	    	
    	    	// Eliminate rightMost node
    	    	if (parentOfRightMost.right == rightMost)
    	    		parentOfRightMost.right = rightMost.left;
    	    	else 
    	    		// Special Case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;	    				
            }  	
        size--;
        blResult = true;   // Element deleted successfully 
        return blResult;
    }   
    
    
    // get the Number of non-leaves.
    public int getNumberofNonLeaves() {   
       /**
         * TODO: INSERT YOUR CODE HERE FIND THE NUMBER OF NON_LEAF NODES IN THE TREE AND
         * RETURN
         */
    	TreeNode<E> current = rootTreeNode; // starting from root   
    	
    	nonleaves = size - getNumberofLeaves(rootTreeNode);
    	
    	return nonleaves;
    }
    
    
    // Get number of leaves method
    private int getNumberofLeaves(TreeNode<E> rootTreeNode) {
    	//TreeNode<E> current = rootTreeNode; // starting from root   
    	if (rootTreeNode == null) {
    		return 0;
    	}
    	else if ( rootTreeNode.left == null &&  rootTreeNode.right == null) {
    		return 1;
    	}
    	else {
    		return getNumberofLeaves(rootTreeNode.left) + getNumberofLeaves(rootTreeNode.right);
    	}	
    }


    // (Implement inorder traversal without using recursion)
    public ArrayList<E> inorderNoRecursion() {
        ArrayList<E> nonRecursiveInorder = new ArrayList<E>();
        Stack<TreeNode<E>> nodes = new Stack<TreeNode<E>>();  // Initializing the Generic Method & Empty Stack
      
        /**
         * TODO: INSERT YOUR CODE HERE FIND THE IN ORDER TRAVERSAL OF THE TREE WITHOUT
         * USING RECURSION AND RETURN THE RESULT TRAVEL SEQUENCE IN ARRAYLIST
         * nonRecursiveInorder
         */
            
        // The arrayList is of generic type ‘E’. 
        // You need to typecast the object when access the node element.
        
        TreeNode<E> current = rootTreeNode; // starting from root   
        
        
        while (!nodes.isEmpty() || current != null) {
        	if (current != null) {
        		// Add root into the stack
        		nodes.push(current);
        		current = current.left;
        	}
        	else {
        		current = nodes.pop();      // get element from stack LIFO
        		nonRecursiveInorder.add(current.element);  // Put into ArrayList
        		current = current.right;
        	}
        }
        return nonRecursiveInorder;
    }
    
    
    // (Implement postorder traversal without using recursion)
    public ArrayList<E> postorderNoRecursion() {
        ArrayList<E> nonRecursivePostorder = new ArrayList<>();

        /**
         * TODO: INSERT YOUR CODE HERE FIND THE POST ORDER TRAVERSAL OF THE TREE WITHOUT
         * USING RECURSION AND RETURN THE RESULT TRAVEL SEQUENCE IN ARRAYLIST
         * nonRecursivePostorder
         */

        TreeNode<E> current = rootTreeNode;
        if (current == null) {
            return nonRecursivePostorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(current);
        
        while (!stack.isEmpty()) {
            TreeNode <E>node = stack.pop();
            nonRecursivePostorder.add(node.element);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return nonRecursivePostorder;
    }
}// end class BST
