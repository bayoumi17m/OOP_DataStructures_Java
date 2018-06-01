package LinkedList;

/* Time spent on a3:  0 hours and 45 minutes.
 *
 * When you change the above, please do it carefully. Change hh to
 * the hours and mm to the minutes and leave everything else as is.
 * If the minutes are 0, change mm to 0. This will help us in
 * extracting times and giving you the average and max.
 * 
 * Name: Magd Bayoumi
 * Netid: mb2363
 * What I thought about this assignment:
 * I really enjoyed this assignment a lot, it was really nice trying to figure out, using this new structure
 * what the optimal ways to actually manipulate and view the data was. I wish we had been given more methods
 * to implement although I do understand why there must be limits to what we do due to time. 
 *
 *
 */

/** An instance is a doubly linked list. */
public class DLList<E>  {
    private Node first; // first node of linked list (null if size is 0)
    private Node last;  // last node of linked list (null if size is 0)
    private int size;   // Number of values in the linked list.

    /** Constructor: an empty linked list. */
    public DLList() {
    }

    /** Return the number of values in this list.
     *  This function takes constant time. */
    public int size() {
        return size;
    }

    /** Return the first node of the list (null if the list is empty). */
    public Node first() {
        return first;
    }

    /** Return the last node of the list (null if the list is empty). */
    public Node last() {
        return last;
    }

    /** Return the value of node n of this list.
     * Precondition: n is a node of this list; it may not be null. */
    public E value(Node n) {
        assert n != null;
        return n.val;
    }

    /** Return a representation of this list: its values, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Takes time proportional to the length of this list.<br>
     * E.g. for the list containing 4 7 8 in that order, the result it "[4, 7, 8]".
     * E.g. for the list containing two empty strings, the result is "[, ]" */
    public String toString() {
        String res= "[";
        Node n= first;
        // inv: res contains values of nodes before node n (all of them if n = null),
        //      with ", " after each (except for the last value)
        while (n != null) {
            res= res + n.val;
            n= n.next;
            if (n != null) {
                res= res + ", ";
            }
        }

        return res + "]";
    }

    /** Return a representation of this list: its values in reverse, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Note that gnirtSot is the reverse of toString.
     * Takes time proportional to the length of this list.
     * E.g. for the list containing 4 7 8 in that order, the result is "[8, 7, 4]".
     * E.g. for the list containing two empty strings, the result is "[, ]". */
    public String gnirtSot() { // Note: 
        //TODO 1. Look at toString to see how that was written.
        //        Use the same scheme. Extreme case to watch out for:
        //        E is String and values are the empty string.
        //        You can't test this fully until #2, prepend, is written.
    		String res= "[";
        Node n= last;
        // inv: res contains values of nodes after node n (all of them if n = null),
        //      with ", " after each (except for the last value)
        while (n != null) {
        		res= res + n.val;
        		n= n.prev;
        		if (n != null) {
        			res= res + ", ";
        		}
        }

        return res + "]";
    }
    

    /** Add value v in a new node at the front of the list.
     * This operation takes constant time. */
    public void prepend(E v) {
        //TODO 2. After writing this method, test this method and
        //        method gnirtSot thoroughly before starting on the next
        //        method. These two must be correct in order to be
        //        able to write and test all the others.
	    	if (first == null) {
	    		first= new Node(null,v,null);
	    		last= first;
	    	} else {
	    		first= new Node(null,v,first);
	    		first.next().prev= first; 
	    	}
	    	size++;
    }

    /** add value v in a new node at the end of the list.
     *  This operation takes constant time. */
    public void append(E v) {
        //TODO 3. This is the third method to write and test
	    	if (first == null) {
	    		first= new Node(null,v,null);
	    		last= first;
	    	} else {
	    		last= new Node(last,v,null);
	    		last.prev().next= last;
	    	}
	    	size++;
    }


    /** Return node number k. 
     *  Precondition: 0 <= k < size of the list.
     *  If k is 0, return first node; if k = 1, return second node, ... */
    public Node getNode(int k) {
        //TODO 4. This method should take time proportional to min(k, size-k).
        // For example, if k <= size/2, search from the beginning of the
        // list, otherwise search from the end of the list.
	    	if (k < 0 || k >= size) {
	    		throw new IndexOutOfBoundsException();
	    	}
	
	    	Node n;
	    	if (k <= (size/2)) {
	    		n= first;
	    		for (int i= 0; i < k; i++) {
	    			n= n.next();
	    		}  
	    	} else {
	    		n= last;
	    		for (int i=size; i > k+1; i--) {
	    			n= n.prev();
	    		}
	    	}
	    	return n;
    }
    
    /** Remove node n from this list.
     * This operation must take constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void delete(Node n) {
	    	//TODO 5. Make sure this method takes constant time.
	    	if (n == first && n == last) {
	    		first= last= null;
	    		size--;
	    	} else if (n == first) {
	    		first= n.next();
	    		first.prev= null;
	    		size--;
	    	} else if (n == last) {
	    		last= n.prev();
	    		last.next= null;
	    		size--;
	    	} else {
	    		n.prev().next= n.next();
	    		n.next().prev= n.prev();
	    		size--;
	    	}

    }

    /** Insert value v in a new node after node n.
     * This operation takes constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void insertAfter(E v, Node n) {
        //TODO 6. Make sure this method takes constant time.
	    	assert n != null;
	    	Node k= null;
	
	    	if (n == last) {
	    		k= new Node(n,v,null);
	    		n.next= k;
	    		last= k;
	    	} else {
	    		k= new Node(n,v,n.next());
	    		n.next().prev= k;
	    		n.next= k;
	    	}
	    	size++; 
    }

 

    /*********************/

    /** An instance is a node of this list. */
    public class Node {
        private Node prev; // Previous node on list (null if this is first node)
        private E val;     // The value of this element
        private Node next; // Next node on list. (null if this is last node)

        /** Constructor: an instance with previous node p (can be null),
         * value v, and next node n (can be null). */
        Node(Node p, E v, Node n) {
            prev= p;
            val= v;
            next= n;
        }

        /** Return the node previous to this one (null if this is the
         * first node of the list). */
        public Node prev() {
            return prev;
        }

        /** Return the value of this node. */
        public E value() {
            return val;
        }

        /** Return the next node in this list (null if this is the
         * last node of this list). */
        public Node next() {
            return next;
        }
    }

}
