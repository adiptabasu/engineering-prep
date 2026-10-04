import java.util.*;

/**
 * Longest Repeating Character Replacement
 *
 * LeetCode:
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * Problem:
 * Given a string containing only uppercase English letters and an integer k,
 * return the length of the longest substring that can be transformed into
 * a substring containing only one distinct character by replacing at most k
 * characters.
 *
 * Example:
 * s = "ABAB", k = 2
 * Answer = 4
 *
 * s = "AABABBA", k = 1
 * Answer = 4
 *
 *
 * ============================================================
 * BRUTE FORCE
 * ============================================================
 *
 * Consider every possible substring.
 *
 * For each substring:
 *
 *     1. Count the frequency of every character.
 *     2. Find the most frequent character.
 *     3. Calculate how many replacements are required.
 *
 * Number of replacements required:
 *
 *     windowLength - frequencyOfMostFrequentCharacter
 *
 * If replacements <= k, the substring is valid.
 *
 * This approach is O(n^2) or worse depending on how the frequencies
 * are calculated.
 *
 *
 * ============================================================
 * OPTIMIZED APPROACH — VARIABLE SIZE SLIDING WINDOW
 * ============================================================
 *
 * We maintain a window:
 *
 *     [left ........ right]
 *
 * The window represents the substring we are currently considering.
 *
 * For the window to become all one character, we keep the most frequent
 * character and replace all the other characters.
 *
 * Therefore:
 *
 *     replacementsRequired
 *         = windowLength - maxFrequency
 *
 * The window is valid when:
 *
 *     windowLength - maxFrequency <= k
 *
 * If:
 *
 *     windowLength - maxFrequency > k
 *
 * the window is invalid, so we move left forward.
 *
 *
 * ============================================================
 * KEY INSIGHT
 * ============================================================
 *
 * We do NOT need to know which character will ultimately fill the window.
 *
 * We only need to know the frequency of the most frequent character.
 *
 * Example:
 *
 *     A A B A B
 *
 * Frequencies:
 *
 *     A -> 3
 *     B -> 2
 *
 * Window length = 5
 * Most frequent = 3
 *
 * Replacements required:
 *
 *     5 - 3 = 2
 *
 * Therefore k must be at least 2 for this window to be valid.
 *
 *
 * ============================================================
 * WHY maxFrequency CAN BE STALE
 * ============================================================
 *
 * This is the subtle part of this problem.
 *
 * When we shrink the window, we decrease a character's frequency.
 *
 * However, we do NOT decrease maxFrequency.
 *
 * Example:
 *
 *     maxFrequency = 5
 *
 * Later, after moving left, the actual frequency of that character
 * might become 4.
 *
 * maxFrequency still says 5.
 *
 * Why is this safe?
 *
 * Because maxFrequency represents the highest frequency we have seen
 * while expanding the window.
 *
 * We are interested only in finding the maximum possible window length.
 *
 * A stale maxFrequency may temporarily make a window appear more
 * favorable than it actually is, but it cannot cause us to record an
 * answer larger than a genuinely achievable maximum.
 *
 * More importantly, because the window only grows by one character
 * at a time, we only need to shrink once when the condition becomes
 * invalid.
 *
 * This allows the algorithm to remain O(n).
 *
 *
 * ============================================================
 * SLIDING WINDOW INVARIANT
 * ============================================================
 *
 * For the current window:
 *
 *     windowLength = right - left + 1
 *
 *     replacementsRequired =
 *         windowLength - maxFrequency
 *
 * Valid:
 *
 *     replacementsRequired <= k
 *
 * Invalid:
 *
 *     replacementsRequired > k
 *
 *
 * ============================================================
 * ALGORITHM
 * ============================================================
 *
 * 1. Create a frequency array of size 26.
 *
 * 2. Expand the window by moving right.
 *
 * 3. Increment the frequency of s[right].
 *
 * 4. Update maxFrequency.
 *
 * 5. Calculate:
 *
 *        windowLength - maxFrequency
 *
 * 6. If this is greater than k:
 *
 *        decrement the frequency of s[left]
 *        move left forward
 *
 * 7. Record the largest window seen.
 *
 * 8. Continue until right reaches the end.
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time: O(n)
 *
 * Every character is:
 *
 *     - added to the window once
 *     - removed from the window at most once
 *
 * Therefore both pointers move only forward.
 *
 * Space: O(1)
 *
 * The frequency array always contains exactly 26 entries.
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Using:
 *
 *      characterFrequency[s.charAt(right)]
 *
 *    instead of:
 *
 *      characterFrequency[s.charAt(right) - 'A']
 *
 *    Java chars are not array indexes from 0 to 25.
 *
 *
 * 2. Using post-increment incorrectly:
 *
 *      int frequency = characterFrequency[index]++;
 *
 *    This stores the OLD frequency in frequency.
 *
 *    Instead:
 *
 *      characterFrequency[index]++;
 *      int frequency = characterFrequency[index];
 *
 *
 * 3. Using:
 *
 *      windowLength - maxFrequency < k
 *
 *    instead of <= k.
 *
 *    We are allowed to perform AT MOST k replacements.
 *
 *
 * 4. Recalculating the maximum frequency after every shrink.
 *
 *    It is unnecessary for this optimization.
 *
 *
 * 5. Forgetting that this is a variable-size Sliding Window.
 *
 *    The window grows with right and shrinks from left when the
 *    replacement requirement becomes too large.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * 1. What changes if the string contains arbitrary Unicode characters?
 *
 *    The fixed int[26] array would no longer be sufficient.
 *    A HashMap<Character, Integer> or another frequency structure
 *    would be appropriate.
 *
 * 2. Why isn't a PriorityQueue necessary?
 *
 *    There are only 26 possible uppercase English characters.
 *    A frequency array is simpler and gives O(1) access.
 *
 * 3. Why is this O(n) despite having a while/if that moves left?
 *
 *    Both left and right only move forward.
 *
 *    right moves at most n times.
 *    left moves at most n times.
 *
 *    Therefore the total number of pointer movements is O(n).
 *
 * 4. What is the difference between this and Permutation in String?
 *
 *    Permutation in String uses a FIXED-SIZE window.
 *
 *    Longest Repeating Character Replacement uses a
 *    VARIABLE-SIZE window.
 *
 *
 * ============================================================
 * LEARNING NOTE
 * ============================================================
 *
 * This problem is particularly important because it combines several
 * Sliding Window ideas:
 *
 *     1. Variable-size window
 *     2. Frequency counting
 *     3. Window validity condition
 *     4. Shrinking an invalid window
 *     5. Maintaining an incremental maximum
 *
 * The most important formula to remember is:
 *
 *     replacementsRequired =
 *         windowLength - maxFrequency
 *
 * This is the pattern to recognize in future problems.
 */
