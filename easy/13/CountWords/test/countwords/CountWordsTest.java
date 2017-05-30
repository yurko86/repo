package countwords;

import countwords.CountWords;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iurii
 */
public class CountWordsTest {

    public CountWordsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testCalculate() {
        System.out.println("Test calculate");
        assertEquals(3, CountWords.calculate("asd ads asdd"));
    }

    @Test
    public void testRemoveDoubleBackspace() {
        System.out.println("Test removeDoubleBackspace");
        assertEquals("d asd ads asdd", CountWords.removeDoubleBackspace("  d  asd    ads    asdd    "));
    }

    
}
