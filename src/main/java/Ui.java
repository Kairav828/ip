public class Ui {
    private static final String LINE = "____________________________________________________________";

    public static void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Krex");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void showBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void showMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

}
