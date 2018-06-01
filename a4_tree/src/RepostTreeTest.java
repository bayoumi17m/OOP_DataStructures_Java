import static org.junit.Assert.*;
import static common.JUnitUtil.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

public class RepostTreeTest {

    private static Network n;
    private static Person[] people;

    @BeforeClass
    public static void setup(){
        n= new Network();
        people= new Person[]{new Person("A", n, 0), new Person("B", n, 0), new Person("C", n, 0),
                new Person("D", n, 0), new Person("E", n, 0), new Person("F", n, 0),
                new Person("G", n, 0), new Person("H", n, 0), new Person("I", n, 0),
                new Person("J", n, 0), new Person("K", n, 0), new Person("L", n, 0)
        };
    }

    @Test
    public void testBuiltInGetters() {
        RepostTree st= new RepostTree(people[1]);
        assertEquals("B", toStringBrief(st));
    }
    
    
    
    /** Create a RepostTree with structure A[B[D E F[G[H[I]]]] C]
     * Doesn't rely on the add(..) method of RepostTree. */ 
    private RepostTree makeTree1() {
        RepostTree dt = new RepostTree(people[0]); // A
        dt.insert(people[1], people[0]); // A, B
        dt.insert(people[2], people[0]); // A, C
        dt.insert(people[3], people[1]); // B, D
        dt.insert(people[4], people[1]); // B, E
        dt.insert(people[5], people[1]); // B, F
        dt.insert(people[6], people[5]); // F, G
        dt.insert(people[7], people[6]); // G, H
        dt.insert(people[8], people[7]); // H, I
        
        return new RepostTree(dt); //Clone over to BugTree
    }
    
    @Test
    public void testMakeTree1() {
        RepostTree dt= makeTree1();
        assertEquals("A[B[D E F[G[H[I]]]] C]", toStringBrief(dt)); 
    }

    @Test
    public void testInsert() {
        RepostTree st= new RepostTree(people[1]); 

        //Test add to root
        RepostTree dt2= st.insert(people[2], people[1]);
        assertEquals("B[C]", toStringBrief(st));
        assertEquals(people[2], dt2.getRoot());
        
        RepostTree dt3= st.insert(people[0],people[1]);
        assertEquals(people[0],dt3.getRoot());
        assertEquals("B[A C]",toStringBrief(st));
        
        RepostTree dt4= dt2.insert(people[3], people[2]);
        assertEquals(people[3],dt4.getRoot());
        assertEquals("C[D]",toStringBrief(dt2));
        assertEquals("B[A C[D]]",toStringBrief(st));
        
        RepostTree dt5= st.insert(people[8], people[2]);
        assertEquals(people[8],dt5.getRoot());
        assertEquals("B[A C[D I]]",toStringBrief(st));
    }
    
    @Test
    public void testSize() {
        RepostTree st= new RepostTree(people[1]); 
        assertEquals(1, st.size());
        
        RepostTree dt2= st.insert(people[2], people[1]);
        assertEquals(2,st.size());
        assertEquals(1,dt2.size());
        
        RepostTree dt3= st.insert(people[0],people[1]);
        assertEquals(3,st.size());
        assertEquals(1,dt3.size());
        
        RepostTree dt4= dt2.insert(people[3], people[2]);
        assertEquals(4,st.size());
        assertEquals(2,dt2.size());
        assertEquals(1,dt4.size());
    }
    
