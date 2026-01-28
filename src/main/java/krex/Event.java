package krex;

/**
 * Represents an event task that occurs at a specific time period.
 */

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getDescription() { return description; }
    
    public String getFromRaw() { return from; }
    
    public String getToRaw() { return to; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
