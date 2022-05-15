package _Set;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class _TreeSet {
    public static void main(String[] args) {
        // TreeSet is implemented from SortedSet
        Set<Integer> treeSet = new TreeSet<>(Arrays.asList(100, 1, 2, 99, 2, 4, 5, 2, 3));

        System.out.println(treeSet);
    }
}
