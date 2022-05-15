package _List;

import java.util.Stack;

/*
    Methods in stack

    empty
    push
    pop
    peek
    search
*/
public class _Stack {
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) {

        boolean isEmpty = stack.empty();
        System.out.println("isEmpty: " + isEmpty);

        stack.push("apple");
        stack.push("orange");
        stack.push("mango");
        stack.push("banana");
        stack.push("grape");
        print();

        stack.pop();
        print();

        String poppedItem = stack.pop();
        print();
        System.out.println("last popped Items is: " + poppedItem);

        System.out.println("peek: " + stack.peek());

        System.out.println("find orange position: " + stack.search("orange"));
    }

    static void print() {
        for (String item: stack)
            System.out.print(item + " ");
        System.out.println();
    }
}
