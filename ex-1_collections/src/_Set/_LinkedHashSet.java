package _Set;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class _LinkedHashSet {
    public static void main(String[] args) {
        Integer[] a = {12, 14, 18, 19, 20, 25};

        // Maintains insertion order
        Set<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(a));

        linkedHashSet.add(25);
        linkedHashSet.add(12);
        linkedHashSet.add(33);
        linkedHashSet.add(3);

        System.out.println(linkedHashSet);
    }
}
