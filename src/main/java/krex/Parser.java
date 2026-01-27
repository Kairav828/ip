import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws KrexException {
        String trimmed = input.trim();

        if (trimmed.equals("bye")) {
            return Command.bye();
        }
        if (trimmed.equals("list")) {
            return Command.list();
        }

        if (trimmed.startsWith("mark ")) {
            int idx = parseIndex(trimmed);
            return Command.index(CommandType.MARK, idx);
        }

        if (trimmed.startsWith("unmark ")) {
            int idx = parseIndex(trimmed);
            return Command.index(CommandType.UNMARK, idx);
        }

        if (trimmed.startsWith("delete ")) {
            int idx = parseIndex(trimmed);
            return Command.index(CommandType.DELETE, idx);
        }

        if (trimmed.startsWith("todo")) {
            String desc = parseTodoDesc(trimmed);
            return Command.add(CommandType.TODO, new Todo(desc));
        }

        if (trimmed.startsWith("deadline")) {
            String[] parts = parseDeadline(trimmed); // [desc, by]
            LocalDate by = parseDate(parts[1]);
            return Command.add(CommandType.DEADLINE, new Deadline(parts[0], by));
        }

        if (trimmed.startsWith("event")) {
            String[] parts = parseEvent(trimmed); // [desc, from, to]
            return Command.add(CommandType.EVENT, new Event(parts[0], parts[1], parts[2]));
        }

        throw new KrexException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static int parseIndex(String input) throws KrexException {
        String[] parts = input.trim().split("\\s+");
        if (parts.length < 2) {
            throw new KrexException("OOPS!!! Please provide an index.");
        }
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new KrexException("OOPS!!! Index must be a number.");
        }
    }

    private static String parseTodoDesc(String input) throws KrexException {
        String desc = input.substring("todo".length()).trim();
        if (desc.isEmpty()) {
            throw new KrexException("OOPS!!! The description of a todo cannot be empty.");
        }
        return desc;
    }

    private static String[] parseDeadline(String input) throws KrexException {
        String body = input.substring("deadline".length()).trim();
        String[] split = body.split(" /by ", 2);
        if (split.length < 2 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            throw new KrexException("OOPS!!! Usage: deadline <desc> /by <yyyy-mm-dd>");
        }
        return new String[] { split[0].trim(), split[1].trim() };
    }

    private static String[] parseEvent(String input) throws KrexException {
        String body = input.substring("event".length()).trim();
        String[] first = body.split(" /from ", 2);
        if (first.length < 2 || first[0].trim().isEmpty()) {
            throw new KrexException("OOPS!!! Usage: event <desc> /from <start> /to <end>");
        }
        String desc = first[0].trim();
        String[] second = first[1].split(" /to ", 2);
        if (second.length < 2 || second[0].trim().isEmpty() || second[1].trim().isEmpty()) {
            throw new KrexException("OOPS!!! Usage: event <desc> /from <start> /to <end>");
        }
        return new String[] { desc, second[0].trim(), second[1].trim() };
    }

    public static LocalDate parseDate(String s) throws KrexException {
        try {
            return LocalDate.parse(s.trim()); // expects yyyy-mm-dd
        } catch (DateTimeParseException e) {
            throw new KrexException("OOPS!!! Date must be yyyy-mm-dd (e.g., 2026-01-27).");
        }
    }
}