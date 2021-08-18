import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Persistence {

    /**
     * In memory storage for log, for history, refer to the history variable
     */
    private static ArrayList<Task> log = new ArrayList<>();

    /**
     * Adds the command to the log.
     *
     * @param task Command to be added to the log
     */
    public static void addToLog(Task task) {
        log.add(task);
    }

    /**
     * Returns the task at the specific log postion
     * @param position
     * @return Task
     */
    public static Task getTask(int position) {
        return log.get(position);
    }

    /**
     * Prints log of tasks. No parameters needed. To change task representation, see Task.
     */
    public static void printLog() {
        for (int i = 0; i < log.size(); i++) {
            Task currentTask = log.get(i);
            int taskNumber = i + 1;
            System.out.println(taskNumber + ". " + currentTask.toString());
        }
    }

    /**
     * Prints a log of tasks that are filtered. True for completed and False for not completed
     */
    public static void print_filtered_log(boolean cond) {
        int taskNumber = 1;
        for (Task temp : log) {
            if (temp.getCompletedStatus() == cond) {
                System.out.println(taskNumber + ". " + temp.toString());
                taskNumber ++;
            }
        }
    }
}