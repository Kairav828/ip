import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath);
    }

    public ArrayList<Task> load() throws KrexException {
        ensureParentFolderExists();

        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                tasks.add(parseTaskFromLine(line));
            }
            return tasks;
        } catch (IOException e) {
            throw new KrexException("OOPS!!! I couldn't load your saved tasks.");
        }
    }

    public void save(List<Task> tasks) throws KrexException {
        ensureParentFolderExists();

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task t : tasks) {
                writer.write(formatTaskForSave(t));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new KrexException("OOPS!!! I couldn't save your tasks.");
        }
    }

    private void ensureParentFolderExists() throws KrexException {
        try {
            Path parent = filePath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            throw new KrexException("OOPS!!! I couldn't create the data folder.");
        }
    }

    private Task parseTaskFromLine(String line) throws KrexException {
        // Format:
        // T | 1 | desc
        // D | 0 | desc | by
        // E | 0 | desc | from | to
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new KrexException("OOPS!!! Your save file is corrupted.");
        }

        String type = parts[0].trim();
        String doneFlag = parts[1].trim();
        boolean isDone = doneFlag.equals("1");

        Task task;
        switch (type) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                if (parts.length < 4) {
                    throw new KrexException("OOPS!!! Your save file is corrupted.");
                }
                task = new Deadline(parts[2].trim(), parts[3].trim());
                break;
            case "E":
                if (parts.length < 5) {
                    throw new KrexException("OOPS!!! Your save file is corrupted.");
                }
                task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new KrexException("OOPS!!! Your save file is corrupted.");
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }

    private String formatTaskForSave(Task t) {
        // We rely on Task subclasses to expose fields via toString parsing minimally.
        // We'll encode based on instanceof.
        String doneFlag = t.isDone() ? "1" : "0";

        if (t instanceof Todo) {
            Todo todo = (Todo) t;
            return "T | " + doneFlag + " | " + todo.getDescription();
        }
        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + doneFlag + " | " + d.getDescription() + " | " + d.getByRaw();
        }
        if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + doneFlag + " | " + e.getDescription() + " | " + e.getFromRaw() + " | " + e.getToRaw();
        }
        return "T | " + doneFlag + " | " + t.toString();
    }
}