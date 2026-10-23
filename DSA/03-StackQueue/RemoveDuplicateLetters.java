import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 316: Remove Duplicate Letters
 * https://leetcode.com/problems/remove-duplicate-letters/
 *
 * Approach: Greedy + Monotonic Stack
 *
 * Goal:
 * Return the lexicographically smallest subsequence containing
 * each distinct character exactly once.
 *
 * Time Complexity: O(n)
 * Auxiliary Space Complexity: O(1) for the fixed 26-letter alphabet.
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        // Count the total occurrences of each character.
        int[] characterCount = new int[26];
        finalizeCharacterCount(s, characterCount);

        // Tracks characters currently present in the stack.
        Set<Character> characterSet = new HashSet<>();

        // Maintains the selected characters in order.
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // After decrementing, the count represents occurrences
            // remaining after the current position.
            characterCount[ch - 'a']--;

            // Each distinct character must appear only once.
            if (characterSet.contains(ch)) {
                continue;
            }

            /*
             * Remove a larger character when:
             * 1. The stack is not empty.
             * 2. The top character is lexicographically greater.
             * 3. The top character occurs again later.
             *
             * The third condition ensures we do not lose a character
             * whose last occurrence has already been processed.
             */
            while (!stack.isEmpty()
                    && stack.peek() > ch
                    && characterCount[stack.peek() - 'a'] > 0) {

                characterSet.remove(stack.pop());
            }

            // Add the current character to both structures.
            stack.push(ch);
            characterSet.add(ch);
        }

        // Popping produces reverse order because push() adds to the top.
        StringBuilder output = new StringBuilder();

        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.reverse().toString();
    }

    /**
     * Counts occurrences of each lowercase English letter.
     */
    private void finalizeCharacterCount(String s, int[] characterCount) {
        for (int i = 0; i < s.length(); i++) {
            characterCount[s.charAt(i) - 'a']++;
        }
    }

    /**
     * Local reference tests.
     */
    public static void main(String[] args) {
        RemoveDuplicateLetters solution = new RemoveDuplicateLetters();

        System.out.println(solution.removeDuplicateLetters("bcabc"));
        // Expected: abc

        System.out.println(solution.removeDuplicateLetters("cbacdcbc"));
        // Expected: acdb

        System.out.println(solution.removeDuplicateLetters("abacb"));
        // Expected: abc

        System.out.println(solution.removeDuplicateLetters("aaaa"));
        // Expected: a

        System.out.println(solution.removeDuplicateLetters("bbcaac"));
        // Expected: bac
    }
}
