/* NetId(s): mb2363, xxxnn. Time spent: h hours, mm minutes.

 * Name(s):
 * What I thought about this assignment:
 * 
 *
 */


import java.util.LinkedList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/** An instance of RepostTree represents the spreading of a Post through
 * a (social) Network of people.
 * The root of the RepostTree is the original poster. From there, each
 * person in the RepostTree is the child of the person from whom they saw the post.
 * For example, for the tree:
 * <p>
 *      A
 *     / \
 *    B   C
 *       / \
 *      D   E
 * <p>
 * Person A originally created the post, B and C saw A's post, 
 * C reshared the post, and D and E saw the post from C.
 * <p>
 * Important note: The name of each person in the RepostTree is unique.
 *
 * @author Mshnik and ebirrell
 */
public class RepostTree {

    /** The String to be used as a separator in toString() */
    public static final String SEPARATOR = " - ";

    /** The String that marks the start of children in toString() */
    public static final String START_CHILDREN_DELIMITER = "[";

    /**  The String that divides children in toString() */
    public static final String DELIMITER = ", ";

    /** The String that marks the end of children in toString() */
    public static final String END_CHILDREN_DELIMITER = "]";

    /** The String that is the space increment in toStringVerbose() */
    public static final String VERBOSE_SPACE_INCREMENT = "\t";

    /** The person at the root of this RepostTree.
     * This is the original poster, who first create the content. 
     * root is non-null.
     * All Person's in a RepostTree have different names. There are no duplicates
     */
    private Person root;

    /** The immediate children of this RepostTree node.
     * Each element of children saw the post from the person at this node.
     * root is non-null but will be an empty set if this is a leaf. */
    private Set<RepostTree> children;

