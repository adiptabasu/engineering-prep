import java.util.Arrays;

/*
 * Problem:
 * Permutation in String
 *
 * LeetCode:
 * https://leetcode.com/problems/permutation-in-string/
 *
 *
 * ============================================================
 * PROBLEM
 * ============================================================
 *
 * Given two strings s1 and s2, return true if s2 contains a
 * permutation of s1.
 *
 * A permutation contains exactly the same characters with the
 * same frequencies, but the characters can appear in any order.
 *
 * Example:
 *
 *     s1 = "ab"
 *     s2 = "eidbaooo"
 *
 * "ba" is a permutation of "ab", so the answer is true.
 *
 *
 * ============================================================
 * LEARNING NOTE — FIXED-SIZE SLIDING WINDOW
 * ============================================================
 *
 * This problem reinforces the Fixed-Size Sliding Window pattern.
 *
 * The window size is ALWAYS:
 *
 *     s1.length()
 *
 * Why?
 *
 * A permutation of s1 must contain exactly the same number of
 * characters as s1.
 *
 * Therefore, if:
 *
 *     s1 = "ab"
 *
 * every possible candidate window in s2 must contain exactly
 * 2 characters.
 *
 *
 * ============================================================
 * KEY INSIGHT
 * ============================================================
 *
 * We don't care about the ORDER of characters.
 *
 * We only care about their FREQUENCY.
 *
 * Example:
 *
 *     s1 = "aab"
 *
 * Frequency:
 *
 *     a -> 2
 *     b -> 1
 *
 * The following are permutations:
 *
 *     "aab"
 *     "aba"
 *     "baa"
 *
 * Therefore, we maintain:
 *
 *     targetFreq -> frequency of characters in s1
 *
 *     windowFreq -> frequency of characters in the current
 *                   window of s2
 *
 * If the two frequency arrays are equal, we found a permutation.
 *
 *
 * ============================================================
 * WHY A FIXED WINDOW?
 * ============================================================
 *
 * Suppose:
 *
 *     s1.length() = 3
 *
 * Then a valid permutation must have exactly 3 characters.
 *
 * Therefore:
 *
 *     window size < 3
 *         -> not enough characters
 *
 *     window size = 3
 *         -> possible candidate
 *
 *     window size > 3
 *         -> remove the leftmost character
 *
 *
 * ============================================================
 * SLIDING WINDOW
 * ============================================================
 *
 * Example:
 *
 *     s1 = "ab"
 *     s2 = "eidbaooo"
 *
 * Window size = 2
 *
 * Windows:
 *
 *     "ei"
 *     "id"
 *     "db"
 *     "ba"  <- permutation found
 *
 * Instead of rebuilding the frequency array for every window,
 * we maintain it incrementally.
 *
 * When a character enters:
 *
 *     windowFreq[character]++;
 *
 * When a character leaves:
 *
 *     windowFreq[character]--;
 *
 *
 * ============================================================
 * BRUTE FORCE APPROACH
 * ============================================================
 *
 * One possible approach:
 *
 *     1. Generate every substring of length s1.length().
 *     2. Sort the substring.
 *     3. Compare it with sorted(s1).
 *
 * If k = s1.length():
 *
 *     Sorting each window -> O(k log k)
 *     Number of windows -> O(n)
 *
 * Total:
 *
 *     O(n * k log k)
 *
 * This is unnecessary because we can maintain character
 * frequencies directly.
 *
 *
 * ============================================================
 * OPTIMIZED APPROACH
 * ============================================================
 *
 * Because the problem contains lowercase English letters,
 * we only need an array of size 26.
 *
 *     targetFreq = new int[26]
 *     windowFreq = new int[26]
 *
 * Build targetFreq once.
 *
 * Then slide the window across s2:
 *
 *     1. Add s2[right].
 *
 *     2. If window becomes larger than s1.length():
 *            remove s2[left]
 *            move left forward.
 *
 *     3. If window size == s1.length():
 *            compare the two frequency arrays.
 *
 *
 * ============================================================
 * WHY Arrays.equals() IS STILL O(1)
 * ============================================================
 *
 * Arrays.equals() compares the arrays element by element.
 *
 * Each array contains exactly 26 elements.
 *
 * Therefore:
 *
 *     O(26) = O(1)
 *
 * with respect to the input size.
 *
 * Overall:
 *
 *     O(n)
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Let n = s2.length().
 *
 * Time:
 *
 *     O(n)
 *
 * Building targetFreq:
 *
 *     O(s1.length())
 *
 * Sliding through s2:
 *
 *     O(n)
 *
 * Comparing two 26-element arrays:
 *
 *     O(26) = O(1)
 *
 * Overall:
 *
 *     O(n + s1.length())
 *
 * Usually written as:
 *
 *     O(n)
 *
 * Space:
 *
 *     O(26)
 *
 * which is:
 *
 *     O(1)
 *
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Using a variable-size window.
 *
 *    A permutation must contain exactly s1.length()
 *    characters.
 *
 *
 * 2. Forgetting to remove the character leaving the window.
 *
 *    When the window becomes too large:
 *
 *        windowFreq[s2.charAt(left) - 'a']--;
 *
 *
 * 3. Incrementing instead of decrementing the outgoing
 *    character's frequency.
 *
 *
 * 4. Comparing the characters themselves instead of their
 *    frequencies.
 *
 *    "ab" and "ba" are both valid permutations.
 *
 *
 * 5. Rebuilding the frequency array for every window.
 *
 *    This defeats the main advantage of Sliding Window.
 *
 *    Instead:
 *
 *        add the entering character
 *        remove the leaving character
 *
 *
 * 6. Using sorting unnecessarily.
 *
 *    Sorting works, but frequency counting is more efficient
 *    and demonstrates better Sliding Window thinking.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UP — MATCHES OPTIMIZATION
 * ============================================================
 *
 * The current implementation uses:
 *
 *     Arrays.equals(windowFreq, targetFreq)
 *
 * Since there are only 26 characters, this is already O(1).
 *
 * However, we can optimize the comparison conceptually by
 * maintaining:
 *
 *     int matches
 *
 * where matches represents how many of the 26 characters have
 * exactly the required frequency.
 *
 * Then:
 *
 *     matches == 26
 *
 * means the current window is a permutation.
 *
 * When one character enters or leaves the window, only that
 * character's frequency changes, so matches can be updated
 * in O(1).
 *
 * This doesn't change the Big-O complexity for this problem,
 * because 26 is already constant.
 *
 * The important interview lesson is:
 *
 *     Maintain changing state incrementally instead of
 *     repeatedly recomputing it.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * Q: Why does the Sliding Window have fixed size?
 *
 * A: A permutation of s1 must contain exactly the same number
 *    of characters as s1.
 *
 *
 * Q: Why can we use an int[26]?
 *
 * A: The problem specifies lowercase English letters, so there
 *    are only 26 possible characters.
 *
 *
 * Q: Why is Arrays.equals() effectively O(1)?
 *
 * A: It compares at most 26 elements, and 26 is a constant.
 *
 *
 * Q: Why not sort every window?
 *
 * A: Sorting every window costs O(k log k), whereas maintaining
 *    frequencies allows each window to be processed in O(1)
 *    apart from the constant 26-element comparison.
 *
 *
 * Q: Why is this Sliding Window?
 *
 * A: We maintain a contiguous section of s2 with a fixed size
 *    equal to s1.length(), adding one character and removing
 *    one character as the window moves.
 */