public class LongestRepeatingCharacterReplacement
{
    public int characterReplacement(String s, int k)
    {
        int[] characterFrequency = new int[26];

        int maxFrequency = 0;
        int maxWindowLength = 0;

        int left = 0;
        int right = 0;

        while (right < s.length())
        {
            int index = s.charAt(right) - 'A';

            // Add the current character to the window.
            characterFrequency[index]++;

            // Keep track of the highest character frequency
            // encountered while expanding the window.
            maxFrequency = Math.max(
                    maxFrequency,
                    characterFrequency[index]
            );

            /*
             * Window length:
             *
             *     right - left + 1
             *
             * Minimum replacements required:
             *
             *     windowLength - maxFrequency
             *
             * If this exceeds k, the window is invalid.
             */
            if (right - left + 1 - maxFrequency > k)
            {
                characterFrequency[s.charAt(left) - 'A']--;
                left++;
            }

            /*
             * After the possible shrink, record the largest
             * window encountered.
             */
            maxWindowLength = Math.max(
                    maxWindowLength,
                    right - left + 1
            );

            right++;
        }

        return maxWindowLength;
    }

    public static void main(String[] args)
    {
        LongestRepeatingCharacterReplacement solution =
                new LongestRepeatingCharacterReplacement();

        System.out.println(
                solution.characterReplacement("ABAB", 2)
        ); // Expected: 4

        System.out.println(
                solution.characterReplacement("AABABBA", 1)
        ); // Expected: 4

        System.out.println(
                solution.characterReplacement("AAAA", 2)
        ); // Expected: 4

        System.out.println(
                solution.characterReplacement("ABCDE", 1)
        ); // Expected: 2

        System.out.println(
                solution.characterReplacement("A", 0)
        ); // Expected: 1

        System.out.println(
                solution.characterReplacement("ABBB", 2)
        ); // Expected: 4
    }
}
