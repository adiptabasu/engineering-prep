
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 150: Evaluate Reverse Polish Notation
 *
 * Problem: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Approach:
 * 1. Traverse tokens from left to right.
 * 2. If a token is an integer, push it onto the stack.
 * 3. If a token is an operator, pop the right operand first,
 *    then pop the left operand.
 * 4. Perform the operation and push the result back onto the stack.
 * 5. After processing all tokens, the stack contains the final result.
 *
 * Time Complexity: O(n), where n is the number of tokens.
 * Each token is processed once, with constant-time stack operations.
 *
 * Space Complexity: O(n) in the worst case for the stack.
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            try {
                // Integer tokens, including negative numbers, are pushed.
                stack.push(Integer.parseInt(token));
            } catch (NumberFormatException ex) {
                // An operator requires two operands.
                // The first popped value is the RIGHT operand.
                int right = stack.pop();

                // The second popped value is the LEFT operand.
                int left = stack.pop();

                int result;

                switch (token) {
                    case "+":
                        result = left + right;
                        break;

                    case "-":
                        result = left - right;
                        break;

                    case "*":
                        result = left * right;
                        break;

                    case "/":
                        // Java integer division truncates toward zero.
                        result = left / right;
                        break;

                    default:
                        throw new IllegalArgumentException(
                            "Unsupported operator: " + token
                        );
                }

                // The intermediate result becomes an operand
                // for subsequent operations.
                stack.push(result);
            }
        }

        // A valid RPN expression leaves exactly one value.
        return stack.pop();
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation solution =
            new EvaluateReversePolishNotation();

        // Example 1: (2 + 1) * 3 = 9
        System.out.println(
            solution.evalRPN(new String[]{"2", "1", "+", "3", "*"})
        ); // Expected: 9

        // Example 2: 4 + (13 / 5) = 6
        System.out.println(
            solution.evalRPN(new String[]{"4", "13", "5", "/", "+"})
        ); // Expected: 6

        // Example 3: (4 + ((7 + 2) * 3)) - 6 = 25
        System.out.println(
            solution.evalRPN(
                new String[]{"4", "7", "2", "+", "3", "*", "+" , "6", "-"}
            )
        ); // Expected: 25

        // Example 4: 7 / -3 = -2 (truncation toward zero)
        System.out.println(
            solution.evalRPN(new String[]{"7", "-3", "/"})
        ); // Expected: -2

        // Example 5: 4 + (-13 / 5) = 2
        System.out.println(
            solution.evalRPN(new String[]{"4", "-13", "5", "/", "+"})
        ); // Expected: 2
    }
}

