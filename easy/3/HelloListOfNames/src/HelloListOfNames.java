import java.util.ArrayList;

/**
 *
 * @author iurii
 */
public class HelloListOfNames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> inputArrayList = new ArrayList<>();

        inputArrayList.add("Tomek");
        inputArrayList.add("Ania");
        inputArrayList.add("Mariia");
        
        System.out.println("Original array:");

        displayArray(inputArrayList);

        System.out.println("Modified array:");
        
        displayArray(ProcessArrayList(inputArrayList));

    }

    public static void displayArray(ArrayList<String> in) {
        for (String element : in) {
            System.out.println(element);
        }
    }

    public static ArrayList ProcessArrayList(ArrayList<String> in) {

        ArrayList<String> out = new ArrayList<>();
        String hello = "Hello ";
        for (String element : in) {
             out.add(hello.concat(element));
        }
        return out;
    }

}
