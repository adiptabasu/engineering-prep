import java.util.HashMap;
import java.util.Map;

/**
 * Valid Anagram
 *
 * LeetCode:
 * https://leetcode.com/problems/valid-anagram/
 *
 * Problem:
 * Given two strings s and t, return true if t is an anagram of s,
 * and false otherwise.
 *
 * Two strings are anagrams if they contain the same characters
 * with the same frequencies, regardless of order.
 *
 * Example:
 * s = "anagram"
 * t = "nagaram"
 *
 * Result: true
 *
 * s = "rat"
 * t = "car"
 *
 * Result: false
 *
 * ------------------------------------------------------------
 * Approach 1: HashMap
 * ------------------------------------------------------------
 *
 * Maintain:
 *
 *     frequency(s, character) - frequency(t, character)
 *
 * For every character:
 *
 *     +1 when it appears in s
 *     -1 when it appears in t
 *
 * At the end, every frequency must be exactly 0.
 *
 * This approach works for a general character set.
 *
 * Time:  O(n) expected
 * Space: O(k), where k is the number of distinct characters
 *
 * ------------------------------------------------------------
 * Approach 2: int[26]
 * ------------------------------------------------------------
 *
 * If the input is restricted to lowercase English letters ('a' to 'z'),
 * we can use a fixed-size array of 26 integers instead of a HashMap.
 *
 * Character-to-index mapping:
 *
 *     'a' - 'a' = 0
 *     'b' - 'a' = 1
 *     ...
 *     'z' - 'a' = 25
 *
 * Increment the frequency for characters in s and decrement it
 * for characters in t.
 *
 * At the end, every element must be 0.
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * Why O(1) space?
 * The array always contains exactly 26 elements, regardless
 * of the size of the input strings.
 *
 * ------------------------------------------------------------
 * Important Constraint
 * ------------------------------------------------------------
 *
 * The int[26] approach only works when characters are guaranteed
 * to be lowercase English letters.
 *
 * If the character set is not restricted, use the HashMap approach
 * or another appropriate frequency-counting structure.
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Checking characters at the same positions.
 *    Anagrams do not need to have the same character ordering.
 *
 * 2. Forgetting the length check.
 *    Strings with different lengths cannot be anagrams.
 *
 * 3. Checking only whether characters exist.
 *    Frequencies matter. "aab" and "abb" are not anagrams.
 *
 * 4. Using int[26] when the input is not restricted to 'a' - 'z'.
 *
 * ------------------------------------------------------------
 * Interview Takeaway
 * ------------------------------------------------------------
 *
 * The important insight is that an anagram is fundamentally a
 * frequency comparison problem.
 *
 * Start with the general frequency-counting idea, then use the
 * input constraints to choose the most efficient data structure.
 */
public class ValidAnagram
{
    /**
     * HashMap-based solution.
     *
     * Works with a general character set.
     */
    public static boolean isAnagramMap(String s, String t)
    {
        if(s.length() != t.length())
            return false;

        Map<Character, Integer> hist = new HashMap<>();

        for(int i = 0; i < s.length(); i++)
        {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            hist.put(charS, hist.getOrDefault(charS, 0) + 1);
            hist.put(charT, hist.getOrDefault(charT, 0) - 1);
        }

        for(Map.Entry<Character, Integer> data : hist.entrySet())
        {
            if(data.getValue() != 0)
                return false;
        }

        return true;
    }

    /**
     * Optimized solution for lowercase English letters.
     */
    public static boolean isAnagramArr(String s, String t)
    {
        if(s.length() != t.length())
            return false;

        int[] frequency = new int[26];

        for(int i = 0; i < s.length(); i++)
        {
            frequency[s.charAt(i) - 'a']++;
            frequency[t.charAt(i) - 'a']--;
        }

        for(int count : frequency)
        {
            if(count != 0)
                return false;
        }

        return true;
    }

    /**
     * Local tests.
     */
    public static void main(String[] args)
    {
        // Basic anagram
        System.out.println(isAnagramMap("anagram", "nagaram")); // true
        System.out.println(isAnagramArr("anagram", "nagaram")); // true

        // Not an anagram
        System.out.println(isAnagramMap("rat", "car")); // false
        System.out.println(isAnagramArr("rat", "car")); // false

        // Different lengths
        System.out.println(isAnagramMap("a", "ab")); // false
        System.out.println(isAnagramArr("a", "ab")); // false

        // Repeated characters
        System.out.println(isAnagramMap("aabbcc", "ccbbaa")); // true
        System.out.println(isAnagramArr("aabbcc", "ccbbaa")); // true

        // Same characters but different frequencies
        System.out.println(isAnagramMap("aab", "abb")); // false
        System.out.println(isAnagramArr("aab", "abb")); // false

        // Empty strings
        System.out.println(isAnagramMap("", "")); // true
        System.out.println(isAnagramArr("", "")); // true
    }
}
