import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 20: Valid Parentheses
 *
 * Problem:
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Given a string containing only the characters '(', ')', '{', '}',
 * '[' and ']', determine whether the input string is valid.
 *
 * A string is valid if:
 * 1. Every opening bracket has a corresponding closing bracket.
 * 2. Brackets are closed in the correct order.
 * 3. Every closing bracket matches the most recent unmatched opening bracket.
 *
 * Examples:
 * Input:  "()"
 * Output: true
 *
 * Input:  "()[]{}"
 * Output: true
 *
 * Input:  "(]"
 * Output: false
 *
 * Input:  "([)]"
 * Output: false
 *
 * Input:  "([])"
 * Output: true
 *
 * Approach: Stack + HashMap
 *
 * Key Insight:
 * A closing bracket must match the most recently encountered opening
 * bracket that has not yet been matched.
 *
 * This is a Last-In, First-Out (LIFO) requirement, making a stack
 * the natural data structure for this problem.
 *
 * Algorithm:
 * 1. Create a stack to store opening brackets.
 * 2. Create a map that associates each closing bracket with its
 *    corresponding opening bracket.
 * 3. Iterate through the input string:
 *    a. If the character is a closing bracket:
 *       - If the stack is empty, return false.
 *       - Pop the most recent opening bracket.
 *       - If it doesn't match the expected opening bracket, return false.
 *    b. Otherwise, push the character onto the stack.
 * 4. After processing all characters, return true only if the stack
 *    is empty. Any remaining opening brackets are unmatched.
 *
 * Why It Works:
 * The stack always holds unmatched opening brackets in the order
 * they were encountered.
 *
 * When a closing bracket appears, only the stack's top can be matched.
 * If the types don't match, the bracket ordering is invalid.
 *
 * If the stack is empty at the end, every opening bracket was matched.
 *
 * Complexity Analysis:
 * Let n be the length of the input string.
 *
 * Time Complexity: O(n)
 * Each character is processed once. Stack operations and average-case
 * HashMap lookups take O(1) time.
 *
 * Auxiliary Space Complexity: O(n)
 * In the worst case, the stack stores all n opening brackets.
 * The HashMap contains only three entries, requiring O(1) space.
 *
 * Common Mistakes:
 * 1. Calling pop() without checking whether the stack is empty.
 *    This can throw NoSuchElementException.
 *
 * 2. Returning true immediately after processing the string.
 *    The stack must be empty; otherwise, opening brackets remain unmatched.
 *
 * 3. Checking whether a matching opening bracket exists anywhere in
 *    the stack rather than checking the top element.
 *    Correct nesting requires LIFO ordering.
 *
 * 4. Comparing bracket values incorrectly or reversing the map's direction.
 *
 * 5. Forgetting that peek() inspects an element without removing it,
 *    whereas pop() removes and returns the top element.
 *
 * Important Edge Cases:
 * - Empty string: valid.
 * - Only opening brackets: invalid.
 * - Only closing brackets: invalid.
 * - Incorrect bracket types: invalid.
 * - Correctly nested brackets: valid.
 *
 * Note:
 * The LeetCode problem guarantees that the string contains only
 * bracket characters. If arbitrary characters were allowed, opening
 * brackets would need to be identified explicitly.
 */
public class ValidParentheses {

    /**
     * Determines whether a string contains valid bracket sequences.
     *
     * @param s input string containing bracket characters
     * @return true if the brackets are valid; false otherwise
     */
    public boolean isValid(String s) {

        // Stores opening brackets that have not yet been matched.
        Deque<Character> bracketStack = new ArrayDeque<>();

        // Maps each closing bracket to its corresponding opening bracket.
        Map<Character, Character> bracketPair = new HashMap<>();
        bracketPair.put(')', '(');
        bracketPair.put('}', '{');
        bracketPair.put(']', '[');

        // Process the string from left to right.
        for (int i = 0; i < s.length(); i++) {

            char bracket = s.charAt(i);

            // A key in the map indicates that the current character
            // is a closing bracket.
            if (bracketPair.containsKey(bracket)) {

                // A closing bracket cannot be valid if there is no
                // unmatched opening bracket available.
                if (bracketStack.isEmpty()) {
                    return false;
                }

                // Retrieve and remove the most recent unmatched
                // opening bracket.
                char latestOpenBracket = bracketStack.pop();

                // The most recent opening bracket must match
                // the current closing bracket.
                if (bracketPair.get(bracket) != latestOpenBracket) {
                    return false;
                }

            } else {

                // Opening bracket: save it for a future match.
                bracketStack.push(bracket);
            }
        }

        // Every opening bracket must have been matched.
        // Any remaining element makes the string invalid.
        return bracketStack.isEmpty();
    }

    /**
     * Local test cases.
     *
     * Run this main() method to verify the solution without LeetCode.
     */
    public static void main(String[] args) {

        ValidParentheses solution = new ValidParentheses();

        String[] testCases = {
            "",
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            "([])",
            "(((",
            ")))",
            "{[()]}"
        };

        boolean[] expectedResults = {
            true,
            true,
            true,
            false,
            false,
            true,
            true,
            false,
            false,
            true
        };

        for (int i = 0; i < testCases.length; i++) {

            String input = testCases[i];
            boolean actual = solution.isValid(input);
            boolean expected = expectedResults[i];

            System.out.printf(
                "Input: %-10s | Expected: %-5b | Actual: %-5b | %s%n",
                "\"" + input + "\"",
                expected,
                actual,
                actual == expected ? "PASS" : "FAIL"
            );
        }
    }
}
