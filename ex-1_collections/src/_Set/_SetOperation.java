package _Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _SetOperation {
    public static void main(String[] args) {
        Integer[] a = {12, 14, 18, 19, 20, 25};
        Integer[] b = {22, 33, 55, 66, 12, 20, 19};

        Set<Integer> setA = new HashSet<>(Arrays.asList(a));
        Set<Integer> setB = new HashSet<>(Arrays.asList(b));

        System.out.println("SetA and SetB");
        System.out.println(setA);
        System.out.println(setB);
        System.out.println();

        // union
        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println(union);

        // intersection
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println(intersection);

        // difference
        Set<Integer> diff = new HashSet<>(setA);
        diff.removeAll(setB);
        System.out.println("Set A - B");
        System.out.println(diff);
    }
}
