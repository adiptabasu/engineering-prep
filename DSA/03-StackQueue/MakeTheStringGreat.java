/**
 * LeetCode 1544: Make The String Great
 *
 * Problem:
 * https://leetcode.com/problems/make-the-string-great/
 *
 * Approach: StringBuilder as a Stack
 *
 * 1. Traverse the input string from left to right.
 * 2. If the builder is not empty, compare its last character
 *    with the current character.
 * 3. If both characters represent the same letter but have
 *    opposite cases, remove the last character from the builder.
 * 4. Otherwise, append the current character.
 * 5. Return the remaining characters.
 *
 * StringBuilder operations:
 * - charAt(length - 1): peek at the stack top.
 * - append(): push a character.
 * - deleteCharAt(length - 1): pop a character.
 *
 * Time Complexity: O(n), where n is the input string length.
 * Each character is appended at most once and removed at most once.
 *
 * Space Complexity: O(n) for the StringBuilder.
 */
public class MakeTheStringGreat {

    public String makeGood(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (result.length() > 0) {
                char previous = result.charAt(result.length() - 1);

                boolean sameLetter =
                        Character.toLowerCase(previous)
                        == Character.toLowerCase(current);

                boolean oppositeCase = previous != current;

                if (sameLetter && oppositeCase) {
                    result.deleteCharAt(result.length() - 1);
                    continue;
                }
            }

            result.append(current);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        MakeTheStringGreat solution = new MakeTheStringGreat();

        System.out.println(solution.makeGood("leEeetcode")); // "leetcode"
        System.out.println(solution.makeGood("abBA"));       // ""
        System.out.println(solution.makeGood("s"));          // "s"
        System.out.println(solution.makeGood("Pp"));         // ""
        System.out.println(solution.makeGood("aabAAB"));     // "aabAAB"
    }
}
