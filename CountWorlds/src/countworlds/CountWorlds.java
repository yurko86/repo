package countworlds;

import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class CountWorlds {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Enter the text: ");
        Scanner s = new Scanner(System.in);
           String name = s.nextLine();
           
           name = name.trim().replaceAll("( )+", " ");
           
           
           int wordLenth =  0;
           
           if(name.length()>0) {
               wordLenth = name.length() - name.replaceAll(" ", "").length()+1; 
           }
 
        System.out.print("The are "+ wordLenth+ " word(s)");
    }
    
}
