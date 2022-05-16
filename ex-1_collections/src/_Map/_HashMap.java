package _Map;

import java.util.HashMap;
import java.util.Map;

public class _HashMap {
    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<>();

        hashMap.put(1, "Arun");
        hashMap.put(2, "Bob");
        hashMap.put(3, "David");
        hashMap.put(4, "Naveen");

        // get method
        System.out.println("get method: " + hashMap.get(1));

        // entry set
        System.out.println("Map.Entry");
        for (Map.Entry m : hashMap.entrySet()) {
            System.out.println(m.getKey() + ": " + m.getValue());
        }

        // remove
        System.out.println("remove");
        hashMap.remove(1);
        printMap(hashMap);

        // compute
        hashMap.compute(2, (key, value) -> value.toUpperCase());

        System.out.println(hashMap);
    }

    private static void printMap(Map<Integer, String> hashMap) {
        for (Map.Entry m : hashMap.entrySet()) {
            System.out.println(m.getKey() + ": " + m.getValue());
        }
    }
}
