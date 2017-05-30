package countwords;

import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class CountWords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String name = getValue();

        name = removeDoubleBackspace(name);

        System.out.print("The are " + calculate(name) + " word(s)");
    }

    protected static int calculate(String str) {
        int wordLenth = 0;

        if (str.length() > 0) {
            wordLenth = str.length() - str.replaceAll(" ", "").length() + 1;
        }

        return wordLenth;
    }

    protected static String removeDoubleBackspace(String name) {
        return name.trim().replaceAll("( )+", " ");
    }

    private static String getValue() {
        System.out.print("Enter the text: ");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

}
