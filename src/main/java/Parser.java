public class Parser {
    public static boolean isBye(String input) { return input.trim().equals("bye"); }
    public static boolean isList(String input) { return input.trim().equals("list"); }
    public static boolean isMark(String input) { return input.trim().startsWith("mark "); }
    public static boolean isUnmark(String input) { return input.trim().startsWith("unmark "); }

    public static boolean isTodo(String input) { return input.startsWith("todo "); }
    public static boolean isDeadline(String input) { return input.startsWith("deadline "); }
    public static boolean isEvent(String input) { return input.startsWith("event "); }

    public static int parseIndex(String input) throws KrexException {
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


    public static String parseTodoDesc(String input) throws KrexException {
        String desc = input.substring("todo".length()).trim();
        if (desc.isEmpty()) {
            throw new KrexException("OOPS!!! The description of a todo cannot be empty.");
        }
        return desc;
    }


    public static String[] parseDeadline(String input) throws KrexException {
        String body = input.substring("deadline".length()).trim();
        String[] split = body.split(" /by ", 2);
        if (split.length < 2 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            throw new KrexException("OOPS!!! Usage: deadline <desc> /by <time>");
        }
        return new String[] { split[0].trim(), split[1].trim() };
    }


    public static String[] parseEvent(String input) throws KrexException {
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

}
