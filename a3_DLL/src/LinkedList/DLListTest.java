package LinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DLListTest {

	@Test
	public void testConstructor() {
	DLList<Integer> b= new DLList<Integer>(); 
	assertEquals("[]", b.toString());
	assertEquals("[]", b.gnirtSot()); 
	assertEquals(0, b.size());
	}
	
	@Test
	public void testAppend() {
		DLList<String> ll= new DLList<String>();
		ll.append("Sampson");
		assertEquals("[Sampson]", ll.toString());
		assertEquals("[Sampson]", ll.gnirtSot());
		assertEquals(1, ll.size());
		
		ll.append("Kaitlyn");
		assertEquals("[Sampson, Kaitlyn]", ll.toString());
		assertEquals("[Kaitlyn, Sampson]", ll.gnirtSot());
		assertEquals(2, ll.size());
	}
	
	@Test
	public void testPrepend() {
		DLList<String> ll= new DLList<String>();
		ll.prepend("Sampson");
		assertEquals("[Sampson]", ll.toString());
		assertEquals("[Sampson]", ll.gnirtSot());
		assertEquals(1, ll.size());
		
		ll.prepend("Kaitlyn");
		assertEquals("[Sampson, Kaitlyn]", ll.gnirtSot());
		assertEquals("[Kaitlyn, Sampson]", ll.toString());
		assertEquals(2, ll.size());
	}
	
	@Test
	public void testgetNode() {
		DLList<Integer> z= new DLList<Integer>();
		z.append(9);
		z.append(3);
		z.append(5);
		z.prepend(1);
		assertEquals(4, z.size());
		assertEquals("[1, 9, 3, 5]", z.toString());
		assertEquals("[5, 3, 9, 1]", z.gnirtSot());
		assertEquals(9, (int) z.getNode(1).value());
		assertEquals(1, (int) z.getNode(1).prev().value());
		assertEquals(3, (int) z.getNode(1).next().value());
		assertEquals(3, (int) z.getNode(2).value());
		assertEquals(9, (int) z.getNode(2).prev().value());
		assertEquals(5, (int) z.getNode(2).next().value());
		assertEquals(5, (int) z.getNode(3).value());
		assertEquals(3, (int) z.getNode(3).prev().value());
	}
	
	@Test
	public void testdelete() {
		DLList<Integer> z= new DLList<Integer>();
		z.append(1);
		z.append(2);
		z.append(3);
		z.append(4);
		z.append(5);
		assertEquals("[1, 2, 3, 4, 5]", z.toString());
		assertEquals("[5, 4, 3, 2, 1]", z.gnirtSot());
		
		z.delete(z.getNode(2));
		assertEquals("[1, 2, 4, 5]", z.toString());
		assertEquals("[5, 4, 2, 1]", z.gnirtSot());
		
		z.delete(z.getNode(0));
		assertEquals("[2, 4, 5]", z.toString());
		assertEquals("[5, 4, 2]", z.gnirtSot());
		
		z.delete(z.getNode(1));
		assertEquals("[2, 5]", z.toString());
		assertEquals("[5, 2]", z.gnirtSot());
		
		z.delete(z.getNode(1));
		assertEquals("[2]", z.toString());
		assertEquals("[2]", z.gnirtSot());
		
		z.delete(z.getNode(0));
		assertEquals("[]", z.toString());
		assertEquals("[]", z.gnirtSot());
	}
	
	@Test
	public void testInsert() {
		DLList<Integer> z= new DLList<Integer>();
		z.append(9);
		z.append(3);
		z.append(5);
		z.prepend(1);
		assertEquals(4,z.size());
		
		z.insertAfter(4, z.getNode(2));
		assertEquals(5,z.size());
		assertEquals("[1, 9, 3, 4, 5]", z.toString());
		assertEquals("[5, 4, 3, 9, 1]", z.gnirtSot());
		
		z.insertAfter(8, z.getNode(0));
		assertEquals(6,z.size());
		assertEquals("[1, 8, 9, 3, 4, 5]", z.toString());
		assertEquals("[5, 4, 3, 9, 8, 1]", z.gnirtSot());
		
		z.insertAfter(0, z.getNode(5));
		assertEquals(7,z.size());
		assertEquals("[1, 8, 9, 3, 4, 5, 0]", z.toString());
		assertEquals("[0, 5, 4, 3, 9, 8, 1]", z.gnirtSot());
	}

}
