/*
 * Problem: Longest Substring Without Repeating Characters
 *
 * LeetCode:
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Difficulty: Medium
 *
 * Pattern:
 * Sliding Window + HashMap
 *
 * -------------------------------------------------------------------------
 * LEARNING NOTE
 * -------------------------------------------------------------------------
 *
 * This problem was intentionally difficult for me because Sliding Window
 * was a new algorithmic pattern.
 *
 * Important concepts that needed extra attention:
 *
 * 1. Understanding what the "window" represents.
 * 2. Understanding that right expands the window.
 * 3. Understanding that left shrinks the window.
 * 4. Maintaining the invariant that the current window is valid.
 * 5. Understanding why left must NEVER move backwards.
 * 6. Understanding why we need:
 *
 *        left = Math.max(left, previousIndex + 1);
 *
 *    rather than simply:
 *
 *        left = previousIndex + 1;
 *
 * This problem should be revisited later with additional Sliding Window
 * problems so that the pattern becomes intuitive rather than memorized.
 *
 * -------------------------------------------------------------------------
 *
 * APPROACH
 * -------------------------------------------------------------------------
 *
 * We maintain a sliding window:
 *
 *        [left ........ right]
 *
 * The window always represents the current substring being considered.
 *
 * The condition for this problem is:
 *
 *        The window must contain no duplicate characters.
 *
 * We use a HashMap to store the most recent index at which each character
 * was seen.
 *
 * When we encounter a character that has appeared before:
 *
 *        previousIndex = last position of this character
 *
 * The window must start after that previous occurrence:
 *
 *        left = previousIndex + 1
 *
 * However, the previous occurrence may already be outside our current
 * window. Therefore, left must never move backwards:
 *
 *        left = Math.max(left, previousIndex + 1);
 *
 * After processing the character, right moves forward.
 *
 * -------------------------------------------------------------------------
 *
 * WHY Math.max() IS IMPORTANT
 * -------------------------------------------------------------------------
 *
 * Consider:
 *
 *        "abba"
 *
 * Index:
 *
 *         0 1 2 3
 *         a b b a
 *
 * When we encounter the second 'b':
 *
 *        previousIndex = 1
 *        left = 0
 *
 * Therefore:
 *
 *        left = max(0, 1 + 1)
 *             = 2
 *
 * Current valid window:
 *
 *        "b"
 *
 * Now we encounter the final 'a'.
 *
 * The previous 'a' was at index 0.
 *
 *        previousIndex = 0
 *        left = 2
 *
 * If we simply did:
 *
 *        left = previousIndex + 1
 *             = 1
 *
 * we would move left BACKWARDS from 2 to 1.
 *
 * That is invalid because the old 'a' at index 0 is already outside
 * the current window.
 *
 * Therefore:
 *
 *        left = max(2, 0 + 1)
 *             = 2
 *
 * left only moves forward.
 *
 * -------------------------------------------------------------------------
 *
 * SLIDING WINDOW INVARIANT
 * -------------------------------------------------------------------------
 *
 * At every point:
 *
 *        [left ... right]
 *
 * represents a valid window with no duplicate characters.
 *
 * right:
 *        Expands the window by processing a new character.
 *
 * left:
 *        Shrinks the window when necessary.
 *
 * Important:
 *
 *        left can move forward.
 *        left can NEVER move backwards.
 *
 * -------------------------------------------------------------------------
 *
 * COMPLEXITY
 * -------------------------------------------------------------------------
 *
 * Time:
 *        O(n)
 *
 * Each character is processed once, and both pointers only move forward.
 *
 * Space:
 *        O(min(n, k))
 *
 * where k is the size of the character set.
 *
 * -------------------------------------------------------------------------
 *
 * BRUTE FORCE
 * -------------------------------------------------------------------------
 *
 * A brute-force solution could generate every possible substring and check
 * whether it contains duplicate characters.
 *
 * This would require substantially more work and can reach O(n^2) or
 * O(n^3) depending on how the substring and uniqueness checks are done.
 *
 * The sliding-window solution reduces this to O(n).
 *
 * -------------------------------------------------------------------------
 *
 * COMMON MISTAKES
 * -------------------------------------------------------------------------
 *
 * 1. Moving left only one position when a duplicate is found.
 *
 * 2. Moving left backwards.
 *
 * 3. Forgetting that a previous occurrence may already be outside
 *    the current window.
 *
 * 4. Forgetting to advance right after processing a character.
 *
 * 5. Calculating the window length incorrectly.
 *
 *    Since right represents the current character:
 *
 *        window length = right - left + 1
 *
 *    If right is incremented first, the equivalent expression is:
 *
 *        right - left
 *
 * 6. Initializing maxSize to 1, which fails for an empty string.
 *
 * -------------------------------------------------------------------------
 *
 * INTERVIEW FOLLOW-UPS
 * -------------------------------------------------------------------------
 *
 * 1. Can this be implemented using a HashSet?
 *
 *    Yes. When a duplicate is encountered, remove characters from the
 *    left until the duplicate disappears.
 *
 * 2. Can we avoid HashMap<Character, Integer>?
 *
 *    If the character set is known and bounded, an array can be used
 *    to store the last-seen index.
 *
 * 3. Can this pattern be used for arrays instead of strings?
 *
 *    Yes. Sliding Window is a general pattern and is not limited to
 *    characters or strings.
 *
 * 4. What if the window has a different validity condition?
 *
 *    The same expand/shrink pattern can often be used, with a different
 *    data structure or condition to determine whether the window is valid.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters
{
    /*
     * Returns the length of the longest substring containing
     * no repeated characters.
     */
    public static int lengthOfLongestSubstring(String s)
    {
        int maxSize = 0;

        /*
         * Stores the most recent index of every character.
         *
         * Example:
         *
         *    a -> 3
         *    b -> 5
         *    c -> 7
         */
        Map<Character, Integer> characterIndexMap = new HashMap<>();

        /*
         * left  = beginning of current window
         * right = character currently being processed
         */
        int left = 0;
        int right = 0;

        while (right < s.length())
        {
            char currentChar = s.charAt(right);

            /*
             * Find the previous occurrence of the current character.
             *
             * -1 means that the character has never been seen before.
             */
            int characterPos =
                    characterIndexMap.getOrDefault(currentChar, -1);

            /*
             * If the character has appeared before, we need to make
             * sure the previous occurrence is no longer inside our
             * current window.
             *
             * left must NEVER move backwards.
             *
             * Example:
             *
             * left = 4
             * previousIndex = 1
             *
             * Math.max(4, 2) = 4
             *
             * The previous occurrence is already outside the window,
             * so left does not move.
             */
            if (characterPos >= 0)
            {
                left = Math.max(left, characterPos + 1);
            }

            /*
             * Record the current/latest position of the character.
             */
            characterIndexMap.put(currentChar, right);

            /*
             * Since right currently points at the current character,
             * the window length is:
             *
             *        right - left + 1
             */
            int size = right - left + 1;

            /*
             * Keep the largest valid window seen so far.
             */
            maxSize = Math.max(maxSize, size);

            /*
             * Expand the window by moving right forward.
             */
            right++;
        }

        return maxSize;
    }

    public static void main(String[] args)
    {
        System.out.println(
                lengthOfLongestSubstring("")
        ); // 0

        System.out.println(
                lengthOfLongestSubstring("a")
        ); // 1

        System.out.println(
                lengthOfLongestSubstring("aa")
        ); // 1

        System.out.println(
                lengthOfLongestSubstring("abc")
        ); // 3

        System.out.println(
                lengthOfLongestSubstring("abcabcbb")
        ); // 3

        System.out.println(
                lengthOfLongestSubstring("bbbbb")
        ); // 1

        System.out.println(
                lengthOfLongestSubstring("pwwkew")
        ); // 3

        /*
         * Important test case for understanding Math.max().
         */
        System.out.println(
                lengthOfLongestSubstring("abba")
        ); // 2

        /*
         * Larger test case where a previous occurrence can already
         * be outside the current window.
         */
        System.out.println(
                lengthOfLongestSubstring("abcadefghijab")
        ); // 10
    }
}
