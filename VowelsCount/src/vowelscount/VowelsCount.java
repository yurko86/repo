package vowelscount;

import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class VowelsCount {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("There are(is) " + countVowel(cutOneWorld(readWorld())) + " vowel(s) in first word");

    }

    static String readWorld() {
        System.out.print("Please enter word: ");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    static String cutOneWorld(String str) {
        int firstWorldEnd = str.trim().indexOf(" ");

        if (firstWorldEnd == -1) {
            firstWorldEnd = str.trim().length();
        }

        return str.trim().substring(0, firstWorldEnd);
    }

    static int countVowel(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if ("aeiouyAEIOUY".indexOf(str.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

}
