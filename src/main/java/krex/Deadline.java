package krex;

/**
 * Represents a task that must be completed before a specific date.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /** Used internally (e.g. Storage) */
    public LocalDate getBy() {
        return by;
    }

    /** Used by Storage to save in ISO format (yyyy-mm-dd) */
    public String getByIso() {
        return by.toString();
    }

    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}