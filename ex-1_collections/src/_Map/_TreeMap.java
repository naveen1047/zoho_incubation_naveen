package _Map;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class _TreeMap {
    public static void main(String[] args) {
        SortedMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 5; i >= 1; i--)
            map.put(i, i * 10);

        System.out.println(map);

        System.out.println("headMap");
        System.out.println(map.headMap(2));

        System.out.println("tailMap");
        System.out.println(map.tailMap(4));

        System.out.println("tailMap");
        System.out.println(map.tailMap(7));

        System.out.println("subMap");
        System.out.println(map.subMap(3, 8));
    }
}
