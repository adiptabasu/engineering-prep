
/**
 * LeetCode 844: Backspace String Compare
 *
 * Problem:
 * https://leetcode.com/problems/backspace-string-compare/
 *
 * Approach: StringBuilder as a Stack
 *
 * 1. Process each string from left to right.
 * 2. For an ordinary character, append it to the StringBuilder.
 * 3. For '#', remove the last character if the builder is not empty.
 * 4. Compare the two processed strings.
 *
 * StringBuilder operations:
 * - append(): push a character.
 * - deleteCharAt(length - 1): pop a character.
 *
 * Time Complexity: O(n + m), where n and m are the lengths
 * of the two input strings.
 *
 * Space Complexity: O(n + m) for the processed strings.
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        return processedString(s).equals(processedString(t));
    }

    private String processedString(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (currentChar == '#') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        BackspaceStringCompare solution = new BackspaceStringCompare();

        System.out.println(
                solution.backspaceCompare("ab#c", "ad#c")); // true

        System.out.println(
                solution.backspaceCompare("a#c", "b")); // false

        System.out.println(
                solution.backspaceCompare("a##", "")); // true

        System.out.println(
                solution.backspaceCompare("xywrrmp", "xywrrmu#p")); // true

        System.out.println(
                solution.backspaceCompare("", "")); // true
    }
}

