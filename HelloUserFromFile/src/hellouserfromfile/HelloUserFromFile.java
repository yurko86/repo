package hellouserfromfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 *
 * @author iurii
 */
public class HelloUserFromFile {

    public static void main(String[] args) {

        String[] parts;
        parts = readFile().split(Pattern.quote(","));

        display(parts);

    }

    public static void display(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            System.out.println("Hello " + parts[i].trim());
        }
    }

    public static String readFile() {

        String fileContent = "";

        ClassLoader loader = HelloUserFromFile.class.getClassLoader();

        try (InputStream in = loader.getResourceAsStream("files/names.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent += line;
            }

        } catch (IOException x) {
            System.err.println(x);
        }

        return fileContent;
    }

}
