package wordsnake;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author iurii
 */
public class WordSnake {

    static Scanner s = new Scanner(System.in);
    static int numberOfPlayers;
    static List<Integer> playersList = new ArrayList<>();
    static List<String> valueList = new ArrayList<>();

    public static void main(String[] args) {

        addValueToList("zegarek");

        setGameParam();

        game();

    }

    private static void setGameParam() {
        boolean exitLoop = false;

        while (!exitLoop) {
            try {
                System.out.print("Please specify number of players: ");
                numberOfPlayers = Integer.parseInt(s.nextLine());
                exitLoop = true;
            } catch (Exception e) {
                System.err.println("Wrong value. Try again");
            }
        }
        createListOfPlayers();
    }

    private static void createListOfPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            playersList.add(i + 1);
        }
    }

    private static void game() {
        String userInput;

        while (playersList.size() > 1) {
            for (int i = 0; i < playersList.size(); i++) {
                System.out.println("---------------------------------------------");
                System.out.println("Player " + playersList.get(i) + " please enter text ");
                userInput = getValue();

                //parse 
                //compare
                if (userInput != ""
                        && parseStringToList(userInput).containsAll(valueList)
                        && (userInput.length() - userInput.replaceAll(",", "").length()) == valueList.size()) {

                    String newWord = userInput.substring(userInput.lastIndexOf(",") + 1, userInput.length());

                    System.out.println("adding new word: " + newWord);
                    // if matched add new word to list
                    addValueToList(newWord);

                } else {
                    //if not match remove player
                    System.out.println("Player " + playersList.get(i) + " gave wrong answer and finish game");
                    playersList.remove(playersList.get(i));
                }

            }
        }

        System.out.println("Win player " + playersList.get(0));
    }

    private static String getValue() {

        System.out.println("Please type previous items and add new one : ");

        System.out.println(parseListToString());

        System.out.print("Poszedł Marek na jarmarek i kupił sobie ");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    private static boolean addValueToList(String str) {
        return valueList.add(str);
    }

    private static ArrayList<String> parseStringToList(String str) {

        ArrayList<String> userInputArray = new ArrayList<>();

        if (str.length() > 0) {

            for (String s : str.split(",")) {
                userInputArray.add(s);
            }

        }
        return userInputArray;
    }

    private static String parseListToString() {
        String stringValue = "";
        for (String value : valueList) {
            stringValue += value + ",";
        }
        return stringValue;
    }

}
