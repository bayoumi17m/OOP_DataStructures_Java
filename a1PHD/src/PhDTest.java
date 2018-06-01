import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PhDTest {

	@Test
	/** Tests the Original Contructor and all 7 of the getter methods */
	public void testContructor1() {
		PhD p= new PhD("Danny Jones",1,1973,'b');
		assertEquals("Danny Jones",p.name());
		assertEquals(1,p.month());
		assertEquals(1973,p.year());
		assertEquals(true,p.isMale());
		assertEquals(null,p.advisor1());
		assertEquals(null,p.advisor2());
		assertEquals(0,p.numAdvisees());
		
		p= new PhD("Kaitlyn Kiley",8,2017,'g');
		assertEquals("Kaitlyn Kiley",p.name());
		assertEquals(8,p.month());
		assertEquals(2017,p.year());
		assertEquals(false,p.isMale());
		assertEquals(null,p.advisor1());
		assertEquals(null,p.advisor2());
		assertEquals(0,p.numAdvisees());
	}
	
	
	@Test
	/** Tests the Original Contructor and all 7 of the getter methods */
	public void testMutator() {
		PhD p= new PhD("Danny Jones",1,1973,'b');
		PhD q= new PhD("Kaitlyn Kiley",8,2017,'g');
		PhD w= new PhD("Elizabeth Banks",4,1981,'g');
		q.addAdvisor1(p);
		assertEquals(p,q.advisor1());
		assertEquals(1,p.numAdvisees());
		q.addAdvisor2(w);
		assertEquals(w,q.advisor2());
		assertEquals(1,w.numAdvisees());
		p.addAdvisor1(w);
		assertEquals(w,p.advisor1());
		assertEquals(2,w.numAdvisees());
		
	}
	
	
	@Test
	/** Tests the Two new Contructors and all 7 of the getter methods */
	public void testConstructor2() {
		PhD w= new PhD("Elizabeth Banks",4,1981,'g');
		PhD p= new PhD("Danny Jones",1,1973,'b',w);
		PhD q= new PhD("Kaitlyn Kiley",8,2017,'g',p,w);
		assertEquals("Kaitlyn Kiley",q.name());
		assertEquals(8,q.month());
		assertEquals(2017,q.year());
		assertEquals(false,q.isMale());
		assertEquals(0,q.numAdvisees());
		assertEquals(p,q.advisor1());
		assertEquals(1,p.numAdvisees());
		assertEquals(w,q.advisor2());
		assertEquals(w,p.advisor1());
		assertEquals(2,w.numAdvisees());
		assertEquals("Danny Jones",p.name());
		assertEquals(1,p.month());
		assertEquals(1973,p.year());
		assertEquals(true,p.isMale());
		assertEquals(null,p.advisor2());
		
	}
	
	@Test
	/** Tests the new Comparison Methods */
	public void testComparison() {
		PhD w= new PhD("Elizabeth Banks",10,1981,'g');
		PhD p= new PhD("Danny Jones",7,1973,'b',w);
		PhD q= new PhD("Kaitlyn Kiley",8,2017,'g',p,w);
		PhD e= new PhD("Jimmy John", 7,2017,'b');
		assertEquals(false,w.gotFirst(q));
		assertEquals(true,q.gotFirst(e));
		assertEquals(true,e.gotFirst(p));
		assertEquals(true,w.gotFirst(p));
		
	}
	
	@Test
	/** */
	public void testAsserts() {
		
	}

}
