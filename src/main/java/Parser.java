public class Parser {
    public static boolean isBye(String input) {
        return input.trim().equals("bye");
    }

    public static boolean isList(String input) {
        return input.trim().equals("list");
    }
}
