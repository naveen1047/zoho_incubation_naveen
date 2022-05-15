package _Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _HashSet {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 11, 22, 33, 44};

        Set<Integer> hashSet = new HashSet<>(Arrays.asList(arr));

        System.out.println(hashSet);

        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(22);

        System.out.println(hashSet);
    }
}
