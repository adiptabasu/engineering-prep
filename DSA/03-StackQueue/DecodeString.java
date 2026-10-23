
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 394: Decode String
 *
 * Problem:
 * https://leetcode.com/problems/decode-string/
 *
 * Approach: StringBuilder + Stack of Repeat Counts
 *
 * 1. Accumulate digits to construct a repetition count.
 * 2. On '[', push the repetition count and reset it.
 * 3. On ']', collect the substring back to the matching '['.
 * 4. Reverse the collected substring, repeat it the required
 *    number of times, and append the result.
 *
 * Time Complexity: O(n + L), where n is the encoded input
 * length and L is the length of the decoded output.
 *
 * Space Complexity: O(n + L) for the builder, repeat-count
 * stack, and decoded output.
 */
public class DecodeString {

    public String decodeString(String s) {
        StringBuilder output = new StringBuilder();
        Deque<Integer> repeatCount = new ArrayDeque<>();
        int repeatRunningValue = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                repeatRunningValue =
                        (repeatRunningValue * 10)
                        + Character.getNumericValue(ch);
            } else if (ch == '[') {
                repeatCount.push(repeatRunningValue);
                repeatRunningValue = 0;
                output.append(ch);
            } else if (ch == ']') {
                StringBuilder partInBracket = new StringBuilder();

                while (output.charAt(output.length() - 1) != '[') {
                    partInBracket.append(
                            output.charAt(output.length() - 1));
                    output.deleteCharAt(output.length() - 1);
                }

                output.deleteCharAt(output.length() - 1);

                output.append(
                        partInBracket.reverse()
                                .toString()
                                .repeat(repeatCount.pop()));
            } else {
                output.append(ch);
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        DecodeString solution = new DecodeString();

        System.out.println(
                solution.decodeString("3[a]2[bc]")); // "aaabcbc"

        System.out.println(
                solution.decodeString("3[a2[c]]")); // "accaccacc"

        System.out.println(
                solution.decodeString("2[abc]3[cd]ef")); // "abcabccdcdcdef"

        System.out.println(
                solution.decodeString("10[a]")); // "aaaaaaaaaa"
    }
}

