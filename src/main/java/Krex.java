import java.util.Scanner;

public class Krex {
    public static void main(String[] args) {
        Ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            String input = sc.nextLine().trim();

            try {
                if (Parser.isBye(input)) {
                    Ui.showBye();
                    break;
                }

                if (Parser.isList(input)) {
                    Ui.showMessage(taskList.formatList());
                    continue;
                }

                if (Parser.isMark(input)) {
                    int idx = Parser.parseIndex(input);
                    Task t = taskList.get(idx);
                    t.markDone();
                    Ui.showMessage("Nice! I've marked this task as done:\n  " + t);
                    continue;
                }

                if (Parser.isUnmark(input)) {
                    int idx = Parser.parseIndex(input);
                    Task t = taskList.get(idx);
                    t.unmarkDone();
                    Ui.showMessage("OK, I've marked this task as not done yet:\n  " + t);
                    continue;
                }

                if (Parser.isTodo(input)) {
                    String desc = Parser.parseTodoDesc(input);
                    Task t = new Todo(desc);
                    taskList.add(t);
                    Ui.showMessage("Got it. I've added this task:\n  " + t + "\nNow you have " + taskList.size() + " tasks in the list.");
                    continue;
                }

                if (Parser.isDeadline(input)) {
                    String[] parts = Parser.parseDeadline(input);
                    Task t = new Deadline(parts[0], parts[1]);
                    taskList.add(t);
                    Ui.showMessage("Got it. I've added this task:\n  " + t + "\nNow you have " + taskList.size() + " tasks in the list.");
                    continue;
                }

                if (Parser.isEvent(input)) {
                    String[] parts = Parser.parseEvent(input);
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    taskList.add(t);
                    Ui.showMessage("Got it. I've added this task:\n  " + t + "\nNow you have " + taskList.size() + " tasks in the list.");
                    continue;
                }

                // Unknown command
                throw new KrexException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            } catch (KrexException e) {
                Ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                Ui.showError("OOPS!!! That task number is out of range.");
            }
        }

        sc.close();
    }
}

