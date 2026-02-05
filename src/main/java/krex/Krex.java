package krex;

import java.util.ArrayList;

public class Krex {
    private static final String SAVE_PATH = "data/krex.txt";

    private final Storage storage;
    private final TaskList taskList;
    private boolean isExit = false;

    public Krex() {
        this(SAVE_PATH);
    }

    public Krex(String filePath) {
        storage = new Storage(filePath);

        TaskList loadedTasks;
        try {
            ArrayList<Task> loaded = storage.load();
            loadedTasks = new TaskList(loaded);
        } catch (KrexException e) {
            loadedTasks = new TaskList();
        }
        taskList = loadedTasks;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponse(String input) {
        String trimmed = input == null ? "" : input.trim();

        try {
            Command cmd = Parser.parse(trimmed);

            switch (cmd.type) {
            case BYE:
                isExit = true;
                storage.save(taskList.getTasks());
                return "Bye. Hope to see you again soon!";

            case LIST:
                return taskList.formatList();

            case TODO:
            case DEADLINE:
            case EVENT:
                taskList.add(cmd.task);
                storage.save(taskList.getTasks());
                return "Got it. I've added this task:\n  " + cmd.task
                        + "\nNow you have " + taskList.size() + " tasks in the list.";

            case MARK: {
                Task t = taskList.get(cmd.index);
                t.markDone();
                storage.save(taskList.getTasks());
                return "Nice! I've marked this task as done:\n  " + t;
            }

            case UNMARK: {
                Task t = taskList.get(cmd.index);
                t.unmarkDone();
                storage.save(taskList.getTasks());
                return "OK, I've marked this task as not done yet:\n  " + t;
            }

            case DELETE: {
                Task removed = taskList.delete(cmd.index);
                storage.save(taskList.getTasks());
                return "Noted. I've removed this task:\n  " + removed
                        + "\nNow you have " + taskList.size() + " tasks in the list.";
            }

            case FIND:
                return taskList.formatFind(cmd.keyword);

            default:
                throw new KrexException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } catch (KrexException e) {
            return e.getMessage();
        }
    }
}
