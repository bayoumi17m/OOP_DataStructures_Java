 import java.util.Arrays;

/* NetIds: netid, netid. Time spent: hh hours, mm minutes. */

/** A collection of static String utility functions.
 * All methods assume that String parameters are non-null.
 *
 * If any method is called with arguments that do not satisfy the preconditions,
 * the behavior is undefined (it can do anything). You can,
 * but do not have to, use assert statements to test preconditions. */
public class A2 {
    /* Wherever possible, prefer library functions to writing your own loops.
     *
     * The more complicated your loops become, the more important it is to
     * explain the logic in comments.
     * 
     * See the JavaHyperText entries for if-statement, while-loop, for-loop,
     * switch, break, and continue if you think you need them. */

    /**Return either s1 + s2 or s1 - s2, depending on b. If b is true, return
     * the sum, otherwise return the difference. */
    public static int sumDif(boolean b, int s1, int s2) {
        // This method is already implemented; it is here to give you something
        // to test, and to show you different ways of writing return statements.
        if (b) {
            int s;
            s = s1 + s2;
            return s;

            /* equivalently:
             * int s = s1 + s2;
             * return s;
             *
             * or simply: return s1 + s2;
             * */
        }

        // b is false;
        return s1 - s2;
    }

    /** Return true iff s has an odd number of characters and
     *  the substring before the middle character equals the substring
     *  after it.
     * Examples: For s = "" return false
     * For s = "b" return true
     * For s = "xbx" return true
     * For s = "xxxx" return false
     * For s = "hellohello" return false
     * For s = "hello!hello" return true */
    public static boolean isDoubled(String s) {
        // TODO 1. There is no need for a loop. Do not use a loop.
        // In all methods, use s1.equals(s2) and NOT s1 == s2 to test
        // equality of s1 and s2.
	    	if (s.length()%2 == 1) {
	    		String s1= s.substring(0, (int) s.length()/2);
	    		String s2= s.substring((int) (s.length()/2) + 1);
	    		if (s1.equals(s2)) {
	    			return true;
	    		} else {
	    			return false;
	    		}
	    	} else {
	    		return false;
	    	}
    }

    /** Return a copy of s with each character duplicated.
     * Examples: for s = "", return ""
     * For s = "b", return "bb"
     * For s = "abb", return "aabbbb" */
    public static String dupChars(String s) {
        // TODO 2.
    		char[] charList= s.toCharArray();
		String finalString= "";
		for (int i= 0; i < charList.length; i++) {
			finalString= finalString + charList[i] + charList[i];
		}
        return finalString;
    }

    /** Return s but with each occurrence of a letter in 'a'..'y'
     * replaced by the next letter and 'z' replaced by 'a'
     *
     * Examples: nextChar("") = ""
     * nextChar("abcz") = "bcda"
     * nextChar("1a$b") = "1b$c"
     * nextChar("AB") = "AB"
     * nextChar("love") = "mpwf"   */
    public static String nextLetter(String s) {
        // TODO 3
    		char[] charList= s.toCharArray();
    		String finalString= "";
    		char newChar= (char) 0;
    		for (int i= 0; i < charList.length; i++) {
    			if (((int) charList[i] >= 97 && (int) charList[i] <= 122)) {
    				newChar= (char) (charList[i] + 1);
        			if (charList[i] == 'Z') {
        				newChar= 'A';
        			} else if (charList[i] == 'z') {
        				newChar= 'a';
        			}
    			} else {
    				newChar= charList[i];
    			}
    			finalString = finalString + newChar;
    			//System.out.println("The current char is " + charList[i] + " the next one is: " + newChar);
    		}
        return finalString;
    }
    
    /** Return true iff s contains exactly one occurrence of s2.
     *  Examples: For s = "ab" and s2 = "bb", return false
     *            For s = "abbb" and s2 = "ab", return true.
     *            For s = "abbbabc" and s2 = "ab", return false. 
     */
    public static boolean containsOne(String s, String s1) {
        // TODO 4
        /** Do not use a loop or recursion. Instead, look through the
         * methods of class String and see how you can tell that the first
         * and last occurrences of s2 in s are the same. Be sure you handle
         * correctly the case that s2 does not occur in s. */
    		int firstIndex= s.indexOf(s1);
    		int lastIndex= s.lastIndexOf(s1);
    		if (firstIndex != -1 && lastIndex != -1 && firstIndex == lastIndex) {
    			return true;
    		}
    		return false;
    }

    /** Return true iff s and t are anagrams of each other. An anagram of
     * a string is another string that has the same characters, but possibly
     * in a different order. Note that 'a' and 'A' are considered different
     * characters and that the space is a character.
     *
     * Examples: For s = "noon", t = "noon", return true.
     *           For s = "mary", t = "army", return true.
     *           For s = "tom marvolo riddle", t = "i am lordvoldemort", return true.
     *           For s = "tommarvoloriddle", t = "i am lordvoldemort", return false.
     *           For s = "hello", t = "world", return false.  */
    public static boolean areAnagrams(String s, String t) {
        // TODO 5
        /* Do not use a loop or recursion! This is tricky but can be
         * done in a few lines. Hint: how can a sequence of characters be
         * uniquely ordered? You might need to first convert the string to an
         * array of characters and then use a function in class Arrays
         * (http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html). */
    		char[] charList= s.toCharArray();
    		char[] charList2 = t.toCharArray();
        Arrays.sort(charList);
        Arrays.sort(charList2);
        return Arrays.equals(charList, charList2);
    }
    
    /** Return true iff s is x + x + ... + x  where x = s[0..n-1].
     * That is, if s consists of its initial string of n characters
     * catenated together a number of times.
     * Precondition: n > 0
     * Examples:
     * isX("x", 1) is true
     * isX("x", 2) is false
     * isX("bbbbbb", 1") is true        isX("bbbbbb", 2") is true
     * isX("bbbbbb", 3") is true        isX("bbbbbb", 4") is false
     * isX("xyzxyz", 1") is false       isX("xyxyxyxy", 2") is true */
    public static boolean isX(String s, int n) {
        // TODO 6
    		if (s.length()%n != 0 || s.equals("")) {
    			return false;
    		} else if (s.length() == n) {
    			return true;
    		}
    		String subS= s.substring(0, n);
    		for (int i= 0; i < n; i++) {
    			if (!subS.equals(s.substring(n, 2*n))) {
    				return false;
    			}
    		}
    		return true;
    }

    /** Return the shortest substring x of s such that s = x + x + ⋯ + x.
     * Examples:
     * For s = "" return ""
     * For s = "xxxxxxxxx" return "x"
     * For s = "xyxyxyxy" return "xy"
     * For s = "hellohellohello" return "hello"
     * For s = "hellohelloworld" return "hellohelloworld"
     * For s = "hellohel" return "hellohel" */
    public static String shorten(String s) {
        // TODO 7.
	    	if (s.length() == 0) {
	    		return s;
	    	}
	
	    	int nxt[]= new int[s.length()];
	    	for (int i= 1; i < nxt.length; i++) {
	    		int k= nxt[i - 1];
	    		while (true) {
	    			if (s.charAt(i) == s.charAt(k)) {
	    				nxt[i]= k+1;
	    				break;
	    			} else if (k == 0) {
	    				nxt[i]= 0;
	    				break;
	    			} else {
	    				k= nxt[i - 1];
	    				break;
	    			}
	    		}
	    	}
	    	int	patternlen= s.length() - nxt[s.length()-1];
	    	if ((s.length()%patternlen) != 0) {
	    		return s;
	    	}
	    	return s.substring(0,patternlen);
	    }
}
