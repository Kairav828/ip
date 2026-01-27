public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getDescription() { return description; }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
