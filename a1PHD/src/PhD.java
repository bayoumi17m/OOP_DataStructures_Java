/** NetId: mb2363 Magd Bayoumi. Time spent: unknown.
 * An instance maintains info about the PhD of a person. */
public class PhD{
	// The PhD Class has 6 fields
	private String name;
	private int month;
	private int year;
	private char gender;
	private PhD Adv1;
	private PhD Adv2;
	private int numadv;
	
	
	// Group A: The first constructor and the getter methods of class PhD.
	/** Constructor: an instance for a person with name n, PhD month m, PhD year y, and gender g. Its 
	 * advisors are unknown, and it has no advisees.
	 * Precondition: n has at least 1 char. m is in 1..12. g is 'g' for girl or 'b' for boy.
	 */
	public PhD(String n, int m, int y, char g) {
		// Assert Statements
		assert n != null && n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert g == 'g' || g == 'b';
		// Function Body
		name = n;
		month = m;
		year = y;
		gender = g;
		Adv1 = null;
		Adv2 = null;
		numadv = 0;
	}
	
	
	/** Return the name of this person. */
	public String name() {
		return name;
	}
	
	
	/** Return the month this person got their PhD. */
	public int month() {
		return month;
	}
	
	
	/** Return the year this person got their PhD. */
	public int year() {
		return year;
	}
	
	
	/** Return the value of the sentence "This person is male." */
	public boolean isMale( ) {
		return 'b' == gender;
	}
	
	
	/** Return the first advisor of this PhD (null if unknown).*/
	public PhD advisor1( ) {
		return Adv1;
	}
	
	
	/** Return the second advisor of this PhD (null if unknown or non-existent). */
	public PhD advisor2() {
		return Adv2;
	}
	
	
	/** Return the number of PhD advisees of this person. */
	public int numAdvisees() {
		return numadv;
	}
	
	
	// Group B: the setter/mutator methods. Note: methods addAdvisor1 and addAdvisor2 must change fields 
	// of both this PhD and its advisor in order to maintain the class invariant.
	/** Add p as the first advisor of this person.
	 * Precondition: the first advisor is unknown and p is not null.*/
	public void addAdvisor1(PhD p) {
		assert p != null;
		assert this.advisor1() == null;
		this.Adv1 = p;
		p.incrementAdvisees();
	}
	
	
	/** Add p as the second advisor of this person.
	 * Precondition: The first advisor (of this person) is known, the second advisor is unknown, 
	 * p is not null, and p is different from the first advisor.
	 */
	public void addAdvisor2(PhD p) {
		assert p != null;
		assert this.advisor1() != null;
		assert this.advisor2() == null;
		p.incrementAdvisees();
		this.Adv2 = p;
	}
	
	/** Helper function to increment the number of advisees
	 */
	public void incrementAdvisees() {
		numadv++;
	}
	
	
	// Group C: Two more constructors. The test procedure for group C has to create a PhD using the 
	// constructor given below. This will require first creating two PhD objects using the first 
	// constructor and then checking that the new constructor sets all 7 fields properly —and also 
	// the number of advisees of adv1 and adv2.
	/** Constructor: a PhD with name n, PhD month m, PhD year y, gender g, first advisor adv, and 
	 * no second advisor.
	 * Precondition: n has at least 1 char, m is in 1..12, g is 'g' for girl or 'b' for boy, and adv 
	 * is not null.
	 */
	public PhD(String n, int m, int y, char g, PhD adv) {
		// Assert Statements
		assert n != null && n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert g == 'g' || g == 'b';
		assert adv != null;
		// Function Body
		name= n;
		month= m;
		year= y;
		gender= g;
		Adv1= adv;
		Adv1.incrementAdvisees();
		Adv2= null;
		numadv= 0;
	}
	
	
	/** Constructor: a PhD with name n, PhD month m, PhD year y, gender g, first advisor adv1, and second 
	 * advisor adv2.
	 * Precondition: n has at least 1 char, m is in 1..12, g is 'g' for girl or 'b' for boy, adv1 and 
	 * adv2 are not null, and adv1 and adv2 are different.
	 */
	public PhD(String n, int m, int y, char g, PhD adv1, PhD adv2) {
		// Assert Statements
		assert n != null && n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert g == 'g' || g == 'b';
		assert adv1 != null;
		assert adv2 != null;
		// Function Body
		name= n;
		month= m;
		year= y;
		gender= g;
		Adv1= adv1;
		Adv1.incrementAdvisees();
		Adv2= adv2;
		Adv2.incrementAdvisees();
		numadv= 0;
	}
	
	
	// Group D: Write two comparison methods. The second tests whether two people are 
	// “intellectual siblings”, that is: they are not the same object and they have a non-null advisor 
	// in common. Write these using only boolean expres- sions (with !, &&, and || and 
	// relations <, <=, ==, etc.). Do not use if-statements, conditional expressions, switches, 
	// addition, multiplication, etc. Each is best written as a single return statement.
	
	/** Return value of “p is not null and p got the PhD before this person.” */
	public boolean gotFirst(PhD p) {
		return p != null && ((p.month() < this.month() && p.year() == this.year()) || 
				(p.year() < this.year()));
	}
	
	
	/** Return value of “this person and p are intellectual siblings.”
	 * Precondition: p is not null. */
	public boolean arePhDSiblings(PhD p) {
		assert p != null;
		return (p != this && ( ( (p.advisor1() == this.advisor1()) && p.advisor1() != null ) || 
				( (p.advisor1() == this.advisor2()) && p.advisor1() != null ) ||
				( (p.advisor2() == this.advisor1()) && p.advisor2() != null ) ||
				( (p.advisor2() == this.advisor2()) && p.advisor2() != null ) ) );
	}

	
	
	
	
}