import static org.junit.Assert.*;

//import com.sun.tools.javac.jvm.Gen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BinarySearchTreeTest {

    private BinarySearchTree<String> tree;

    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<String>();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }


    @Test
    public void testConstructor() {
        assertEquals("A new tree should be empty", "", tree.toString());
    }

    @Test
    public void testAdd_toEmptyTree() {
        tree.add("K");
        assertEquals("empty tree + K", "K", tree.toString());
    }

    @Test
    public void testAdd_depthZeroTreeLeftChild() {
        tree.add("K");
        tree.add("D");
        assertEquals("K + D", "D\nK", tree.toString());
    }

    @Test
    public void testAdd_depthZeroTreeLeftAndRightChildren() {
        tree.add("K");
        tree.add("A");
        tree.add("L");
        assertEquals("K + A + L", "A\nK\nL", tree.toString());
    }

    @Test
    public void testAddShowTree_depthZeroTreeLeftChild() {
        tree.add("K");
        tree.add("D");
        assertEquals("K + D", "K\n D", tree.showTree());
    }

    @Test
    public void testAddShowTree_depthZeroTreeLeftAndRightChildren() {
        tree.add("K");
        tree.add("A");
        tree.add("L");
        assertEquals("K + L + A", "K\n A\n L", tree.showTree());
    }

    @Test
    public void testFind_emptyTree() {
        assertNull("find on empty shall return null", tree.find("A"));
    }

    @Test
    public void testFind_nonEmptyTreeElementFound() {
        tree.add("K");
        tree.add("A");
        tree.add("L");
        assertEquals("finds rood", "K", tree.find("K"));
        assertEquals("finds right child", "A", tree.find("A"));
        assertEquals("finds left child", "L", tree.find("L"));

        tree.add("B");
        assertEquals("works further down", "B", tree.find("B"));
    }

    @Test
    public void testRemove_onlyRight() {
        tree.add("M");
        tree.add("E");
        tree.add("P");
        tree.add("G");
        tree.add("O");
        tree.add("R");
        tree.add("F");
        String dataRemoved = tree.remove("E");

        assertEquals("returns removed node", "E", dataRemoved);
        assertEquals("tree looks right", "M\n G\n  F\n P\n  O\n  R", tree.showTree());
    }

    @Test
    public void testRemove_onlyLeft() {
        tree.add("M");
        tree.add("E");
        tree.add("P");
        tree.add("G");
        tree.add("O");
        tree.add("R");
        tree.add("F");
        String dataRemoved = tree.remove("G");
        assertEquals("returns removed node", "G", dataRemoved);
        assertEquals("tree looks right", "M\n E\n  F\n P\n  O\n  R", tree.showTree());
    }

    @Test
    public void testRemove_bothRandL() {
        tree.add("M");
        tree.add("E");
        tree.add("P");
        tree.add("G");
        tree.add("O");
        tree.add("R");
        tree.add("F");
        String dataRemoved = tree.remove("P");
        assertEquals("returns removed node", "P", dataRemoved);
        assertEquals("tree looks right", "M\n E\n  G\n   F\n O\n  R", tree.showTree());
    }

    @Test
    public void testRemove_noChildren() {
        tree.add("M");
        tree.add("E");
        tree.add("P");
        tree.add("G");
        tree.add("O");
        tree.add("R");
        tree.add("F");
        String dataRemoved = tree.remove("R");
        assertEquals("returns removed node", "R", dataRemoved);
        assertEquals("tree looks right", "M\n E\n  G\n   F\n P\n  O", tree.showTree());
    }

    @Test
    public void testRemove_root() {
        tree.add("M");
        tree.add("E");
        tree.add("P");
        tree.add("G");
        tree.add("O");
        tree.add("R");
        tree.add("F");
        String dataRemoved = tree.remove("M");
        assertEquals("returns removed node", "M", dataRemoved);
        assertEquals("tree looks right", "G\n E\n  F\n P\n  O\n  R", tree.showTree());
    }

    @Test
    public void testRemove_equalNodes() {
        tree.add("M");
        tree.add("E");
        tree.add("P");
        tree.add("E");
        tree.add("F");
        tree.add("P");
        String dataRemoved = tree.remove("E");

        assertEquals("returns removed node", "E", dataRemoved);
        assertEquals("tree looks right", "M\n E\n  F\n P\n  P", tree.showTree());

        dataRemoved = tree.remove("P");
        assertEquals("returns removed node", "P", dataRemoved);
        assertEquals("tree looks right", "M\n E\n  F\n P", tree.showTree());
    }
}
