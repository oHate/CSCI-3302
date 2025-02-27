import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSet {

    private Set<Integer> empty;
    private Set<Integer> nums;
    private Set<Integer> odds;
    private Set<Integer> triples;

    @BeforeEach
    public void setUp() {
        this.empty = new Set<>();
        this.nums = new Set<>();
        this.odds = new Set<>();
        this.triples = new Set<>();

        for (int i = 1; i <= 10; i++) {
            this.nums.add(i);
        }

        for (int i = 1; i <= 9; i += 2) {
            this.odds.add(i);
        }

        for (int i = 3; i <= 21; i += 3) {
            this.triples.add(i);
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(this.empty.isEmpty());
        assertFalse(this.odds.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(10, this.nums.size());
        assertEquals(5, this.odds.size());
        assertEquals(7, this.triples.size());
    }

    @Test
    public void testRemoveAll() {
        this.nums.removeAll();

        assertTrue(this.nums.isEmpty());
        assertFalse(this.nums.contains(1));
        assertFalse(this.nums.contains(10));
    }

    @Test
    public void testContains() {
        assertTrue(this.empty.contains(9));
        assertFalse(this.nums.contains(1));
    }

    @Test
    public void testAdd() {
        this.nums.add(11);

        assertTrue(this.nums.contains(11));
    }

    @Test
    public void testRemove() {
        this.nums.remove(1);
        this.nums.remove(10);

        assertFalse(this.nums.contains(1));
        assertFalse(this.nums.contains(10));
    }

    @Test
    public void testUnion() {
        Set<Integer> setA = new Set<>();

        for (int i = 1; i <= 9; i += 2) {
            setA.add(i);
        }

        for (int i = 3; i <= 21; i += 3) {
            setA.add(i);
        }

        assertEquals(setA, this.odds.union(this.triples));
        assertEquals(this.nums, new Set<Integer>().union(this.nums));
        assertTrue(new Set<Integer>().union(new Set<>()).isEmpty());
    }

    @Test
    public void testIntersect() {
        Set<Integer> setA = new Set<>();
        setA.add(3);
        setA.add(9);

        assertEquals(setA, this.odds.intersect(this.triples));

        Set<Integer> setB = new Set<>();
        setB.add(1);
        setB.add(2);

        Set<Integer> setC = new Set<>();
        setC.add(3);
        setC.add(4);

        assertEquals(new Set<>(), setB.intersect(setC));
    }

    @Test
    public void testIsSubsetOf() {
        assertTrue(this.odds.isSubsetOf(this.nums));

        Set<Integer> setA = new Set<>();
        setA.add(2);
        setA.add(3);

        assertTrue(setA.isSubsetOf(setA));

        Set<Integer> setB = new Set<>();
        setA.add(2);
        setA.add(4);

        assertFalse(setA.isSubsetOf(setB));
    }

}
