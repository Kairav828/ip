package krex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addAndGet_validIndex_success() throws KrexException {
        TaskList list = new TaskList();
        list.add(new Todo("read book"));
        Task t = list.get(1);
        assertEquals("read book", t.getDescription());
    }

    @Test
    public void get_invalidIndex_throwsException() {
        TaskList list = new TaskList();
        assertThrows(KrexException.class, () -> list.get(1));
    }
}
