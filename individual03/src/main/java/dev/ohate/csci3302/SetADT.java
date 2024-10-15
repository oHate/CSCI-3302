package dev.ohate.csci3302;

public interface SetADT<E> {

    /**
     * This method determine if the set is empty.
     *
     * @return - true if the set is empty,
     *         false otherwise
     */
    public boolean isEmpty();

    /**
     * This method finds and returns the number of elements in the set.
     *
     * @return - an integer representing the number of elements in the set.
     */
    public int size();

    /**
     * This method removes all elements from the set.
     * Nothing is returned. The implicit set has not elements.
     */
    public void removeAll();

    /**
     * This method determines is a given element is in the set.
     *
     * @param element - the element to look for in the set
     * @return true if the element is in the set, false otherwise
     */
    public boolean contains(E element);

    /**
     * This method determines if the implicitly given set is a subset of the
     * given set paramter.
     *
     * @param set - a set of elements
     * @return true if the implicit set (this) is a subset of the given set; return
     *         false otherwise
     */
    public boolean isSubsetOf(Set<E> set);

    /**
     * This method adds an element to the implicit set. Note: a set does not
     * have duplicate elements.
     *
     * @param element - the element to add to the implicit set
     */
    public void add(E element);

    /**
     * This method finds the union of the implicit set (this) and the
     * given set. A new set is created and returned that contains the
     * elements from both sets.
     *
     * @param set - a set of elements
     * @return - a new set containing all elements from the implicity set
     *         and the given set.
     */
    public Set<E> union(Set<E> set);

    /**
     * This method finds the intersection of the implicit set (this) and the
     * given set. A new set is created and returned that contains only the
     * elements found in both sets.
     *
     * @param set - a set of elements
     * @return - a new set containing only the element founds in both sets
     */
    public Set<E> intersect(Set<E> set);

    /**
     * The method removes the given element from the set if the element is present.
     * If the element is not in the set, then nothing happens.
     *
     * @param element - an element to remove from a set
     */
    public void remove(E element);

}
