import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 225: Implement Stack using Queues
 * https://leetcode.com/problems/implement-stack-using-queues/
 *
 * Approach:
 * Use a single queue and maintain the invariant that the
 * most recently pushed element is always at the front.
 *
 * During push():
 * 1. Record the current queue size.
 * 2. Insert the new element.
 * 3. Rotate all previously existing elements to the rear.
 *
 * This ensures that pop() and top() operate on the front.
 *
 * Time Complexity:
 * push()  - O(n)
 * pop()   - O(1)
 * top()   - O(1)
 * empty() - O(1)
 *
 * Space Complexity:
 * O(n), where n is the number of stack elements.
 */
public class MyStack {

    private final Queue<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        int oldSize = queue.size();
        queue.offer(x);

        for (int i = 0; i < oldSize; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    /**
     * Basic local examples.
     */
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.top());   // 30
        System.out.println(stack.pop());   // 30
        System.out.println(stack.top());   // 20
        System.out.println(stack.pop());   // 20
        System.out.println(stack.pop());   // 10
        System.out.println(stack.empty()); // true
    }
}
