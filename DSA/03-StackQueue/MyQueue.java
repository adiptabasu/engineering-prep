import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 232: Implement Queue using Stacks
 *
 * Problem URL:
 * https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * Approach:
 * Use two stacks to implement FIFO (First In, First Out) behavior.
 *
 * 1. inputStack:
 *    Stores newly inserted elements.
 *
 * 2. outputStack:
 *    Supplies elements for pop() and peek().
 *
 * When outputStack is empty, transfer all elements from inputStack
 * to outputStack. This reverses their order, placing the oldest
 * element at the top of outputStack.
 *
 * Complexity:
 * - push(): O(1)
 * - pop(): O(1) amortized; O(n) worst case for an individual call
 * - peek(): O(1) amortized; O(n) worst case for an individual call
 * - empty(): O(1)
 * - Space: O(n)
 */
class MyQueue {

    private final Deque<Integer> inputStack;
    private final Deque<Integer> outputStack;

    /**
     * Initializes the two stacks.
     */
    public MyQueue() {
        inputStack = new ArrayDeque<>();
        outputStack = new ArrayDeque<>();
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param x element to insert
     * Time Complexity: O(1)
     */
    public void push(int x) {
        inputStack.push(x);
    }

    /**
     * Transfers elements from inputStack to outputStack.
     *
     * This operation is performed only when outputStack is empty.
     * Reversing the order ensures that the oldest element is
     * available at the top of outputStack.
     *
     * Time Complexity: O(n) for a transfer of n elements.
     */
    private void transferElements() {
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * If outputStack is empty, transfer elements first.
     *
     * Amortized Time Complexity: O(1)
     */
    public int pop() {
        if (outputStack.isEmpty()) {
            transferElements();
        }

        return outputStack.pop();
    }

    /**
     * Returns the front element without removing it.
     *
     * If outputStack is empty, transfer elements first.
     *
     * Amortized Time Complexity: O(1)
     */
    public int peek() {
        if (outputStack.isEmpty()) {
            transferElements();
        }

        return outputStack.peek();
    }

    /**
     * Checks whether the queue contains any elements.
     *
     * The queue is empty only when both stacks are empty.
     *
     * Time Complexity: O(1)
     */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    /**
     * Local test cases.
     *
     * LeetCode invokes the MyQueue methods directly, so this main()
     * method is intended for local testing and repository reference.
     */
    public static void main(String[] args) {

        MyQueue queue = new MyQueue();

        // Test 1: An initially empty queue.
        check(queue.empty(), true, "Initially empty");

        // Test 2: Insert elements.
        queue.push(10);
        queue.push(20);
        queue.push(30);

        check(queue.empty(), false, "Queue after insertion");

        // Test 3: peek() should not remove the front element.
        check(queue.peek(), 10, "Peek should return 10");
        check(queue.peek(), 10, "Repeated peek should return 10");

        // Test 4: FIFO behavior.
        check(queue.pop(), 10, "First pop should return 10");
        check(queue.pop(), 20, "Second pop should return 20");

        // Test 5: Insert an element while outputStack still has data.
        queue.push(40);

        check(queue.pop(), 30, "Third pop should return 30");
        check(queue.pop(), 40, "Fourth pop should return 40");

        // Test 6: Queue should now be empty.
        check(queue.empty(), true, "Queue after removing all elements");

        // Test 7: Reuse the queue after it becomes empty.
        queue.push(50);

        check(queue.peek(), 50, "Peek after reusing queue");
        check(queue.pop(), 50, "Pop after reusing queue");
        check(queue.empty(), true, "Queue empty after reuse");

        System.out.println("All test cases passed!");
    }

    /**
     * Checks an integer result against the expected value.
     */
    private static void check(int actual, int expected, String description) {
        if (actual != expected) {
            throw new AssertionError(
                description + ": expected " + expected + ", got " + actual
            );
        }
    }

    /**
     * Checks a boolean result against the expected value.
     */
    private static void check(boolean actual, boolean expected, String description) {
        if (actual != expected) {
            throw new AssertionError(
                description + ": expected " + expected + ", got " + actual
            );
        }
    }
}
