import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TestContagion {

    private Contagion contagion;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.contagion = new Contagion("src/test/resources/test.dat");
    }

    @Test
    public void testInfect() {
        this.contagion.infect(0, 0);

        assertEquals(4, this.contagion.countInfections());

        assertTrue(this.contagion.isInfected(0, 0));
        assertTrue(this.contagion.isInfected(0, 1));
        assertTrue(this.contagion.isInfected(1, 1));
        assertTrue(this.contagion.isInfected(1, 2));

        assertFalse(this.contagion.isInfected(0, 2));
        assertFalse(this.contagion.isInfected(2, 0));
    }

    @Test
    public void testOutOfBounds() {
        assertFalse(() -> this.contagion.isInfected(10, 10));
        assertDoesNotThrow(() -> this.contagion.infect(10, 10));
    }

    @Test
    public void testToString() {
        this.contagion.infect(0, 0);

        String output = this.contagion.toString();

        String expectedOutput =
                "X X 0 0 0 0\n" +
                "0 X X 0 1 1\n" +
                "0 0 0 0 1 1\n" +
                "1 1 0 0 0 1";

        assertEquals(expectedOutput, output);

        this.contagion.infect(2, 5);

        output = this.contagion.toString();

        expectedOutput =
                "X X 0 0 0 0\n" +
                "0 X X 0 X X\n" +
                "0 0 0 0 X X\n" +
                "1 1 0 0 0 X";

        assertEquals(expectedOutput, output);
    }

}
