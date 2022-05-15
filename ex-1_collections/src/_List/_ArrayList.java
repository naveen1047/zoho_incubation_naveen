package _List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _ArrayList {
    /*
        some basic methods:

        replaceAll, sort

        get, add, addAll, set, remove,
        indexOf, lastIndexOf, subList,
    */
    static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        _add();
        print(list, "After add method:");

        _get();
        _addAll();
        print(list, "After addAll method:");

        _set();
        print(list, "After set method:");

        _remove();
        print(list, "After remove method:");

        _indexOf();

        _lastIndexOf();

        _subList();

        _sort();
        print(list, "After sorting");

        _desc();
        print(list, "After sorting in reverseOrder");

        _replaceAll();
        print(list, "After replaceAll");

    }

    static void _replaceAll() {
        list.replaceAll(v -> v + 10);
    }

    static void _sort() {
        Collections.sort(list);
    }

    static void _desc() {
        Collections.sort(list, Collections.reverseOrder());
    }

    static void _subList() {
        // returns the subList(fromInd, toInd) of list
        List<Integer> subList =  new ArrayList<>();
        subList = list.subList(3, 6);
        print(subList, "SubList: ");
    }

    static void _lastIndexOf() {
        // it will get the last index of the elem
        list.lastIndexOf(10);
    }

    static void _indexOf() {
        // returns value
        list.indexOf(10);
    }

    static void _remove() {
        list.remove(10);
    }

    static void _addAll() {
        list.addAll(new ArrayList<>(
                Arrays.asList(
                        1, 2, 3, 4, 5)
        ));
    }

    static void _set() {
        list.set(1, 10);
        list.set(2, 44);
        list.set(0, 23);
    }

    static void _get() {
        System.out.println("get method:");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    static void _add() {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        print(list, "Add method: ");
    }

    static <E> void print(List<E> values) {
        for (E value: values)
            System.out.print(value + " ");
        System.out.println();
    }

    static <E> void print(List<E> values, String message) {
        System.out.println(message);

        for (E value: values)
            System.out.print(value + " ");
        System.out.println();
    }
}
