import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group Anagrams
 *
 * LeetCode:
 * https://leetcode.com/problems/group-anagrams/
 *
 * Problem:
 * Given an array of strings, group the anagrams together.
 *
 * Two strings are anagrams if they contain the same characters
 * with the same frequencies, regardless of their order.
 *
 * Example:
 *
 * Input:
 * ["eat", "tea", "tan", "ate", "nat", "bat"]
 *
 * Output:
 * [
 *     ["eat", "tea", "ate"],
 *     ["tan", "nat"],
 *     ["bat"]
 * ]
 *
 * The order of the groups does not matter.
 *
 * ------------------------------------------------------------
 * Key Insight
 * ------------------------------------------------------------
 *
 * Every anagram has the same character-frequency distribution.
 *
 * Therefore, we can calculate a frequency signature for every
 * string and use that signature as the HashMap key.
 *
 * Example:
 *
 *     eat
 *     tea
 *     ate
 *
 * all produce the same frequency signature.
 *
 * The HashMap then becomes:
 *
 *     signature -> list of anagrams
 *
 * ------------------------------------------------------------
 * Approach
 * ------------------------------------------------------------
 *
 * For every string:
 *
 * 1. Create a frequency array of size 26.
 * 2. Increment the frequency for every character.
 * 3. Convert the frequency array into an unambiguous String.
 * 4. Use that String as the HashMap key.
 * 5. Add the original string to the corresponding group.
 *
 * Example:
 *
 *     "eat"
 *
 * frequency:
 *
 *     a = 1
 *     e = 1
 *     t = 1
 *
 * Signature:
 *
 *     "1,0,0,0,1,...,1,"
 *
 * "tea" and "ate" produce exactly the same signature.
 *
 * ------------------------------------------------------------
 * Why use a separator?
 * ------------------------------------------------------------
 *
 * We cannot simply concatenate the frequency values:
 *
 *     [1, 11] -> "111"
 *     [11, 1] -> "111"
 *
 * These would collide even though the distributions are different.
 *
 * Using a separator:
 *
 *     [1, 11] -> "1,11,"
 *     [11, 1] -> "11,1,"
 *
 * makes the representation unambiguous.
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 *
 * Let:
 *
 *     n = number of strings
 *     k = average length of each string
 *
 * For every string:
 *
 *     Build frequency array -> O(k)
 *     Build signature       -> O(26) = O(1)
 *
 * Therefore:
 *
 *     Time:  O(n * k)
 *
 * Space:
 *
 *     HashMap and result groups require storage for the strings.
 *     The frequency signature has fixed size because there are
 *     only 26 lowercase English characters.
 *
 *     Auxiliary space: O(n)
 *     Output space:    O(n * k)
 *
 * ------------------------------------------------------------
 * Constraint
 * ------------------------------------------------------------
 *
 * This implementation assumes that every character is a lowercase
 * English letter from 'a' to 'z'.
 *
 * If the character set is unrestricted, a different signature
 * strategy would be required.
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Using the original string as the HashMap key.
 *
 * 2. Using an int[] directly as a HashMap key.
 *
 *    Java arrays use identity-based equals() and hashCode(),
 *    so two arrays with identical contents are different keys.
 *
 * 3. Creating an ambiguous signature by simply concatenating
 *    frequency values without separators.
 *
 * 4. Sorting every string unnecessarily when a fixed alphabet
 *    allows O(k) frequency counting.
 *
 * ------------------------------------------------------------
 * Alternative Approach
 * ------------------------------------------------------------
 *
 * Another valid solution is to sort every string and use the
 * sorted string as the HashMap key.
 *
 * Example:
 *
 *     eat -> aet
 *     tea -> aet
 *     ate -> aet
 *
 * That approach has:
 *
 *     Time: O(n * k log k)
 *
 * compared with the frequency-signature approach:
 *
 *     Time: O(n * k)
 *
 * ------------------------------------------------------------
 * Interview Takeaway
 * ------------------------------------------------------------
 *
 * The important pattern is:
 *
 *     Find a canonical representation
 *     -> use it as a HashMap key
 *     -> group values sharing that representation.
 *
 * Group Anagrams is an important example of using a derived
 * representation as a HashMap key.
 */
public class GroupAnagrams
{
    /**
     * Groups strings using a frequency-based signature.
     */
    public static List<List<String>> groupAnagrams(String[] strs)
    {
        Map<String, List<String>> anagramGroup = new HashMap<>();

        for(String str : strs)
        {
            String signature = getSignature(str);

            List<String> group =
                    anagramGroup.getOrDefault(signature, new ArrayList<>());

            group.add(str);
            anagramGroup.put(signature, group);
        }

        List<List<String>> returnList = new ArrayList<>();

        for(Map.Entry<String, List<String>> data : anagramGroup.entrySet())
        {
            returnList.add(data.getValue());
        }

        return returnList;
    }

    /**
     * Creates a unique frequency-based signature.
     *
     * Assumes lowercase English letters.
     */
    private static String getSignature(String str)
    {
        StringBuilder sb = new StringBuilder();
        int[] frequencyArray = new int[26];

        for(int i = 0; i < str.length(); i++)
        {
            frequencyArray[str.charAt(i) - 'a']++;
        }

        for(int freqVal : frequencyArray)
        {
            sb.append(freqVal).append(',');
        }

        return sb.toString();
    }

    /**
     * Local tests.
     */
    public static void main(String[] args)
    {
        String[] test1 =
                {"eat", "tea", "tan", "ate", "nat", "bat"};

        String[] test2 =
                {""};

        String[] test3 =
                {"a"};

        String[] test4 =
                {"ab", "ba", "abc", "bca", "cab"};

        String[] test5 =
                {"aaaaaaaaaa", "aaaaaaaaab"};

        System.out.println("Test 1:");
        System.out.println(groupAnagrams(test1));

        System.out.println("\nTest 2:");
        System.out.println(groupAnagrams(test2));

        System.out.println("\nTest 3:");
        System.out.println(groupAnagrams(test3));

        System.out.println("\nTest 4:");
        System.out.println(groupAnagrams(test4));

        System.out.println("\nTest 5:");
        System.out.println(groupAnagrams(test5));
    }
}
