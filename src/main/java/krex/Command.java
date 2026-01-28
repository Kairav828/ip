package krex;

public class Command {
    public final CommandType type;
    public final Task task;   // for add commands
    public final int index;   // for mark/unmark/delete (1-based)
    public final String keyword;

    private Command(CommandType type, Task task, int index, String keyword) {
        this.type = type;
        this.task = task;
        this.index = index;
        this.keyword = keyword;
    }

    public static Command bye() {
        return new Command(CommandType.BYE, null, -1, null);
    }

    public static Command list() {
        return new Command(CommandType.LIST, null, -1, null);
    }

    public static Command add(CommandType type, Task task) {
        return new Command(type, task, -1, null);
    }

    public static Command index(CommandType type, int index) {
        return new Command(type, null, index, null);
    }

    public static Command find(String keyword) {
        return new Command(CommandType.FIND, null, -1, keyword);
    }
}