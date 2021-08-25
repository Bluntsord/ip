import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime dateTime;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor for event class
     * @param name Name for the event
     * @param dateTime date the event is held
     */
    public Event(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * String representation of the event class
     * @return String. See above.
     */
    public String toString() {
        return "[E]" + super.toString() + " " + formatter.format(this.dateTime);
    }
}
