package vowelscount;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iurii
 */
public class VowelsCountTest {

    public VowelsCountTest() {
    }

    /**
     * Test of cutOneWorld method, of class VowelsCount.
     */
    @Test
    public void testCutOneWorld() {
        System.out.println("cutOneWorld");

        assertEquals("abcd", VowelsCount.cutOneWorld(" abcd  ef"));
        assertEquals("abcde", VowelsCount.cutOneWorld(" abcde  fef d"));
        assertEquals("abc", VowelsCount.cutOneWorld("     abc  ef"));

    }

    

    
    /**
     * Test of countVowel method, of class VowelsCount.
     */
    @Test
    public void testCountVowel() {
        System.out.println("countVowel");
        assertEquals(3, VowelsCount.countVowel("abcdee"));
        assertEquals(3, VowelsCount.countVowel("AbcdEe"));
        assertEquals(3, VowelsCount.countVowel("AbcdeE"));
        assertEquals(0, VowelsCount.countVowel("bbcd"));
        assertEquals(3, VowelsCount.countVowel("aee"));
    
    }

}
