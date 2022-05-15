package _Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/*
    Methods in Deque

    addFirst,
    addLast,
    descendingIterator,
    getFirst,
    getLast,
    offerFirst,
    offerLast,
    peekFirst,
    peekLast,
    pollFirst,
    pollLast,
    pop,
    push,
    removeFirst,
    removeLast,
    removeFirstOccurrence,
    removeLastOccurrence
*/
public class _Deque {

    static Deque<String> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        deque.add("alan");
        deque.add("Bob");
        deque.add("David");
        deque.add("George");
        deque.add("Henry");
        deque.add("Ram");

        // addFirst,
        deque.addFirst("adam");
        print();

        // addLast,
        deque.addLast("Steve");
        print();

        // descendingIterator,
        System.out.println("descendingIterator: ");
        Iterator<String> descendingIterator = deque.descendingIterator();

        while (descendingIterator.hasNext())
            System.out.print(descendingIterator.next() + " ");
        System.out.println();

        // getFirst,
        System.out.println("getFirst: ");
        System.out.println(deque.getFirst());

        // getLast,
        System.out.println("getLast: ");
        System.out.println(deque.getLast());

        // offerFirst,
        deque.offerFirst("Kavin");

        // offerLast,
        deque.offerLast("Arun");
        print();

        // peekFirst,
        System.out.println("PeekFirst: " + deque.peekFirst());

        // peekLast,
        System.out.println("PeekLast" + deque.peekLast());

        // pollFirst,
        deque.pollFirst();

        // pollLast,
        deque.pollLast();
        print();

        // pop,
        String poppedValue = deque.pop();

        // push,
        deque.push(poppedValue);
        print();

        // removeFirst,
        deque.removeFirst();

        // removeLast,
        deque.removeLast();
        print();

        // removeFirstOccurrence,
        deque.removeFirstOccurrence("Bob");

        // removeLastOccurrence
        deque.removeLastOccurrence("Bob");
        print();
    }

    static void print() {
        for (String item : deque)
            System.out.print(item + " ");
        System.out.println();
    }
}
