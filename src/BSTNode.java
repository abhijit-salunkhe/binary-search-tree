/**
 * Implementation of a generic node for BinarySearchTree.
 *
 * @author Nick Suarez-Canton
 * @version 3/3/2015
 */
public class BSTNode<E extends Comparable<E>> {
    // *************************************************************************
    // Invariant of BSTNode:
    // (1) Instance variable data will always contain a reference to an Object
    // of type E.
    // (2) Instance variable rlink will either contain a reference to a node's
    // right child (another BSTNode), or, in the case that the node is a leave,
    // it will contain a reference to null.
    // (2) Instance variable llink will either contain a reference to a node's
    // left child (another BSTNode), or, in the case that the node is a leave,
    // it will contain a reference to null.
    // *************************************************************************
    public E data;
    public BSTNode<E> rlink;
    public BSTNode<E> llink;


    /**
     * Creates a new BSTNode.
     *
     * @param initialData the data to be contained in the new node.
     */
    public BSTNode(E initialData) {
        data = initialData;
        llink = null;
        rlink = null;
    }

    /**
     * toString() method for a BSTNode.
     *
     * @return a String representation of the node.
     */
    public String toString() {

        return "" + data.toString();
    }

    /**
     * Checks whether or not a given node is a leaf.
     *
     * @return true iff a given node is a leaf (i.e. has no children).
     */
    public boolean isLeaf() {

        return (this.rlink == null) && (this.llink == null);
    }

    /**
     * Checks whether or not a given node has a left child.
     *
     * @return true iff a given node has a left child (i.e. llink !=null).
     */
    public boolean hasLeft() {
        return llink != null;
    }

    /**
     * Checks whether or not a given node has a right child.
     *
     * @return true iff a given node has a right child (i.e. rlink !=null).
     */
    public boolean hasRight() {
        return rlink != null;
    }
}