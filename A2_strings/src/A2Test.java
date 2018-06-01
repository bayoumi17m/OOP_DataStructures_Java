import static org.junit.Assert.*;
import org.junit.Test;

public class A2Test {

    @Test
    public void testIsDoubled() {
        assertEquals(false,  A2.isDoubled(""));
        assertEquals(true,  A2.isDoubled("b"));
        assertEquals(false,  A2.isDoubled("xx"));
        assertEquals(true,  A2.isDoubled("xbx"));
        assertEquals(false,  A2.isDoubled("hellohello"));
        assertEquals(true, A2.isDoubled("hello!hello"));
        assertEquals(false, A2.isDoubled("helloxhello11"));
        assertEquals(true, A2.isDoubled("hello!hello$hello!hello"));
    }

    @Test
    public void testDupChars() {
        assertEquals("",    A2.dupChars(""));
        assertEquals("bb",   A2.dupChars("b"));
        assertEquals("bbcc",   A2.dupChars("bc"));
        assertEquals("aaaaaaaaxxcc",  A2.dupChars("aaaaxc"));
        assertEquals("aabbccddeeffgggg", A2.dupChars("abcdefgg"));
    }

    @Test
    public void testNextLetter() {
        assertEquals("", A2.nextLetter(""));
        assertEquals("h", A2.nextLetter("g"));
        assertEquals("bcdefghijklmnopqrstuvwxyza", A2.nextLetter("abcdefghijklmnopqrstuvwxyz"));
        assertEquals("1c$d", A2.nextLetter("1b$c"));
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", A2.nextLetter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertEquals("!@#$%^&*()_+{}|:'<>?", A2.nextLetter("!@#$%^&*()_+{}|:'<>?"));
        assertEquals("0123456789[];',./", A2.nextLetter("0123456789[];',./"));
    }
    
    @Test
    public void testContainsOne() {
        assertEquals(true,                A2.containsOne("", ""));
        assertEquals(false,               A2.containsOne("b", ""));
        assertEquals(false,               A2.containsOne("", "b"));
        assertEquals(true,                A2.containsOne("b", "b"));
        assertEquals(false,               A2.containsOne("ab", "bb"));
        assertEquals(true,                A2.containsOne("abb", "bb"));
        assertEquals(false,               A2.containsOne("abbb", "bb"));
        assertEquals(true,                A2.containsOne("abbcb", "bbc"));
        assertEquals(false,               A2.containsOne("abbcbbc", "bbc"));
    }
    
    @Test
    public void testAreAnagrams() {
        assertEquals(true, A2.areAnagrams("", ""));
        assertEquals(false, A2.areAnagrams("n", ""));
        assertEquals(false, A2.areAnagrams("", "m"));
        assertEquals(true, A2.areAnagrams("m", "m"));
        assertEquals(true, A2.areAnagrams("noon", "nono"));
        assertEquals(true, A2.areAnagrams("mary", "army"));
        assertEquals(false, A2.areAnagrams("hello", "world"));
        assertEquals(false, A2.areAnagrams("hello", "hellos"));
        assertEquals(true, A2.areAnagrams("tom marvolo riddle", "i am lordvoldemort"));
        assertEquals(false, A2.areAnagrams("tommarvoloriddle", "i am lordvoldemort"));
    }

    @Test
    public void testIsX() {
        assertEquals(false,                A2.isX("", 1));
        assertEquals(true,               A2.isX("x", 1));
        assertEquals(false,               A2.isX("x", 2));
        assertEquals(true,               A2.isX("bbbbbb", 1));
        assertEquals(true,               A2.isX("bbbbbb", 2));
        assertEquals(true,               A2.isX("bbbbbb", 3));
        assertEquals(false,               A2.isX("bbbbbb", 4));
        assertEquals(false,               A2.isX("bbbbbb", 5));
        assertEquals(true,               A2.isX("bbbbbb", 6));
        assertEquals(false,               A2.isX("bbbbbb", 7));
        assertEquals(false,               A2.isX("bbbbbb", 12));
        assertEquals(false,               A2.isX("abcdabcdabcd", 1));
        assertEquals(false,               A2.isX("abcdabcdabcd", 2));
        assertEquals(false,               A2.isX("abcdabcdabcd", 3));
        assertEquals(true,                A2.isX("abcdabcdabcd", 4));
        assertEquals(false,               A2.isX("abcdabcdabcd", 2));
        assertEquals(false,               A2.isX("abcdabcdabcd", 3));
        assertEquals(true,               A2.isX("abcdabcdabcd", 12));
    }
    
   @Test
    public void testShorten() {
        assertEquals("",                A2.shorten(""));
        assertEquals("x",               A2.shorten("x"));
        assertEquals("x",               A2.shorten("xxxxxx"));
        assertEquals("xy",              A2.shorten("xyxyxyxy"));
        assertEquals("xyxz",            A2.shorten("xyxzxyxz"));
        assertEquals("hello",           A2.shorten("hellohellohello"));
        assertEquals("hellohelloworld", A2.shorten("hellohelloworld"));
        assertEquals("hellohel",        A2.shorten("hellohel"));
    }
}
