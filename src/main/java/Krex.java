import java.util.ArrayList;
import java.util.Scanner;

public class Krex {
    private static final String SAVE_PATH = "data/krex.txt";

    public static void main(String[] args) {
        Ui.showWelcome();

        Storage storage = new Storage(SAVE_PATH);
        TaskList taskList;

        try {
            ArrayList<Task> loaded = storage.load();
            taskList = new TaskList(loaded);
        } catch (KrexException e) {
            Ui.showError(e.getMessage());
            taskList = new TaskList();
        }

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine().trim();

                try {
                    Command cmd = Parser.parse(input);

                    switch (cmd.type) {
                    case BYE:
                        Ui.showBye();
                        storage.save(taskList.getTasks());
                        return; // exits the program back to terminal

                    case LIST:
                        Ui.showMessage(taskList.formatList());
                        break;

                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        taskList.add(cmd.task);
                        storage.save(taskList.getTasks());
                        Ui.showMessage("Got it. I've added this task:\n  " + cmd.task
                                + "\nNow you have " + taskList.size() + " tasks in the list.");
                        break;

                    case MARK: {
                        Task t = taskList.get(cmd.index);
                        t.markDone();
                        storage.save(taskList.getTasks());
                        Ui.showMessage("Nice! I've marked this task as done:\n  " + t);
                        break;
                    }

                    case UNMARK: {
                        Task t = taskList.get(cmd.index);
                        t.unmarkDone();
                        storage.save(taskList.getTasks());
                        Ui.showMessage("OK, I've marked this task as not done yet:\n  " + t);
                        break;
                    }

                    case DELETE: {
                        Task removed = taskList.delete(cmd.index);
                        storage.save(taskList.getTasks());
                        Ui.showMessage("Noted. I've removed this task:\n  " + removed
                                + "\nNow you have " + taskList.size() + " tasks in the list.");
                        break;
                    }

                    default:
                        throw new KrexException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                } catch (KrexException e) {
                    Ui.showError(e.getMessage());
                }
            }
        }
    }
}