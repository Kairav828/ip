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
        this.tasks = loaded;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) throws KrexException {
        int zeroIdx = idx - 1;
        if (zeroIdx < 0 || zeroIdx >= tasks.size()) {
            throw new KrexException("OOPS!!! That task number is out of range.");
        }
        return tasks.get(zeroIdx);
    }

    public Task delete(int idx) throws KrexException {
        int zeroIdx = idx - 1;
        if (zeroIdx < 0 || zeroIdx >= tasks.size()) {
            throw new KrexException("OOPS!!! That task number is out of range.");
        }
        return tasks.remove(zeroIdx);
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