public class PermutationInString
{
    /*
     * Returns true if s2 contains a permutation of s1.
     */
    public static boolean checkInclusion(String s1, String s2)
    {
        /*
         * A permutation must have exactly the same character
         * frequencies as s1.
         */
        int[] targetFreq = new int[26];

        for (int i = 0; i < s1.length(); i++)
        {
            targetFreq[s1.charAt(i) - 'a']++;
        }

        int left = 0;

        /*
         * Frequency of characters in the current window
         * of s2.
         */
        int[] windowFreq = new int[26];

        /*
         * Expand the fixed-size window using right.
         */
        for (int right = 0; right < s2.length(); right++)
        {
            /*
             * Add the character entering the window.
             */
            windowFreq[s2.charAt(right) - 'a']++;

            /*
             * If the window becomes larger than s1,
             * remove the character leaving from the left.
             */
            if (right - left + 1 > s1.length())
            {
                windowFreq[s2.charAt(left) - 'a']--;
                left++;
            }

            /*
             * Once the window has exactly the same size
             * as s1, compare their frequency distributions.
             */
            if (right - left + 1 == s1.length())
            {
                if (Arrays.equals(windowFreq, targetFreq))
                {
                    return true;
                }
            }
        }

        return false;
    }


    /*
     * Local tests.
     */
    public static void main(String[] args)
    {
        System.out.println(
                checkInclusion("ab", "eidbaooo")
        ); // Expected: true

        System.out.println(
                checkInclusion("ab", "eidboaoo")
        ); // Expected: false

        System.out.println(
                checkInclusion("a", "a")
        ); // Expected: true

        System.out.println(
                checkInclusion("a", "b")
        ); // Expected: false

        System.out.println(
                checkInclusion("aabc", "eidcbaooo")
        ); // Expected: true
    }
}
