/**
 * LeetCode 622: Design Circular Queue
 * https://leetcode.com/problems/design-circular-queue/
 *
 * Approach:
 * Implement a circular queue using a fixed-size array.
 *
 * front points to the first element in the queue.
 * rear points to the last element in the queue.
 *
 * Empty state:
 * front = -1, rear = -1
 *
 * Circular indexing:
 * (index + 1) % capacity
 *
 * When the last element is removed, both front and rear
 * are reset to -1.
 *
 * Time Complexity:
 * O(1) per operation.
 *
 * Space Complexity:
 * O(k), where k is the queue capacity.
 */
public class MyCircularQueue {

    private int front;
    private int rear;
    private final int[] input;

    /**
     * Initializes the circular queue with capacity k.
     *
     * @param k maximum number of elements
     */
    public MyCircularQueue(int k) {
        front = -1;
        rear = -1;
        input = new int[k];
    }

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param value element to insert
     * @return true if insertion succeeds, false if full
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % input.length;
        }

        input[rear] = value;
        return true;
    }

    /**
     * Removes the front element.
     *
     * @return true if removal succeeds, false if empty
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        // Removing the only remaining element.
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % input.length;
        }

        return true;
    }

    /**
     * Returns the front element.
     *
     * @return front element, or -1 if empty
     */
    public int Front() {
        return isEmpty() ? -1 : input[front];
    }

    /**
     * Returns the rear element.
     *
     * @return rear element, or -1 if empty
     */
    public int Rear() {
        return isEmpty() ? -1 : input[rear];
    }

    /**
     * Checks whether the queue is empty.
     */
    public boolean isEmpty() {
        return front == -1;
    }

    /**
     * Checks whether the queue is full.
     *
     * The next rear position would collide with front.
     */
    public boolean isFull() {
        return (rear + 1) % input.length == front;
    }

    /**
     * Basic local examples for understanding queue behavior.
     */
    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);

        System.out.println(queue.enQueue(10)); // true
        System.out.println(queue.enQueue(20)); // true
        System.out.println(queue.enQueue(30)); // true
        System.out.println(queue.enQueue(40)); // false

        System.out.println(queue.Front());     // 10
        System.out.println(queue.Rear());      // 30

        System.out.println(queue.deQueue());   // true
        System.out.println(queue.enQueue(40)); // true (wrap-around)

        System.out.println(queue.Front());     // 20
        System.out.println(queue.Rear());      // 40
        System.out.println(queue.isFull());    // true

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        System.out.println(queue.isEmpty());   // true
        System.out.println(queue.Front());     // -1
        System.out.println(queue.Rear());      // -1
    }
}
