// Program Name: Tree.java 
//  Description: Interface with abstract methods


import java.util.ArrayList;


public interface TreeInterface<E> {

    // Return true if the element is in the tree 
    public boolean search(E e);

    // Insert element o into the binary tree Return true if the element is inserted successfully
    public void insert(E e);
    
    // Delete the specified element from the tree Return true if the element is deleted successfully
    public boolean delete(E e);

    // Return the Number of non-leaves
    public int getNumberofNonLeaves();

    // Return inorder traversal using stack i.e. without using recursion 
    public ArrayList<E> inorderNoRecursion();

    // Return postorder traversal using stack i.e. without using recursion 
    public ArrayList<E> postorderNoRecursion();

}// end interface Tree

