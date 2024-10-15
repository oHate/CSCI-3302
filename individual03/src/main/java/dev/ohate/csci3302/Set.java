package dev.ohate.csci3302;

public class Set<E> implements SetADT<E> {

    private Node<E> leadNode;

    /**
     * Constructs set with element as the head.
     * @param element the head of the set.
     */
    public Set(E element) {
        leadNode = new Node<>(element);
    }

    /**
     * Constructs default Set with a null head.
     */
    public Set() {
        leadNode = null;
    }

    /**
     * Determines if the set is empty.
     * @return true if there are no elements in the set.
     */
    @Override
    public boolean isEmpty() {
        return leadNode == null;
    }

    /**
     * Gets the size of the set.
     * @return the size of the set.
     */
    @Override
    public int size() {
        int size = 0;
        Node<E> curNode = leadNode;

        while (curNode != null) {
            size++;
            curNode = curNode.getNext();
        }

        return size;
    }

    /**
     * Sets the leadNode to null effectively clearing it.
     */
    @Override
    public void removeAll() {
        leadNode = null;
    }

    /**
     * Iterates through the set and checks if it contains the element.
     * @param element the element to look for in the set.
     * @return true if it found the element.
     */
    @Override
    public boolean contains(E element) {
        Node<E> curNode = leadNode;

        while (curNode != null) {
            if (curNode.getItem() == element) {
                return true;
            }

            curNode = curNode.getNext();
        }

        return false;
    }

    /**
     * Determines if one set is a subset of the other.
     * @param set the set to check.
     * @return if this set is a subset of a set.
     */
    @Override
    public boolean isSubsetOf(Set<E> set) {
        if (isEmpty()) {
            return true;
        }

        Node<E> curNode = leadNode;

        while (curNode != null) {
            if (!set.contains(curNode.getItem())) {
                return false;
            }

            curNode = curNode.getNext();
        }

        return true;
    }

    /**
     * Adds a new element to the head of the set if it's not already in the set.
     * @param element the element to add.
     */
    @Override
    public void add(E element) {
        if (contains(element)) {
            return;
        }

        Node<E> newNode = new Node<>(element);
        newNode.setNext(leadNode);
        leadNode = newNode;
    }

    /**
     * Creates a new set consisting of this set and another set.
     * @param set the set to add elements from.
     * @return a new set of two sets.
     */
    @Override
    public Set<E> union(Set<E> set) {
        Set<E> newSet = new Set<>();

        newSet.addAll(this);
        newSet.addAll(set);

        return newSet;
    }

    /**
     * Helper method used for adding all elements from one set to another.
     * @param set the set to add elements from.
     */
    private void addAll(Set<E> set) {
        Node<E> curNode = set.leadNode;

        while (curNode != null) {
            add(curNode.getItem());
            curNode = curNode.getNext();
        }
    }

    /**
     * Creates a new set that has all elements found in both sets.
     * @param set the set to add elements from.
     * @return a new set that has all elements that were the same in both sets.
     */
    @Override
    public Set<E> intersect(Set<E> set) {
        Set<E> newSet = new Set<>();

        if (this.isEmpty() || set.isEmpty()) {
            return newSet;
        }

        Node<E> curNode = leadNode;

        while (curNode != null) {
            if (set.contains(curNode.getItem())) {
                newSet.add(curNode.getItem());
            }

            curNode = curNode.getNext();
        }

        return newSet;
    }

    /**
     * Removes an element from the set.
     * @param element the element to remove.
     */
    @Override
    public void remove(E element) {
        if (isEmpty()) {
            return;
        }

        Node<E> prevNode = null;
        Node<E> curNode = leadNode;

        while (curNode != null) {
            if (curNode.getItem() == element) {
                if (prevNode != null) {
                    prevNode.setNext(curNode.getNext());
                } else {
                    leadNode = curNode.getNext();
                }
            }

            prevNode = curNode;
            curNode = curNode.getNext();
        }
    }

    /**
     * The following method determines if the two sets are equal.
     * @param - o could be any object
     * @return - false if the object is not set or if the sets are not equal;
     * true is returned otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Set))
            return false;

        Set<E> set = (Set) o;

        return (set.isSubsetOf(this) && this.isSubsetOf(set));
    }

    /**
     * The following method expresses a set as a string.
     * @return - returns a string representation of a set
     */
    @Override
    public String toString() {
        String s = "{";
        Node<E> current = this.leadNode;
        while (current != null) {
            s = s + current.getItem().toString();
            current = current.getNext();
            if (current != null)
                s += ", ";
        }

        return s+"}";
    }

}
