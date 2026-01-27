package krex;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Krex");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void close() {
        sc.close();
    }
}