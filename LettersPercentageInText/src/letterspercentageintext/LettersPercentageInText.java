package letterspercentageintext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class LettersPercentageInText {

    static int lettersCount;
    static String word;

    public static void main(String[] args) {

        word = getValue();
        lettersCount = word.length();

        displayResult(CountOccurance(word));

    }

    static void displayResult(HashMap<Character, Integer> lettersList) {
        for (Map.Entry<Character, Integer> entry : lettersList.entrySet()) {

            double percentage = ((double) entry.getValue()) / lettersCount * 100;

            System.out.println(entry.getKey() + " appears " + entry.getValue() + " time(s) "
                    + "(" + String.format("%.2f", percentage) + "%)");

        }
    }

    static HashMap<Character, Integer> CountOccurance(String str) {

        HashMap<Character, Integer> lettersList = new HashMap<>();

        for (char letter : str.toCharArray()) {
            if (Character.isLetter(letter)) {
                if (lettersList.containsKey(letter)) {
                    lettersList.replace(letter, lettersList.get(letter) + 1);
                } else {
                    lettersList.put(letter, 1);
                }
            }
        }
        return lettersList;
    }

    private static String getValue() {
        System.out.print("Enter the text: ");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

}
