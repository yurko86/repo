package sayhellouser;

import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class SayHelloUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String name;
        if (args.length == 0) {
            System.out.print("Please enter your name: ");
            Scanner s = new Scanner(System.in);
            name = s.next();
        } else {
            name = args[0];
        }
        System.out.println("Hello " + name);
    }

}
