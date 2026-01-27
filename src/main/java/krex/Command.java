public class Command {
    public final CommandType type;
    public final Task task;   // for add commands
    public final int index;   // for mark/unmark/delete (1-based)

    private Command(CommandType type, Task task, int index) {
        this.type = type;
        this.task = task;
        this.index = index;
    }

    public static Command bye() {
        return new Command(CommandType.BYE, null, -1);
    }

    public static Command list() {
        return new Command(CommandType.LIST, null, -1);
    }

    public static Command add(CommandType type, Task task) {
        return new Command(type, task, -1);
    }

    public static Command index(CommandType type, int index) {
        return new Command(type, null, index);
    }
}