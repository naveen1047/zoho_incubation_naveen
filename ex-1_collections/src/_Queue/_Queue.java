package _Queue;

import java.util.LinkedList;
import java.util.Queue;

/*
* Queue methods
*
*   add
    offer
    peek
    poll
    remove
    element
* */
public class _Queue {
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        // add - returns true always and throws Exception if can't add to queue
        queue.add("Alan");
        queue.add("David");
        queue.add("Bob");
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
        String elem = queue.remove();
        print();

        // element
        String element = queue.element();
        System.out.println("element: " + element);
    }

    static void print() {
        for (String item : queue)
            System.out.print(item + " ");
        System.out.println();
    }
}
