import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanner {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Used to scan and parse the commands entered by the user
     * @return the command as one string
     */
    public static String scan() {
        String command = new String();
        try {
            command = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return command;
    }
}