    @Test
    public void testDepth() {
        RepostTree st= new RepostTree(people[1]);
        assertEquals(0, st.depth(people[1]));
        assertEquals(-1, st.depth(people[5]));
        
        RepostTree dt2= st.insert(people[2], people[1]);
        assertEquals(0,st.depth(people[1]));
        assertEquals(1,st.depth(people[2]));
        assertEquals(0,dt2.depth(people[2]));
        assertEquals(-1,st.depth(people[5]));
        assertEquals(-1,dt2.depth(people[5]));
        
        RepostTree dt3= st.insert(people[0],people[1]);
        assertEquals(0,st.depth(people[1]));
        assertEquals(1,st.depth(people[0]));
        assertEquals(0,dt3.depth(people[0]));
        assertEquals(-1,st.depth(people[5]));
        assertEquals(-1,dt2.depth(people[5]));
        assertEquals(-1,dt3.depth(people[5]));
        
        RepostTree dt4= dt2.insert(people[3], people[2]);
        assertEquals(0,dt2.depth(people[2]));
        assertEquals(1,st.depth(people[2]));
        assertEquals(1,dt2.depth(people[3]));
        assertEquals(0,dt4.depth(people[3]));
        assertEquals(2,st.depth(people[3]));
        assertEquals(-1,st.depth(people[5]));
        assertEquals(-1,dt2.depth(people[5]));
        assertEquals(-1,dt3.depth(people[5]));
        assertEquals(-1,dt4.depth(people[5]));
    }
    
    @Test
    public void testWidthAtDepth() {
        RepostTree st= new RepostTree(people[0]); 
        assertEquals(1, st.widthAtDepth(0));
        
        RepostTree st2= st.insert(people[1], people[0]);
        RepostTree st3= st.insert(people[2], people[0]);
        RepostTree st4= st2.insert(people[3], people[1]);
        RepostTree st5= st3.insert(people[4], people[2]);
        RepostTree st6= st3.insert(people[5], people[2]);
        RepostTree st7= st6.insert(people[6], people[5]);
        
        assertEquals(2,st.widthAtDepth(1));
        assertEquals(3,st.widthAtDepth(2));
        assertEquals(1,st.widthAtDepth(3));
        assertEquals(0,st.widthAtDepth(4));
        assertEquals(1,st3.widthAtDepth(0));
        assertEquals(2,st3.widthAtDepth(1)); 
        assertEquals(1,st7.widthAtDepth(0));
        assertEquals(1,st5.widthAtDepth(0));
        assertEquals(1,st4.widthAtDepth(0));
        assertEquals(0,st7.widthAtDepth(1));
        assertEquals(0,st5.widthAtDepth(1));
        assertEquals(0,st4.widthAtDepth(1));
    }
    
    @Test
    public void testGetRepostRoute() {
        RepostTree st= new RepostTree(people[0]);
        List<Person> ll= new LinkedList<>();
        ll.add(people[0]);
        assertEquals(ll, st.getRepostRoute(people[0]));
        
        RepostTree st2= st.insert(people[1], people[0]);
        RepostTree st3= st.insert(people[2], people[0]);
        RepostTree st4= st2.insert(people[3], people[1]);
        RepostTree st5= st3.insert(people[4], people[2]);
        RepostTree st6= st3.insert(people[5], people[2]);
        RepostTree st7= st6.insert(people[6], people[5]);
        
        ll.add(people[2]);
        ll.add(people[4]);
        assertEquals(ll,st.getRepostRoute(people[4]));
        ll.remove(people[2]);
        ll.remove(people[4]);
        assertEquals(null,st.getRepostRoute(people[7]));
        assertEquals(null,st2.getRepostRoute(people[2]));
        ll.add(people[1]);
        ll.add(people[3]);
        ll.remove(people[0]);
        assertEquals(ll,st2.getRepostRoute(people[3]));
    }
    
