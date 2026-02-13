package krex;

/**
 * Represents a list of tasks and provides operations to manage them.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loaded) {
        assert loaded != null : "loaded list must not be null";
        this.tasks = loaded;
    }

    public void add(Task task) {
        assert task != null : "task must not be null";
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Converts a 1-based index to 0-based and validates bounds.
     */
    private int toZeroIndex(int idx) throws KrexException {
        int zeroIdx = idx - 1;
        if (zeroIdx < 0 || zeroIdx >= tasks.size()) {
            throw new KrexException("OOPS!!! That task number is out of range.");
        }
        return zeroIdx;
    }

    public Task get(int idx) throws KrexException {
        return tasks.get(toZeroIndex(idx));
    }

    public Task delete(int idx) throws KrexException {
        return tasks.remove(toZeroIndex(idx));
    }

    /** Returns a COPY of the internal tasks list (so Storage can save safely). */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> asUnmodifiableList() {
        return Collections.unmodifiableList(tasks);
    }

    public String formatList() {
        if (tasks.isEmpty()) {
            return "No tasks in your list yet.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String formatFind(String keyword) {
        assert keyword != null : "keyword must not be null";

        String key = keyword.toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        int count = 0;
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(key)) {
                count++;
                sb.append(count).append(".").append(t).append("\n");
            }
        }

        if (count == 0) {
            return "No matching tasks found.";
        }

        return sb.toString().trim();
    }
}