    /** Constructor: a new RepostTree with root p and no children.
     * Throw an IllegalArgumentException if p is null. */
    public RepostTree(Person p) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("Can't construct RepostTree with null root");
        root= p;
        children= new HashSet<>();
    }

    /** Constructor: a new RepostTree that is a copy of tree p.
     * Tree p and its copy have no node in common (but nodes can share a Person).
     * Throw an IllegalArgumentException if p is null. */
    public RepostTree(RepostTree p) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("Can't construct RepostTree as copy of null");
        root= p.root;
        children= new HashSet<>();

        for (RepostTree rt : p.children) {
            children.add(new RepostTree(rt));
        }
    }

    /** Return the person that is at the root of this RepostTree */
    public Person getRoot() {
        return root;
    }

    /** Return the number of direct children of this RepostTree */
    public int getChildrenCount() {
        return children.size();
    }

    /** Return a COPY of the set of children of this RepostTree. */
    public Set<RepostTree> getChildren() {
        return new HashSet<>(children);
    }

    /** Insert c in this RepostTree as a child of p and
     * return the RepostTree whose root is the new child.
     * Throw an IllegalArgumentException if:<br>
     * -- p or c is null,<br>
     * -- c is already in this RepostTree, or<br>
     * -- p is not in this RepostTree */
    public RepostTree insert(Person c, Person p) throws IllegalArgumentException {
        //TODO 1. This function has a simple, non-recursive implementation
    		if (p == null || c == null || this.contains(c) || !this.contains(p)) {
    			throw new IllegalArgumentException();
    		}
    		RepostTree rt= new RepostTree(c);
    		RepostTree rt2 = getTree(p);
    		rt2.children.add(rt);
        return rt;
    }

    /** Return the number of people in this RepostTree.
     * Note: If this is a leaf, the size is 1 (just the root) */
    public int size() {
        //TODO 2
    	if (children.size() == 0) {
			return 1;
		}
		
		int size= 1;
		for (RepostTree temp: children) {
			size = size + temp.size();
		}
		return size;
    }

    /**Return the depth at which p occurs in this RepostTree, or -1
     * if p is not in the RepostTree.
     * Note: depth(root) is 0.
     * If p is a child of this RepostTree, then depth(p) is 1. etc. */
    public int depth(Person p) {
        //TODO 3
	    	if (root == p) {
	    		return 0;
	    	}
	    	if (root != p && children.size() == 0) {
	    		return -1;
	    	}
	    	int nodeDepth= 0;
	    	for (RepostTree temp: children) {
	    		int result= 1+temp.depth(p);
	    		if (result > 0) {
	    			nodeDepth= result;
	    			return nodeDepth;
	    		}
	    	}
	
	    	return -1;

    }

    /** If p is in this tree, return the RepostTree object in this tree 
     * that contains p. If p is not in this tree, return null.
     * <p>
     * Example: Calling getTree(root) should return this.
     */
    public RepostTree getTree(Person p) {
        if (root == p) return this; //Base case - look here

        // Recursive case - ask children to look
        for (RepostTree rt : children) {
            RepostTree search= rt.getTree(p);
            if (search != null) return search;
        }

        return null; // Not found
    }

    /** Return true iff this RepostTree contains p. */
    public boolean contains(Person p) {
        /* Note: This RepostTree contains p iff the root of this
         * RepostTree is p or if one of p's children contains p. */
        if (root == p) return true;
        for (RepostTree rt : children) {
            if (rt.contains(p)) return true;
        }
        return false;
    }


    /** Return the maximum depth of this RepostTree, i.e. the longest path from
     * the root to a leaf. Example. If this RepostTree is a leaf, return 0.
     */
    public int maxDepth() {
        int maxDepth= 0;
        for (RepostTree rt : children) {
            maxDepth= Math.max(maxDepth, rt.maxDepth() + 1);
        }
        return maxDepth;
    }

    /** Return the width of this tree at depth d (i.e. the number of repost 
     * trees that occur at depth d, where the depth of the root is 0.
     * Throw an IllegalArgumentException if depth < 0.
     * Thus, for the following tree :
     * Depth level:
     * 0       A
     *        / \
     * 1     B   C
     *      /   / \
     * 2   D   E   F
     *              \
     * 3             G
     * <p>
     * A.widthAtDepth(0) = 1,  A.widthAtDepth(1) = 2,
     * A.widthAtDepth(2) = 3,  A.widthAtDepth(3) = 1,
     * A.widthAtDepth(4) = 0.
     * C.widthAtDepth(0) = 1,  C.widthAtDepth(1) = 2
     */
    public int widthAtDepth(int d) throws IllegalArgumentException {
        //TODO 4
        // For d > 0, the width at depth d is the sum of the widths
        //            of the children at depth d-1.
	    	if (d < 0) {
	    		throw new IllegalArgumentException();
	    	}
	    	if (d == 0) {
	    		return 1;
	    	}
	    	int width= 0;
	    	for (RepostTree temp: children) {
	    		width= width + temp.widthAtDepth(d-1);
	    	}
	
	    	return width;
    }

    /** Return the maximum width of all the widths in this tree, i.e. the
     * maximum value that could be returned from widthAtDepth for this tree.
     */
    public int maxWidth() {
        return maxWidthImplementationOne(this);
    }

    // Simple implementation of maxWith. Relies on widthAtDepth.
    // Takes time proportional the the square of the size of the t.
    static int maxWidthImplementationOne(RepostTree t) {
        int width = 0;
        int depth = t.maxDepth();
        for (int i = 0; i <= depth; i++) {
            width = Math.max(width, t.widthAtDepth(i));
        }
        return width;
    }

    /* Better implementation of maxWidth. Caches results in an array.
     * Takes time proportional to the size of t. */
    static int maxWidthImplementationTwo(RepostTree t) {
        // For each integer d, 0 <= d <= maximum depth of t, store in
        // widths[d] the number of nodes at depth d in t.
        // The calculation is done by calling recursive procedure addToWidths.
        int[] widths = new int[t.maxDepth() + 1];   // initially, contains 0's
        t.addToWidths(0, widths);

        int max = 0;
        for (int width : widths) {
            max = Math.max(max, width);
        }
        return max;
    }

    /* For each node of this RepostTree that is at some depth d in this
     * RepostTree add 1 to widths[depth + d]. */
    private void addToWidths(int depth, int[] widths) {
        widths[depth]++;        //the root of this SharintTree is at depth d = 0
        for (RepostTree dt : children) {
            dt.addToWidths(depth + 1, widths);
        }
    }

    /* Better implementation of maxWidth. Caches results in a HashMap.
     * Takes time proportional to the size of t. */
    static int maxWidthImplementationThree(RepostTree t) {
        // For each possible depth d >= 0 in tree t, widthMap will contain the
        // entry (d, number of nodes at depth d in t). The calculation is
        // done using recursive procedure addToWidthMap.

        // For each integer d, 0 <= d <= maximum depth of t, add to
        // widthMap an entry <d, 0>.
        HashMap<Integer, Integer> widthMap = new HashMap<>();
        for (int d= 0; d <= t.maxDepth() + 1; d= d+1) {
            widthMap.put(d, 0);
        }

        t.addToWidthMap(0, widthMap);

        int max= 0;
        for (Integer w : widthMap.values()) {
            max= Math.max(max, w);
        }
        return max;
    }

    /* For each node of this RepostTree that is at some depth d in this RepostTree,
     * add 1 to the value part of entry <depth + d, ...> of widthMap. */
    private void addToWidthMap(int depth, HashMap<Integer, Integer> widthMap) {
        widthMap.put(depth, widthMap.get(depth) + 1);  //the root is at depth d = 0
        for (RepostTree dt : children) {
            dt.addToWidthMap(depth + 1, widthMap);
        }
    }

    /** Return the route the Post took to get from "here" (the root of
     * this RepostTree) to child c.
     * Return null if no such route.
     * For example, for this tree:
     * <p>
     * Depth level:
     * 0      A
     *       / \
     * 1    B   C
     *     /   / \
     * 2  D   E   F
     *             \
     * 3            G
     * <p>
     * A.getRepostRoute(E) should return a list of [A, C, E].
     * A.getRepostRoute(A) should return [A].
     * A.getRepostRoute(X) should return null.
     * <p>
     * B.getRepostRoute(C) should return null.
     * B.getRepostRoute(D) should return [B, D]
     */
    public List<Person> getRepostRoute(Person c) {
        //TODO 5
        // Hint: You have to return a List<Person>. But List is an
        // interface, so use something that implements it.
        // LinkedList<Person> is preferred to ArrayList<Person> here.
        // The reason is a hint on how to use it.
        // Base Case - The root of this RepostTree is c. Route is just [child]

	    	List<Person> lp= new LinkedList<Person>();
	    	if (!contains(c)) {
	    		return null;
	    	}
	    	lp.add(root);
	    	if (c==root) {
	    		return lp;
	    	}
	    	for (RepostTree temp: children) {
	    		if (temp.contains(c)) {
	    			lp.addAll(temp.getRepostRoute(c));
	    		}
	    	}
	    	return lp;
    }

    /** Return the immediate parent of c (null if c is not in this
     * RepostTree).
     * <p>
     * Thus, for the following tree:
     * Depth level:
     * 0      A
     *       / \
     * 1    B   C
     *     /   / \
     * 2  D   E   F
     *     \
     * 3    G
     * <p>
     * A.getParent(E) returns C.
     * C.getParent(E) returns C.
     * A.getParent(B) returns A.
     * E.getParent(F) returns null.
     */
    public Person getParent(Person c) {
        // Base case
        for (RepostTree rt : children) {
            if (rt.root == c) return root;
        }

        // Recursive case - ask children to look
        for (RepostTree rt : children) {
            Person parent = rt.getParent(c);
            if (parent != null) return parent;
        }

        return null; //Not found
    }

    /** If either child1 or child2 is null or is not in this RepostTree, return null.
     * Otherwise, return the person at the root of the smallest subtree of this
     * RepostTree that contains child1 and child2.
     * <p>
     * Examples. For the following tree (which does not contain H):
     * <p>
     * Depth level:
     * 0      A
     *       / \
     * 1    B   C
     *     /   / \
     * 2  D   E   F
     *     \
     * 3    G
     * <p>
     * A.getSharedAncestor(B, A) is A
     * A.getSharedAncestor(B, B) is B
     * A.getSharedAncestor(B, C) is A
     * A.getSharedAncestor(A, C) is A
     * A.getSharedAncestor(E, F) is C
     * A.getSharedAncestor(G, F) is A
     * B.getSharedAncestor(B, E) is B
     * B.getSharedAncestor(B, A) is null
     * B.getSharedAncestor(D, F) is null
     * B.getSharedAncestor(D, H) is null
     * A.getSharedAncestor(null, C) is null
     */
    public Person getSharedAncestor(Person child1, Person child2) {
        //TODO 6
        // It's possible to get a lot of getParent calls to do this,
        // but that can be inefficient. Instead, try building the
        // sharing routes to child1 and child2 and figure out the
        // largest k such that l1[0..k] and l2[0..k] are equal.
               
	    	if (child1 == child2) return child1;
	    	if(child1 == null || child2 == null) return null;
	    	if(getParent(child1) == child2) return child2;
	    	if(getParent(child2) == child1) return child1;
	    	if(getParent(child1) == getParent(child2)) return getParent(child1);
	    	if(!this.contains(child1) || !this.contains(child2)) return null;
	
	    	return FindAncestor(this, child1, child2);
    }
    
    
    /**
     * Recursive method to traverse the tree and find the common ancestor of the 
     * two children
     * @param tree
     * @param child1
     * @param child2
     * @return the common ancestor of type Person
     */
    private Person FindAncestor(RepostTree tree, Person child1, Person child2){
    	
	    	for(RepostTree st: tree.children){
	    		if(st.contains(child1) && st.contains(child2))
	    			return FindAncestor(st, child1, child2);
	    	}
	    	return tree.root;
    }

    /** Return a (single line) String representation of this RepostTree.
     * If this RepostTree has no children (it is a leaf), return the root's substring.
     * Otherwise, return
     * root's substring + SEPARATOR + START_CHILDREN_DELIMITER + each child's
     * toString, separated by DELIMITER, followed by END_CHILD_DELIMITER.
     * Make sure there is not an extra DELIMITER following the last child.
     * <p>
     * Finally, make sure to use the static final fields declared at the top of
     * RepostTree.java.
     * <p>
     * Thus, for the following tree:
     * Depth level:
     * 0      A
     *       / \
     * 1    B   C
     *     /   / \
     * 2  D   E   F
     *         \
     * 3        G
     * A.toString() should print:
     * (A) - HEALTHY - [(C) - HEALTHY - [(F) - HEALTHY, (E) - HEALTHY - [(G) - HEALTHY]], (B) - HEALTHY - [(D) - HEALTHY]]
     * <p>
     * C.toString() should print:
     * (C) - HEALTHY - [(F) - HEALTHY, (E) - HEALTHY - [(G) - HEALTHY]]
     */
    public String toString() {
        if (children.isEmpty()) return root.toString();
        String s = root.toString() + SEPARATOR + START_CHILDREN_DELIMITER;
        for (RepostTree dt : children) {
            s = s + dt.toString() + DELIMITER;
        }
        return s.substring(0, s.length() - 2) + END_CHILDREN_DELIMITER;
    }


    /** Return a verbose (multi-line) string representing this RepostTree. */
    public String toStringVerbose() {
        return toStringVerbose(0);
    }

    /** Return a verbose (multi-line) string representing this RepostTree.
     * Each person in the tree is on its own line, with indentation representing
     * what each person is a child of.
     * indent is the the amount of indentation to put before this line.
     * Should increase on recursive calls to children to create the above pattern.
     * Thus, for the following tree:
     * Depth level:
     * 0      A
     *       / \
     * 1    B   C
     *     /   / \
     * 2  D   E   F
     *         \
     * 3        G
     * <p>
     * A.toStringVerbose(0) should return:
     * (A) - HEALTHY
     *    (C) - HEALTHY
     *       (F) - HEALTHY
     *       (E) - HEALTHY
     *         (G) - HEALTHY
     *    (B) - HEALTHY
     *        (D) - HEALTHY
     * <p>
     * Make sure to use VERBOSE_SPACE_INCREMENT for indentation.
     */
    private String toStringVerbose(int indent) {
        String s = "";
        for (int i = 0; i < indent; i++) {
            s = s + VERBOSE_SPACE_INCREMENT;
        }
        s = s + root.toString();

        if (children.isEmpty()) return s;

        for (RepostTree dt : children) {
            s = s + "\n" + dt.toStringVerbose(indent + 1);
        }
        return s;
    }

    /** Return true iff this is equal to ob.
     * If ob is not a RepostTree, it cannot be equal to this RepostTree, return false.
     * Two RepostTrees are equal if they are the same object (==) or:
     * <br> - they have the same root Person object (==)
     * <br> - their children sets are the same size:
     * <br> --their children sets are equal, which, since their sizes
     * <br> --are equal, requires:
     * <br> --- for every RepostTree rt in one set there is a RepostTree rt2
     *            in the other set for which rt.equals(rt2) is true.
     * <p>
     * Otherwise the two RepostTrees are not equal.
     * Do not use any of the toString functions to write equals(). */
    public boolean equals(Object ob) {
        //TODO 7
        // Hint about checking whether each child of one tree equals SOME
        // other tree of the other tree's children.
        // First, you have to check them all until you find an equal one (or
        // return false if you don't.)
        // Second, you know that a child of one tree cannot equal more than one
        // child of another tree because the names of Person's are all unique;
        // there are no duplicates.

	    	if (!(ob instanceof RepostTree)) {
	    		return false;
	    	}
	    	if (this == ob) {
	    		return true;
	    	}
	    	if ((getRoot() != ((RepostTree) ob).getRoot())) {
	    		return false;
	    	} else {
	    		if (getChildrenCount() != ((RepostTree) ob).getChildrenCount()) {
	    			return false;
	    		} else {
	    			for (RepostTree rt : children) {
	    				if (!EqualtoChild(rt, ((RepostTree) ob).children)) {
	    					return false;
	    				}
	    			}
	    		}
	    	}
	    	return true;

    }

    /** Return true iff SharingTree equals some tree in the set of Sharing trees c. 
     * This is a helper function for Equals to search through all the nodes
     * @return True iff the the Sharing Tree is equal to a Tree in the set of Sharing Trees
     * @param st is a SharingTree child that is not null
     * @param c is a set of SharingTrees to search through
     **/
    public static boolean EqualtoChild(RepostTree rt, Set<RepostTree> c) {
	    	for (RepostTree ch2 : c) {
	    		if (rt.equals(ch2)) {
	    			return true;
	    		}
	    	}
	    	return false;
    }

}
