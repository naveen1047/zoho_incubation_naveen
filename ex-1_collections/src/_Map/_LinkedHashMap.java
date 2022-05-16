package _Map;

import java.util.LinkedHashMap;
import java.util.Map;

public class _LinkedHashMap {
    public static void main(String[] args) {
        // Maintains insertion order
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Arun");
        map.put(2, "Bob");
        map.put(3, "David");
        map.put(4, "Naveen");

        map.put(0, "Aravind");
        System.out.println(map);

    }
}
