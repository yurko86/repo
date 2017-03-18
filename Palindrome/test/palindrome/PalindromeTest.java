/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iurii
 */
public class PalindromeTest {
    
    public PalindromeTest() {
    }

   
    /**
     * Test of setLoverCase method, of class Palindrome.
     */
    @Test
    public void testSetLoverCase() {
        System.out.println("setLoverCase");
        String str = "AnNa";
        String expResult = "anna";
        String result = Palindrome.setLoverCase(str);
        assertEquals(expResult, result);
                
    }

    
    /**
     * Test of evaluateString method, of class Palindrome.
     */
    @Test
    public void testEvaluateString() {
        System.out.println("evaluateString");
        assertTrue(Palindrome.evaluateString("anna"));
        assertTrue(Palindrome.evaluateString("aha"));
        assertTrue(Palindrome.evaluateString("A"));
        assertFalse(Palindrome.evaluateString("hanna"));
        
    }
    
}
