package stringreverse;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iurii
 */
public class StringReverseTest {
    
    public StringReverseTest() {
    }

    /**
     * Test of reverseString method, of class StringReverse.
     */
    @Test
    public void testReverseString() {
        System.out.println("reverseString");
        assertEquals("cba", StringReverse.reverseString("abc"));
        
        assertEquals("Anna", StringReverse.reverseString("annA"));
    }
    
}
