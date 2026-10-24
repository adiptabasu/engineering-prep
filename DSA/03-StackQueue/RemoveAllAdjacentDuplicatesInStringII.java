
/*
 * LeetCode 1209: Remove All Adjacent Duplicates in String II
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * Approach:
 * - Use a stack of characters and a parallel stack of counts.
 * - Each character stack entry represents a character in the
 *   processed string.
 * - The corresponding count stores the consecutive run length
 *   ending at that character.
 * - When the count reaches k, remove the entire group.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution
{
    public String removeDuplicates(String s, int k)
    {
        Deque<Character> characterStack = new ArrayDeque<>();
        Deque<Integer> characterCountStack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++)
        {
            char currentCharacter = s.charAt(i);
            int currentCharacterCount = 1;

            if (!characterStack.isEmpty()
                    && characterStack.peek() == currentCharacter)
            {
                currentCharacterCount =
                        characterCountStack.peek() + 1;
            }

            if (currentCharacterCount == k)
            {
                // Remove the previous k - 1 characters.
                // The current character has not been pushed yet.
                for (int j = 1; j < k; j++)
                {
                    characterStack.pop();
                    characterCountStack.pop();
                }
            }
            else
            {
                characterStack.push(currentCharacter);
                characterCountStack.push(currentCharacterCount);
            }
        }

        StringBuilder output = new StringBuilder();

        while (!characterStack.isEmpty())
        {
            output.append(characterStack.pop());
        }

        return output.reverse().toString();
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();

        System.out.println(
                solution.removeDuplicates("abcd", 2)); // Expected: abcd

        System.out.println(
                solution.removeDuplicates("deeedbbcccbdaa", 3)); // Expected: aa

        System.out.println(
                solution.removeDuplicates("pbbcggttciiippooaais", 2)); // Expected: ps

        System.out.println(
                solution.removeDuplicates("aaa", 3)); // Expected: ""

        System.out.println(
                solution.removeDuplicates("aaabbb", 3)); // Expected: ""

        System.out.println(
                solution.removeDuplicates("aa", 3)); // Expected: aa

        System.out.println(
                solution.removeDuplicates("aabb", 3)); // Expected: aabb
    }
}

