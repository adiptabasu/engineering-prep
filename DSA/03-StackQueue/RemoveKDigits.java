
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 402: Remove K Digits
 * https://leetcode.com/problems/remove-k-digits/
 *
 * Approach: Greedy + Monotonic Stack
 *
 * Algorithm:
 * 1. Process each digit from left to right.
 * 2. While the stack is not empty, k > 0, and the current
 *    digit is smaller than the top digit, remove the top digit
 *    and decrement k.
 * 3. Push the current digit onto the stack.
 * 4. If removals remain after processing all digits, remove
 *    digits from the end of the stack.
 * 5. Reconstruct the result in its original order.
 * 6. Remove leading zeroes. If no digits remain, return "0".
 *
 * Time Complexity: O(n)
 * Each digit is pushed and popped at most once.
 *
 * Space Complexity: O(n)
 * The stack and result can each store up to n digits.
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        Deque<Integer> stack = new ArrayDeque<>();

        // Greedily remove larger preceding digits.
        for (int i = 0; i < num.length(); i++) {
            int value = num.charAt(i) - '0';

            while (!stack.isEmpty()
                    && k > 0
                    && value < stack.peek()) {
                stack.pop();
                k--;
            }

            stack.push(value);
        }

        // If removals remain, remove digits from the end.
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        // Reconstruct the number in its original order.
        StringBuilder output = new StringBuilder();

        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        output.reverse();

        // Remove leading zeroes.
        while (output.length() > 0 && output.charAt(0) == '0') {
            output.deleteCharAt(0);
        }

        return output.length() == 0 ? "0" : output.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits solution = new RemoveKDigits();

        // Standard examples
        System.out.println(
                solution.removeKdigits("1432219", 3)); // "1219"

        System.out.println(
                solution.removeKdigits("10200", 1));   // "200"

        System.out.println(
                solution.removeKdigits("10", 2));      // "0"

        // Increasing digits: remove from the end
        System.out.println(
                solution.removeKdigits("12345", 2));   // "123"

        // Leading zeroes after removal
        System.out.println(
                solution.removeKdigits("1002", 1));    // "2"

        // All remaining digits are zeroes
        System.out.println(
                solution.removeKdigits("1000", 1));    // "0"
    }
}

