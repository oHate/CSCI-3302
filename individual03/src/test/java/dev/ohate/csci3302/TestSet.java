package dev.ohate.csci3302;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSet {

    private Set<Integer> nums;
    private Set<Integer> odds;
    private Set<Integer> triples;

    @BeforeEach
    public void setUp() {
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
    public void size() {
        assertEquals(10, this.nums.size());
        assertEquals(5, this.odds.size());
        assertEquals(7, this.triples.size());
    }

    @Test
    public void isEmpty() {
        this.nums.removeAll();

        assertTrue(this.nums.isEmpty());
        assertFalse(this.odds.isEmpty());
    }

    @Test
    public void contains() {
        this.nums.removeAll();

        assertTrue(this.odds.contains(9));
        assertFalse(this.nums.contains(1));
    }

    @Test
    public void add() {
        this.nums.add(11);

        assertTrue(this.nums.contains(11));
    }

    @Test
    public void remove() {
        this.nums.remove(1);
        this.nums.remove(10);

        assertFalse(this.nums.contains(1));
        assertFalse(this.nums.contains(10));
    }

    @Test
    public void union() {
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
    public void intersect() {
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
    public void isSubsetOf() {
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

//      | public boolean isSubsetOf(Set<E> set);
//      | public Set<E> intersect(Set<E> set);

//
//        System.out.println("nums: " + nums);
//        System.out.println("odds: " + odds);
//        System.out.println("union of nums and odds: " + nums.union(odds));
//        System.out.println("intersection of nums and odds: " + nums.intersect(odds));
//        System.out.println();
//
//        System.out.println("subset of nums and odds: " + nums.isSubsetOf(odds));
//        System.out.println();
//
//        Set<Integer> lowNums = new Set<>();
//        lowNums.add(2);
//        lowNums.add(3);
//        lowNums.add(4);
//
//        System.out.println("lowNums: " + lowNums);
//        System.out.println("subset of lowNums and nums: " + lowNums.isSubsetOf(nums));
//        System.out.println();
//
//        Set<Integer> empty1 = new Set<>();
//        Set<Integer> empty2 = new Set<>();
//
//        System.out.println("empty subset of nums: " + empty1.isSubsetOf(nums));
//        System.out.println("nums subset of empty: " + nums.isSubsetOf(empty1));
//        System.out.println();
//
//        System.out.println("empty union: " + empty1.union(empty2));
//        System.out.println("nums intersect empty: " + nums.intersect(empty1));
//        System.out.println();
//
//        System.out.println("nums: " + nums);
//        System.out.println("nums size: " + nums.size());
//        System.out.println("nums empty: " + nums.isEmpty());
//
//        nums.removeAll();
//        System.out.println("nums remove all: " + nums);
//        System.out.println("nums empty: " + nums.isEmpty());
//    }

}
