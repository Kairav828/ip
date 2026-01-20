import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int oneBasedIndex) {
        return tasks.get(oneBasedIndex - 1);
    }

    public int size() {
        return tasks.size();
    }

    public String formatList() {
        if (tasks.isEmpty()) {
            return "No tasks in your list yet.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
