import java.util.ArrayList;
import java.util.Locale;

public class Logic {


    /**
     * The process is the main function to process the parsed string from presentation.
     * @param command refers to the parsed string. See presentation for parsing
     * @throws InvalidCommandException is throwed when there is an invalid command in the form of a string
     */
    public static void process(String command) throws InvalidCommandException{
        System.out.println("Process logic called");

        //Logic to check each individual commands, checks for special commands first, then checks for other input
        if (command.equals("")) {
            throw new EmptyCommandException();
        } else {
            //Use Parser to package command into a packaged command
            Command packagedCommand = Parser.parse(command);
            ArrayList<String> listOfCommandInputs = packagedCommand.getListOfCommandInputs();
            String loggedCommand = packagedCommand.getLog();

            System.out.println("Passed on to persistence stage");
            System.out.println(listOfCommandInputs);
            if (listOfCommandInputs.size() == 1 && listOfCommandInputs.get(0).equals("list")) {
                Persistence.printLog();
            } else if (listOfCommandInputs.contains("done")) {
                System.out.println("Done called");
                int pos = Integer.parseInt(listOfCommandInputs.get(1));
                if (pos > Task.getNumberOfTask()) {
                    throw new InvalidCommandException();
                }
                Task currentTask = Persistence.getTask(pos - 1 );
                currentTask.completeTask();
                System.out.println("Ohhhh myyyy. I have been waiting for this quest to complete for ages.");
            } else {
                switch (packagedCommand.getTaskType()) {
                    case TODO:
                        Persistence.addToLog(new Todo(loggedCommand));
                        break;
                    case DEADLINE:
                        Persistence.addToLog(new Deadline(loggedCommand));
                        break;
                    case EVENT:
                        Persistence.addToLog(new Event(loggedCommand));
                        break;
                    case NOTAPPLICABLE:
                        System.out.println(loggedCommand);
                }
            }
        }
    }
}
