public class Parser {
    public static boolean isBye(String input) { return input.trim().equals("bye"); }
    public static boolean isList(String input) { return input.trim().equals("list"); }
    public static boolean isMark(String input) { return input.trim().startsWith("mark "); }
    public static boolean isUnmark(String input) { return input.trim().startsWith("unmark "); }

    public static boolean isTodo(String input) { return input.startsWith("todo "); }
    public static boolean isDeadline(String input) { return input.startsWith("deadline "); }
    public static boolean isEvent(String input) { return input.startsWith("event "); }

    public static int parseIndex(String input) {
        String[] parts = input.trim().split("\\s+");
        return Integer.parseInt(parts[1]);
    }

    public static String parseTodoDesc(String input) {
        return input.substring("todo".length()).trim();
    }

    public static String[] parseDeadline(String input) {
        // "deadline desc /by something"
        String body = input.substring("deadline".length()).trim();
        String[] split = body.split(" /by ", 2);
        return split; // [0]=desc, [1]=by
    }

    public static String[] parseEvent(String input) {
        // "event desc /from A /to B"
        String body = input.substring("event".length()).trim();
        String[] first = body.split(" /from ", 2);
        String desc = first[0];
        String[] second = first[1].split(" /to ", 2);
        String from = second[0];
        String to = second[1];
        return new String[] { desc, from, to };
    }
}
