public class Parser {
    public static boolean isBye(String input) {
        return input.trim().equals("bye");
    }

    public static boolean isList(String input) {
        return input.trim().equals("list");
    }

    public static boolean isMark(String input) {
        return input.trim().startsWith("mark ");
    }

    public static boolean isUnmark(String input) {
        return input.trim().startsWith("unmark ");
    }

    public static int parseIndex(String input) {
        String[] parts = input.trim().split("\\s+");
        return Integer.parseInt(parts[1]);
    }
}
