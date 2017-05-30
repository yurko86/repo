package palindrome;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class Palindrome {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (evaluateString(setLoverCase(getValue()))) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

    }
    
    static String setLoverCase( String str) {
        return str.toLowerCase();
    }
    
    
    private static String getValue() {
        System.out.print("Enter the text: ");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    
    static boolean evaluateString(String str) {

        boolean result = true;

        char[] charArray = str.toCharArray();

        int begin = 0;
        int end = charArray.length - 1;

        while (begin <= end) {
            if (charArray[begin] == charArray[end]) {
                begin++;
                end--;
            } else {
                result = false;
                break;
            }
        }

        return result;
    }

}
