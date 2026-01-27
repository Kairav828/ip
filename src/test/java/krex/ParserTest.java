package krex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDeadline_validInput_success() throws KrexException {
        Command c = Parser.parse("deadline submit report /by 2026-02-01");
        assertEquals(CommandType.DEADLINE, c.type);
        assertEquals("submit report", c.task.getDescription());
    }

    @Test
    public void parseDeadline_missingBy_throwsException() {
        assertThrows(KrexException.class, () -> {
            Parser.parse("deadline submit report");
        });
    }
}
