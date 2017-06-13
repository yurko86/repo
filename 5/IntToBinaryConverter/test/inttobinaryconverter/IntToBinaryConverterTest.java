package inttobinaryconverter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class IntToBinaryConverterTest {

    public IntToBinaryConverterTest() {
    }

    @Test
    public void testConvertIntToBinary() {
        System.out.println("Test ConvertIntToBinary");
        assertEquals(IntToBinaryConverter.convertIntToBinary(5), "101");
        assertNotEquals(IntToBinaryConverter.convertIntToBinary(5), "111");
        assertEquals(IntToBinaryConverter.convertIntToBinary(7), "111");
        assertEquals(IntToBinaryConverter.convertIntToBinary(10), "1010");
        assertEquals(IntToBinaryConverter.convertIntToBinary(25), "11001");
    }
}
