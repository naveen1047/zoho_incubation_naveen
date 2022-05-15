package _Queue;

import java.util.PriorityQueue;
import java.util.Queue;

/*
    Methods in PriorityQueue

    add
    offer
    peek
    poll
    remove
    element
*/
public class _PriorityQueue {
    static Queue<String> queue = new PriorityQueue<>();

    public static void main(String[] args) {
        // add - returns true always and throws Exception if can't add to queue
        queue.add("Alan");
        queue.add("Bob");
        queue.add("David");
        queue.add("Ram");
        print();

        // offer - returns true/false
        queue.offer("Naveen");
        print();

        // peek
        String peek = queue.peek();
        System.out.println("peek: " + peek);

        // poll - return and remove head
        String head = queue.poll();
        System.out.println("head: " + head);

        // remove
        queue.remove();
        print();

        // element
        String element = queue.element();
        System.out.println("element: " + element);
    }

    static void print() {
        for (String item: queue)
            System.out.print(item + " ");
        System.out.println();
    }
}