    @Test
    public void testGetSharedAncestor() {
        //RepostTree st= makeTree1();
        // A.testSharedAncestorOf(A, A) is A
    		RepostTree st= new RepostTree(people[0]);
        assertEquals(people[0], st.getSharedAncestor(people[0], people[0]));
        
        
        RepostTree st2= st.insert(people[1], people[0]);
        RepostTree st3= st.insert(people[2], people[0]);
        RepostTree st4= st2.insert(people[3], people[1]);
        RepostTree st5= st3.insert(people[4], people[2]);
        RepostTree st6= st3.insert(people[5], people[2]);
        RepostTree st7= st6.insert(people[6], people[5]);
        
        assertEquals(people[0],st.getSharedAncestor(people[0], people[1]));
        assertEquals(people[1],st2.getSharedAncestor(people[1], people[1]));
        assertEquals(people[0],st.getSharedAncestor(people[1], people[2]));
        assertEquals(people[0],st.getSharedAncestor(people[0], people[6]));
        assertEquals(people[5],st.getSharedAncestor(people[5], people[6]));
        assertEquals(people[1],st2.getSharedAncestor(people[1],people[1]));
        assertEquals(null,st2.getSharedAncestor(people[3], people[5]));
        assertEquals(null,st2.getSharedAncestor(people[3], people[8]));
        assertEquals(null,st.getSharedAncestor(null, people[2]));
       
    }
    
    @Test
    public void testEquals() {
    		RepostTree st= new RepostTree(people[0]);
        assertEquals(st, st);
        
        RepostTree st2= st.insert(people[1], people[0]);
        RepostTree st3= st.insert(people[2], people[1]);
        RepostTree st4= st2.insert(people[3], people[1]);
        RepostTree st5= st3.insert(people[4], people[2]);
        RepostTree st6= st3.insert(people[5], people[2]);
        RepostTree st7= st4.insert(people[6], people[3]);
        
        RepostTree dt= new RepostTree(people[0]);
        assertEquals(dt, dt);
        
        RepostTree dt2= dt.insert(people[1], people[0]);
        RepostTree dt3= dt.insert(people[2], people[1]);
        RepostTree dt4= dt2.insert(people[3], people[1]);
        RepostTree dt5= dt3.insert(people[4], people[2]);
        RepostTree dt6= dt3.insert(people[5], people[2]);
        RepostTree dt7= dt4.insert(people[6], people[3]);
        
        assert(st.equals(dt));
        assert(st2.equals(dt2));
        assert(st3.equals(dt3));
        assert(st4.equals(dt4));
        assert(st5.equals(dt5));
        assert(st6.equals(dt6));
        assert(st7.equals(dt7));
    }
    
    
    
    /** Return a representation of this tree. This representation is:
     * (1) the name of the Person at the root, followed by
     * (2) the representations of the children (in alphabetical
     *     order of the children's names).
     * There are two cases concerning the children.
     *
     * No children? Their representation is the empty string.
     * Children? Their representation is the representation of each child, with
     * a blank between adjacent ones and delimited by "[" and "]".
     * Examples:
     * One-node tree: "A"
     * root A with children B, C, D: "A[B C D]"
     * root A with children B, C, D and B has a child F: "A[B[F] C D]"
     */
    public static String toStringBrief(RepostTree t) {
        String res= t.getRoot().getName();

        Object[] childs= t.getChildren().toArray();
        if (childs.length == 0) return res;
        res= res + "[";
        selectionSort1(childs);

        for (int k= 0; k < childs.length; k= k+1) {
            if (k > 0) res= res + " ";
            res= res + toStringBrief(((RepostTree)childs[k]));
        }
        return res + "]";
    }

    /** Sort b --put its elements in ascending order.
     * Sort on the name of the Person at the root of each RepostTree
     * Throw a cast-class exception if b's elements are not RepostTrees */
    public static void selectionSort1(Object[] b) {
        int j= 0;
        // {inv P: b[0..j-1] is sorted and b[0..j-1] <= b[j..]}
        // 0---------------j--------------- b.length
        // inv : b | sorted, <= | >= |
        // --------------------------------
        while (j != b.length) {
            // Put into p the index of smallest element in b[j..]
            int p= j;
            for (int i= j+1; i != b.length; i++) {
                String bi= ((RepostTree)b[i]).getRoot().getName();
                String bp= ((RepostTree)b[p]).getRoot().getName();
                if (bi.compareTo(bp) < 0) {
                    p= i;

                }
            }
            // Swap b[j] and b[p]
            Object t= b[j]; b[j]= b[p]; b[p]= t;
            j= j+1;
        }
    }

}
