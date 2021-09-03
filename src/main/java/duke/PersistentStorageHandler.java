package duke;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistent storage refers to the database used to store all the information regarding tasks. Allows for handling of
 * .txt files and manages all of them
 */
public class PersistentStorageHandler {

    private final File file;
    private final String fileName;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int numberOfLines = 0;
    private final int maxNumberOfLines = 10;
    private boolean hasStorageTextFile = false;
    private final ArrayList<String> allLines = new ArrayList<>();

    /**
     * Constructor for persistent storage handler
     *
     * @param fileName name that the storage handler will read and write to.
     */
    public PersistentStorageHandler(String fileName) {
        //Name the file
        this.fileName = fileName;
        this.file = new File(fileName);

        // Create the writer and buffered writer
        try {
            FileWriter writer = new FileWriter(fileName, true);
            this.bufferedWriter = new BufferedWriter(writer);
            this.bufferedReader = new BufferedReader(new FileReader(fileName));
            this.hasStorageTextFile = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check if file exist. If not create a new file
        if (!hasStorageTextFile) {
            System.out.println("Oooooooooooooo. Fresh meat. Welcome my dinn- I mean friend");
            //Create a storage text file with the same name
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ahhhhhh. It seems you have an old scroll. Let me read the contents......");
            //Add all lines of the file to all_lines variable
            try {
                List<String> list = Files.readAllLines(new File(fileName).toPath(), Charset.defaultCharset());
                allLines.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Write to the persistent storage
     *
     * @param text string to be written
     */
    public void write(String text) {
        //Count the number of lines in the string
        String[] tempStringArray = text.split("\r\n|\r|\n");
        int linesInAddedText = tempStringArray.length;

        // Writes the content to the file
        try {
            this.bufferedWriter.write(text + "\n");
            this.bufferedWriter.flush();
            //this.bufferedWriter.close();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        //Add the number of lines
        numberOfLines += linesInAddedText;

        //Accumulate all lines
        allLines.add(text);
    }

    /**
     * Print all the lines in the storage
     */
    public void printAllLines() {
        for (String line : allLines) {
            System.out.println(line);
        }
    }

    /**
     * Clears the entire history of the log
     */
    //I see what youre doing yes. Why are you clearing history?
    public void clearHistory() {
        //Code to clear history
        this.allLines.clear();
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Closes the buffer writer
     */
    public void stopWriting() {
        try {
            this.bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return all lines
     * @return
     */
    public ArrayList<String> getAllLines() {
        return this.allLines;
    }
}
