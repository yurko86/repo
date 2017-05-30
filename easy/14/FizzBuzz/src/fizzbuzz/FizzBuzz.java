package fizzbuzz;

import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class FizzBuzz {

 
    public static void main(String[] args) {
        runGame(getUpperNumber());
    }

    static void runGame(int upperBound) {
        for (int i = 1; i <= upperBound; i++) {
            String lineOutput = "";
            boolean flag = false;

            if (i % 3 == 0) {
                lineOutput += "Fizz";
                flag = true;
            }

            if (i % 5 == 0) {
                if (flag) {
                    lineOutput += " ";
                }
                lineOutput += "Buzz";
                flag = true;
            }

            if (!flag) {
                lineOutput = Integer.toString(i);
            }

            if (i != upperBound) {
                lineOutput += ", ";
            }
            System.out.print(lineOutput);
        }
    }

    static int getUpperNumber() {
        Scanner s = new Scanner(System.in);
        int upperBound = 0;
        boolean exitWhile = false;
        while (!exitWhile) {
            System.out.print("Please enter upper bound number: ");
            try {
                upperBound = Integer.parseInt(s.next());
                exitWhile = true;
            } catch (Exception e) {
                System.out.println("it's not a number. Try again");
            }
        }
        return upperBound;
    }

}
