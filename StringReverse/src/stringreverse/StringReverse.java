package stringreverse;

import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class StringReverse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println(reverseString(readWord()));

    }

    static String reverseString(String str) {
        String reversedString = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedString += str.charAt(i);
        }
        return reversedString;
    }

    static String readWord() {
        System.out.print("Please enter word: ");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
