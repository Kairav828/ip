package krex;

import java.util.ArrayList;

public class Krex {
    private static final String SAVE_PATH = "data/krex.txt";

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Krex(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        TaskList loadedTasks;
        try {
            ArrayList<Task> loaded = storage.load();
            loadedTasks = new TaskList(loaded);
        } catch (KrexException e) {
            ui.showError(e.getMessage());
            loadedTasks = new TaskList();
        }
        taskList = loadedTasks;
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            try {
                Command cmd = Parser.parse(input);

                switch (cmd.type) {
                case BYE:
                    ui.showBye();
                    storage.save(taskList.getTasks());
                    ui.close();
                    return;

                case LIST:
                    ui.showMessage(taskList.formatList());
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    taskList.add(cmd.task);
                    storage.save(taskList.getTasks());
                    ui.showMessage("Got it. I've added this task:\n  " + cmd.task
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
                    break;

                case MARK: {
                    Task t = taskList.get(cmd.index);
                    t.markDone();
                    storage.save(taskList.getTasks());
                    ui.showMessage("Nice! I've marked this task as done:\n  " + t);
                    break;
                }

                case UNMARK: {
                    Task t = taskList.get(cmd.index);
                    t.unmarkDone();
                    storage.save(taskList.getTasks());
                    ui.showMessage("OK, I've marked this task as not done yet:\n  " + t);
                    break;
                }

                case DELETE: {
                    Task removed = taskList.delete(cmd.index);
                    storage.save(taskList.getTasks());
                    ui.showMessage("Noted. I've removed this task:\n  " + removed
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
                    break;
                }

                case FIND:
                    ui.showMessage(taskList.formatFind(cmd.keyword));
                    break;
                    
                default:
                    throw new KrexException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (KrexException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Krex(SAVE_PATH).run();
    }
}