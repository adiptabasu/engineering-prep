import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Standalone practice questions and reference implementations for monotonic
 * stack boundaries.
 *
 * For each question, return an array of indices. At position i, the result
 * should identify the nearest qualifying element on the requested side.
 * These helpers are useful when an element's contribution depends on how far
 * a subarray can extend before a smaller or greater value blocks it.
 *
 * Questions:
 * 1. For every index, where is the previous value less than or equal to it?
 * 2. For every index, where is the next value strictly less than it?
 * 3. For every index, where is the previous value greater than or equal to it?
 * 4. For every index, where is the next value strictly greater than it?
 *
 * The equality rules are intentionally asymmetric within each pair. For
 * minimum boundaries, the previous side keeps equals and the next side removes
 * them. For maximum boundaries, the previous side keeps equals and the next
 * side removes them. This gives duplicate extrema a consistent owner when
 * counting subarrays.
 */
public class MonotonicStackBoundaryReference {

    /**
     * Question 1: Find the previous smaller-or-equal element for every index.
     *
     * Scan left to right so the stack contains only indices already visited.
     * Before answering for i, pop values greater than arr[i]. They cannot be
     * the previous smaller-or-equal boundary for i. After those candidates
     * are removed, the stack top is the nearest remaining value <= arr[i].
     *
     * The stack stores indices whose values are nondecreasing from bottom to
     * top. Equal values remain, so the closest equal value can serve as this
     * boundary. If the stack is empty, -1 means there is no boundary and can
     * be used as the exclusive left edge in a width calculation.
     *
     * @param arr input values
     * @return previous smaller-or-equal index for each position, or -1
     */
    public int[] prevSmallerElement(int[] arr) {
        int[] previous = new int[arr.length];
        Deque<Integer> increasingStack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            // Larger values cannot be the nearest value <= arr[i].
            while (!increasingStack.isEmpty()
                    && arr[increasingStack.peek()] > arr[i]) {
                increasingStack.pop();
            }

            // The top is the closest surviving previous value <= arr[i].
            previous[i] = increasingStack.isEmpty() ? -1 : increasingStack.peek();
            increasingStack.push(i);
        }

        return previous;
    }

    /**
     * Question 2: Find the next strictly smaller element for every index.
     *
     * Scan right to left so the stack contains only indices to the right of
     * i. Pop values greater than or equal to arr[i]: larger values are not
     * smaller, and equal values are deliberately excluded to make this side
     * strict. The remaining top is the nearest value < arr[i].
     *
     * If no candidate remains, arr.length is the exclusive right edge. With
     * Question 1's previous smaller-or-equal boundary, this tie rule assigns
     * subarrays with repeated minimums consistently.
     *
     * @param arr input values
     * @return next strictly smaller index for each position, or arr.length
     */
    public int[] nextSmallerElement(int[] arr) {
        int[] next = new int[arr.length];
        Deque<Integer> increasingStack = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            // Remove values that are not strictly smaller than arr[i].
            while (!increasingStack.isEmpty()
                    && arr[increasingStack.peek()] >= arr[i]) {
                increasingStack.pop();
            }

            // The top is the closest surviving value < arr[i].
            next[i] = increasingStack.isEmpty() ? arr.length : increasingStack.peek();
            increasingStack.push(i);
        }

        return next;
    }

    /**
     * Question 3: Find the previous greater-or-equal element for every index.
     *
     * Scan left to right. Pop values smaller than arr[i], because they cannot
     * be the previous greater-or-equal boundary. The top that remains is the
     * closest previous value >= arr[i].
     *
     * Equal values stay on the stack, mirroring Question 1's inclusive
     * previous boundary. The candidate values are nonincreasing from bottom
     * to top. If no candidate remains, -1 marks the left edge.
     *
     * @param arr input values
     * @return previous greater-or-equal index for each position, or -1
     */
    public int[] prevGreaterElement(int[] arr) {
        int[] previous = new int[arr.length];
        Deque<Integer> decreasingStack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            // Smaller values cannot be the nearest value >= arr[i].
            while (!decreasingStack.isEmpty()
                    && arr[decreasingStack.peek()] < arr[i]) {
                decreasingStack.pop();
            }

            // The top is the closest surviving previous value >= arr[i].
            previous[i] = decreasingStack.isEmpty() ? -1 : decreasingStack.peek();
            decreasingStack.push(i);
        }

        return previous;
    }

    /**
     * Question 4: Find the next strictly greater element for every index.
     *
     * Scan right to left. Pop values less than or equal to arr[i], because
     * this boundary must be strictly greater. The remaining top is the
     * nearest value > arr[i].
     *
     * Equal values are removed on this side to pair with Question 3's
     * inclusive previous boundary. This consistently assigns subarrays with
     * repeated maximums. If no candidate remains, arr.length marks the right
     * edge. Candidate values are nonincreasing from bottom to top.
     *
     * @param arr input values
     * @return next strictly greater index for each position, or arr.length
     */
    public int[] nextGreaterElement(int[] arr) {
        int[] next = new int[arr.length];
        Deque<Integer> decreasingStack = new ArrayDeque<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            // Remove values that are not strictly greater than arr[i].
            while (!decreasingStack.isEmpty()
                    && arr[decreasingStack.peek()] <= arr[i]) {
                decreasingStack.pop();
            }

            // The top is the closest surviving value > arr[i].
            next[i] = decreasingStack.isEmpty() ? arr.length : decreasingStack.peek();
            decreasingStack.push(i);
        }

        return next;
    }
}
