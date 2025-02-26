import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestTable {

    private Table table;

    /**
     * Dummy Data
     */
    @BeforeEach
    public void setUp() {
        this.table = new Table(3, 4);

        this.table.setValue(1, 1, 0.1d);
        this.table.setValue(1, 2, -1.2d);
        this.table.setValue(1, 3, 4.0d);
        this.table.setValue(1, 4, 0.8d);

        this.table.setValue(2, 1, -2.2d);
        this.table.setValue(2, 2, 4.3d);
        this.table.setValue(2, 3, -1.7d);
        this.table.setValue(2, 4, -1.9d);

        this.table.setValue(3, 1, -3.8d);
        this.table.setValue(3, 2, 3.1d);
        this.table.setValue(3, 3, -3.5d);
        this.table.setValue(3, 4, 4.6d);
    }

    @Test
    public void loadValues() {
        Table loadedTable = new Table(3, 4);

        assertDoesNotThrow(() -> loadedTable.loadValues("src/test/resources/test.dat"));
        assertEquals(loadedTable, this.table);
    }

    @Test
    public void outOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> this.table.getValue(0, 1));
        assertThrows(IllegalArgumentException.class, () -> this.table.getValue(4, 1));
        assertThrows(IllegalArgumentException.class, () -> this.table.getValue(1, 0));
        assertThrows(IllegalArgumentException.class, () -> this.table.getValue(1, 5));
    }

    @Test
    public void numberOfRows() {
        assertEquals(3, this.table.getNumberOfRows());
    }

    @Test
    public void numberOfColumns() {
        assertEquals(4, this.table.getNumberOfColumns());
    }

    @Test
    public void getValue() {
        assertEquals(0.1d, this.table.getValue(1, 1));
        assertEquals(-1.7d, this.table.getValue(2, 3));
        assertEquals(4.6d, this.table.getValue(3, 4));
    }

    @Test
    public void setValue() {
        this.table.setValue(1, 1, 3.5d);
        assertEquals(3.5d, this.table.getValue(1, 1));

        this.table.setValue(3, 4, 34d);
        assertEquals(34d, this.table.getValue(3, 4));
    }

    @Test
    public void getMax() {
        assertEquals(4.6d, this.table.getMax());
    }

    @Test
    public void getMin() {
        assertEquals(-3.8d, this.table.getMin());
    }

    @Test
    public void getAverage() {
        assertEquals(0.2166d, this.table.getAverage(), 0.0001);
    }

    @Test
    public void getNumberInRange() {
        assertEquals(4, this.table.getNumberInRange(-3,0));
        assertEquals(2, this.table.getNumberInRange(0,3));
        assertEquals(6, this.table.getNumberInRange(-3,3));
    }

    @Test
    public void getRowMax() {
        assertEquals(4.0d, this.table.getRowMax(1));
        assertEquals(4.3d, this.table.getRowMax(2));
        assertEquals(4.6d, this.table.getRowMax(3));
    }

    @Test
    public void getRowMin() {
        assertEquals(-1.2d, this.table.getRowMin(1));
        assertEquals(-2.2d, this.table.getRowMin(2));
        assertEquals(-3.8d, this.table.getRowMin(3));
    }

    @Test
    public void getRowAverage() {
        assertEquals(0.925d, this.table.getRowAverage(1), 0.0001);
        assertEquals(-0.375d, this.table.getRowAverage(2), 0.0001);
        assertEquals(0.1d, this.table.getRowAverage(3), 0.0001);
    }

    @Test
    public void getColumnMax() {
        assertEquals(0.1d, this.table.getColumnMax(1));
        assertEquals(4.3d, this.table.getColumnMax(2));
        assertEquals(4.0d, this.table.getColumnMax(3));
        assertEquals(4.6d, this.table.getColumnMax(4));
    }

    @Test
    public void getColumnMin() {
        assertEquals(-3.8d, this.table.getColumnMin(1));
        assertEquals(-1.2d, this.table.getColumnMin(2));
        assertEquals(-3.5d, this.table.getColumnMin(3));
        assertEquals(-1.9d, this.table.getColumnMin(4));
    }

    @Test
    public void getColumnAverage() {
        assertEquals(-1.9666d, this.table.getColumnAverage(1), 0.0001);
        assertEquals(2.0666d, this.table.getColumnAverage(2), 0.0001);
        assertEquals(-0.4d, this.table.getColumnAverage(3), 0.0001);
        assertEquals(1.1666d, this.table.getColumnAverage(4), 0.0001);
    }

}
