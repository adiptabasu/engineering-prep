/**
 * LeetCode: Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Problem:
 * Given a string s, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * Example:
 *
 * Input:  "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Input:  "race a car"
 * Output: false
 *
 * Approach:
 *
 * Use two pointers:
 *
 *     left  -> starts at the beginning
 *     right -> starts at the end
 *
 * Move the pointers toward each other.
 *
 * - If the left character is not alphanumeric, skip it.
 * - If the right character is not alphanumeric, skip it.
 * - Otherwise compare the two characters ignoring case.
 * - If they differ, the string is not a palindrome.
 * - If they match, move both pointers inward.
 *
 * We do not create a cleaned version of the string.
 *
 * Key Insight:
 *
 * We only care about alphanumeric characters, so there is no need to
 * modify the original string or create another string.
 *
 * The two pointers allow us to compare the relevant characters directly
 * while skipping characters that should be ignored.
 *
 * Why It Works:
 *
 * A palindrome reads identically from both directions.
 *
 * Therefore, the first valid character from the left must match the first
 * valid character from the right, the second valid character from each
 * side must match, and so on.
 *
 * By moving the pointers inward after every successful comparison, we
 * verify every relevant character pair exactly once.
 *
 * Case Handling:
 *
 * Instead of converting the entire string to lowercase, we convert only
 * the two characters being compared:
 *
 *     Character.toLowerCase(leftChar)
 *     Character.toLowerCase(rightChar)
 *
 * This avoids creating an additional String.
 *
 * Complexity:
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * Common Mistakes:
 *
 * 1. Moving the right pointer in the wrong direction.
 *    The right pointer must move toward the beginning:
 *
 *        right--
 *
 * 2. Using left >= right as the loop condition.
 *    We continue while:
 *
 *        left < right
 *
 * 3. Forgetting to move both pointers after a successful comparison.
 *
 * 4. Comparing punctuation or spaces instead of skipping them.
 *
 * 5. Creating a cleaned string unnecessarily when O(1) auxiliary space
 *    can be achieved with two pointers.
 *
 * Interview Follow-ups:
 *
 * 1. Can you solve it without creating another string?
 *    Yes. Skip invalid characters directly with two pointers.
 *
 * 2. What is the complexity?
 *    O(n) time and O(1) auxiliary space.
 *
 * 3. Why don't we need to inspect every character more than once?
 *    Each pointer only moves forward toward the center, so the total
 *    number of pointer movements is O(n).
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        int leftPoint = 0;
        int rightPoint = s.length() - 1;

        while (leftPoint < rightPoint) {

            char leftChar = s.charAt(leftPoint);
            char rightChar = s.charAt(rightPoint);

            // Skip non-alphanumeric characters from the left.
            if (!Character.isLetterOrDigit(leftChar)) {
                leftPoint++;
            }

            // Skip non-alphanumeric characters from the right.
            else if (!Character.isLetterOrDigit(rightChar)) {
                rightPoint--;
            }

            // Both characters are alphanumeric.
            else {

                // Compare characters ignoring case.
                if (Character.toLowerCase(leftChar)
                        != Character.toLowerCase(rightChar)) {

                    return false;
                }

                // Characters matched, move both pointers inward.
                leftPoint++;
                rightPoint--;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        ValidPalindrome solution = new ValidPalindrome();

        test(solution, "A man, a plan, a canal: Panama");
        test(solution, "race a car");
        test(solution, " ");
        test(solution, "");
        test(solution, "a");
        test(solution, ".,");
        test(solution, "0P");
        test(solution, "Madam");
        test(solution, "No 'x' in Nixon");
    }

    private static void test(ValidPalindrome solution, String input) {

        boolean result = solution.isPalindrome(input);

        System.out.println(
                "Input:  \"" + input + "\""
                        + " -> Output: " + result
        );
    }
}
