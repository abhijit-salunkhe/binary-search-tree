/**
 * Basic implementation of a BinarySearchTree using BSTNode.
 *
 * @author Nick Suarez-Canton
 * @version 3/9/2015
 */
public class BinarySearchTree<T extends Comparable<T>> {
    // *************************************************************************
    // Invariant of BinarySearchTree:
    // (1) Instance variable root will either contain a reference to a the root
    // node of a binary tree, or refer to null if the tree is empty.
    // (2) Instance variable root will contain the number of elements in the
    // tree. It will equal zero if tree is empty.
    // *************************************************************************

    private BSTNode<T> root;
    private int size;


    /**
     * Default constructor for a BinarySearchTree.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Finds a node in a given BinarySearchTree which, according to compareTo(), matches with toFind.
     *
     * @param toFind element to find in the tree.
     * @return a reference to the element, if found. Else, returns null.
     */
    public T find(T toFind) {
        BSTNode<T> cursor = find(root, toFind);
        if (cursor != null) {
            return cursor.data;
        } else {
            return null;
        }
    }

    // Recursive call for find method.
    private BSTNode<T> find(BSTNode<T> cursor, T toFind) {
        if (cursor != null) {
            int compareResult = cursor.data.compareTo(toFind);

            if (compareResult == 0) {
                return cursor;
            }

            if (cursor.data.compareTo(toFind) >= 0) {
                return find(cursor.llink, toFind);
            } else {
                return find(cursor.rlink, toFind);
            }
        }
        return null;
    }


    /**
     * Adds a new node with the value toAdd to a BinarySearchTree.
     *
     * @param toAdd value to add to the tree.
     */
    public void add(T toAdd) {
        root = add(root, toAdd);
        size++;
    }


    // Recursive call for add method.
    private BSTNode<T> add(BSTNode<T> cursor, T toAdd) {
        if (cursor == null) {
            return new BSTNode<T>(toAdd);
        }
        if (cursor.data.compareTo(toAdd) >= 0) {
            cursor.llink = add(cursor.llink, toAdd);
        } else {
            cursor.rlink = add(cursor.rlink, toAdd);
        }

        return cursor;
    }


    /**
     * Finds a node in a BinarySearchTree which, according to compareTo(), matches with toRemove, and removes that node
     * from the given tree.
     *
     * @param toRemove value to remove from the tree.
     * @return value removed from the tree, if found. Else, returns null.
     * @throws IllegalStateException User cannot call remove on an empty tree.
     */
    public T remove(T toRemove) {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove when tree is empty.");
        }

        T nodeFound = find(root, toRemove).data;
        remove(parentOf(find(root, toRemove)), find(root, toRemove));
        size--;
        return nodeFound;

    }

    // Helper method for the remove method. It does the actual removing.
    private void remove(BSTNode<T> parent, BSTNode<T> nodeRemove) {

        if (nodeRemove.isLeaf()) {
            if (parent == null) {
                root = null;
            } else if (parent.hasLeft() && parent.llink.data.equals(nodeRemove.data)) {
                parent.llink = null;
            } else if (parent.hasRight() && parent.rlink.data.equals(nodeRemove.data)) {
                parent.rlink = null;
            }
        } else if (!nodeRemove.hasLeft()) {

            BSTNode<T> temp = nodeRemove.rlink;
            if (parent.llink == nodeRemove)
                parent.llink = temp;
            else
                parent.rlink = temp;

        } else {

            BSTNode<T> temp = largestValue(nodeRemove.llink);
            BSTNode<T> parentOfTemp = parentOf(temp);
            nodeRemove.data = temp.data;

            if (nodeRemove == parentOfTemp) {
                parentOfTemp.llink = temp.llink;
            } else {
                parentOfTemp.rlink = temp.llink;
            }
        }
    }


    // Helper method that finds the parent of a given node.
    private BSTNode<T> parentOf(BSTNode<T> toFind) {
        return parentOf(root, toFind);
    }


    // Recursive call for helper method parentOf
    private BSTNode<T> parentOf(BSTNode<T> cursor, BSTNode<T> toFind) {
        if (cursor == toFind) {
            return null;
        } else {
            if (cursor.llink == toFind || cursor.rlink == toFind) {
                return cursor;
            } else {
                if (cursor.data.compareTo(toFind.data) >= 0) {
                    return parentOf(cursor.llink, toFind);
                } else {
                    return parentOf(cursor.rlink, toFind);
                }
            }
        }
    }


    // Helper method that, given a node, finds the largest value in its subtree.
    private BSTNode<T> largestValue(BSTNode<T> cursor) {
        if (cursor != null && cursor.hasRight()) {
            return largestValue(cursor.rlink);
        }
        return cursor;
    }


    /**
     * @return a String representing the elements of the tree in order determined by compareTo().
     */
    public String toString() {
        BSTNode<T> cursor = root;
        String extra = toString(cursor);
        if (size > 0) {
            return extra.substring(0, extra.length() - 1);
        } else {
            return "";
        }
    }


    // Recursive helper method that creates a String representation of a given binary search tree.
    private String toString(BSTNode<T> root) {
        String ordered = "";
        if (root != null) {
            ordered += toString(root.llink);
            ordered += root.toString() + "\n";
            ordered += toString(root.rlink);
        }
        return ordered;
    }


    /**
     * @return a String representing the elements of the tree in pre-ordered order.
     */
    public String showTree() {
        BSTNode cursor = root;
        String extra = showTree(cursor, 0);
        if (size > 0) {
            return extra.substring(0, extra.length() - 1);
        } else {
            return "";
        }
    }


    // Recursive helper method that creates a String (graphic) representation of a given binary search tree.
    private String showTree(BSTNode root, int level) {
        String desc = "";
        if (root != null) {
            desc += printSpaces(level);
            desc += root.toString() + "\n";
            desc += showTree(root.llink, level + 1);
            desc += showTree(root.rlink, level + 1);
        }
        return desc;
    }


    // Helper method for the spacing.
    private String printSpaces(int level) {
        String spaces = "";
        for (int i = 0; i < level; i++) {
            spaces += " ";
        }
        return spaces;
    }
}