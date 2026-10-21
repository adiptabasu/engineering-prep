
/**
 * LeetCode 2390: Removing Stars From a String
 *
 * Problem:
 * https://leetcode.com/problems/removing-stars-from-a-string/
 *
 * Approach: StringBuilder as a Stack
 *
 * 1. Traverse the input string from left to right.
 * 2. For an ordinary character, append it to the StringBuilder.
 * 3. For '*', remove the most recently appended character,
 *    if one exists.
 * 4. Return the remaining characters in their original order.
 *
 * StringBuilder operations:
 * - append() acts like stack push.
 * - deleteCharAt(length - 1) acts like stack pop.
 *
 * Time Complexity: O(n), where n is the input string length.
 * Each character is processed once, and deleting the last
 * character takes constant time.
 *
 * Space Complexity: O(n) for the StringBuilder.
 */
public class RemovingStarsFromString {

    public String removeStars(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '*') {
                // Remove the closest surviving character to the left.
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                // Preserve ordinary characters.
                result.append(current);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        RemovingStarsFromString solution =
            new RemovingStarsFromString();

        System.out.println(
            solution.removeStars("leet**cod*e")
        ); // Expected: "lecoe"

        System.out.println(
            solution.removeStars("erase*****")
        ); // Expected: ""

        System.out.println(
            solution.removeStars("ab**c*")
        ); // Expected: ""

        System.out.println(
            solution.removeStars("abc")
        ); // Expected: "abc"

        System.out.println(
            solution.removeStars("a*b*c")
        ); // Expected: "c"
    }
}

