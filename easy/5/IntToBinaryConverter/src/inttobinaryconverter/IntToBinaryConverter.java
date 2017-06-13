package inttobinaryconverter;

import java.util.Scanner;

public class IntToBinaryConverter {

    public static void main(String[] args) {
        System.out.println(convertIntToBinary(getInt()));
    }

    static String convertIntToBinary(int x) {
        int loopExit = 0;
        String result = "";

        while (loopExit == 0) {
            int remainder = x % 2;

            if (remainder != 0) {
                x = x - remainder;
            }
            x = x / 2;
            // concatenate string in reverse order
            result = remainder + result;
            if (x < 1) {
                loopExit = 1;
            }
        }
        return result;
    }

    private static int getInt() {
        Scanner s = new Scanner(System.in);

        int inputNumber = 0;

        boolean exitWhile = false;
        while (!exitWhile) {
            System.out.print("Please enter number: ");

            try {
                inputNumber = Integer.parseInt(s.nextLine());
                exitWhile = true;
            } catch (NumberFormatException e) {
                System.out.println("its not a number! try again!");
            }
        }
        return inputNumber;
    }

}
