/**
 * LeetCode: Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Problem:
 * Given the head of a singly linked list, determine whether the list
 * is a palindrome.
 *
 * A palindrome reads the same forwards and backwards.
 *
 * Example:
 *
 * 1 -> 2 -> 2 -> 1
 *          palindrome
 *
 * 1 -> 2 -> 3 -> 2 -> 1
 *              palindrome
 *
 * 1 -> 2 -> 3
 *          not a palindrome
 *
 * ------------------------------------------------------------
 * Brute Force Approach:
 * ------------------------------------------------------------
 *
 * Traverse the linked list and store every value in an ArrayList.
 *
 * Then use two pointers:
 *
 * left  -> beginning of the array
 * right -> end of the array
 *
 * Compare values while moving towards the center.
 *
 * Time:  O(n)
 * Space: O(n)
 *
 * This is straightforward, but requires O(n) additional memory.
 *
 * ------------------------------------------------------------
 * Optimized Approach:
 * ------------------------------------------------------------
 *
 * We can solve the problem using O(1) extra space by combining
 * two techniques:
 *
 * 1. Fast/slow pointers to find the middle.
 * 2. Reverse the second half of the linked list.
 *
 * Then compare the first half with the reversed second half.
 *
 * ------------------------------------------------------------
 * Step 1: Find the middle
 * ------------------------------------------------------------
 *
 * slow moves one node at a time.
 * fast moves two nodes at a time.
 *
 * When fast reaches the end, slow is at the middle.
 *
 * Example:
 *
 * 1 -> 2 -> 3 -> 2 -> 1
 *          ^
 *         slow
 *
 * For an even-sized list:
 *
 * 1 -> 2 -> 2 -> 1
 *              ^
 *            second half
 *
 * With the standard fast/slow loop, slow lands at the
 * beginning of the second half.
 *
 * ------------------------------------------------------------
 * Step 2: Reverse the second half
 * ------------------------------------------------------------
 *
 * Example:
 *
 * Before:
 *
 * 1 -> 2 -> 3 -> 2 -> 1
 *          |
 *        second half
 *
 * Second half:
 *
 * 3 -> 2 -> 1
 *
 * After reversal:
 *
 * 1 -> 2 -> 3
 *
 * We now have two sequences that can be compared:
 *
 * First half:
 * 1 -> 2 -> 3
 *
 * Reversed second half:
 * 1 -> 2 -> 3
 *
 * ------------------------------------------------------------
 * Step 3: Compare both halves
 * ------------------------------------------------------------
 *
 * left  starts at head.
 * right starts at the head of the reversed second half.
 *
 * Compare:
 *
 * left.val == right.val
 *
 * If any pair differs, the list is not a palindrome.
 *
 * If all values match, the list is a palindrome.
 *
 * ------------------------------------------------------------
 * Why does this work?
 * ------------------------------------------------------------
 *
 * A palindrome has identical values when read from both directions.
 *
 * Reversing the second half converts the backwards comparison
 * into a normal forward traversal.
 *
 * Example:
 *
 * Original:
 *
 * 1 -> 2 -> 3 -> 2 -> 1
 *
 * Second half:
 *
 * 3 -> 2 -> 1
 *
 * Reversed:
 *
 * 1 -> 2 -> 3
 *
 * Now both halves can be compared from left to right.
 *
 * ------------------------------------------------------------
 * Important Pointer Insight:
 * ------------------------------------------------------------
 *
 * During reversal, always save current.next before changing it.
 *
 *     ListNode temp = current.next;
 *     current.next = prev;
 *
 * Otherwise, the original forward link would be lost.
 *
 * This is the same pointer principle used in Reverse Linked List.
 *
 * ------------------------------------------------------------
 * Complexity:
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * We perform a constant number of O(n) traversals.
 *
 * Only a few pointer variables are used.
 *
 * ------------------------------------------------------------
 * Common Mistakes:
 * ------------------------------------------------------------
 *
 * 1. Using slow.next instead of current.next when reversing.
 *
 * 2. Moving current using current.next AFTER changing current.next.
 *
 *    Always save the original next node first.
 *
 * 3. Comparing the original second half instead of the reversed
 *    second half.
 *
 * 4. Returning true immediately after comparing only one node.
 *
 * 5. Forgetting that the list is modified by the reversal.
 *
 * ------------------------------------------------------------
 * Interview Follow-ups:
 * ------------------------------------------------------------
 *
 * Q: Can you solve this using O(n) extra space?
 *
 * A: Yes. Store all values in an array/list and compare from both ends.
 *
 * Q: Can you solve it without modifying the linked list?
 *
 * A: Yes, but generally requires O(n) additional space, unless additional
 *    constraints or a different data structure are available.
 *
 * Q: What is the space complexity of the optimal solution?
 *
 * A: O(1) auxiliary space.
 *
 * Q: Can the original linked list be restored?
 *
 * A: Yes. After comparison, reverse the second half again to restore
 *    its original order.
 */
public class PalindromeLinkedList
{
    /**
     * Definition for singly-linked list.
     */
    static class ListNode
    {
        int val;
        ListNode next;

        ListNode()
        {
        }

        ListNode(int val)
        {
            this.val = val;
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Determines whether the linked list is a palindrome.
     */
    public static boolean isPalindrome(ListNode head)
    {
        // Find the middle of the linked list.
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }

        /*
         * Reverse the second half of the list.
         *
         * slow points to the beginning of the second half
         * for even-sized lists and to the middle for odd-sized lists.
         */
        ListNode prev = null;
        ListNode current = slow;

        while (current != null)
        {
            // Save the original forward link before modifying current.next.
            ListNode temp = current.next;

            // Reverse the current node's pointer.
            current.next = prev;

            // Move prev forward.
            prev = current;

            // Continue using the saved forward link.
            current = temp;
        }

        /*
         * Compare the first half with the reversed second half.
         */
        ListNode left = head;
        ListNode right = prev;

        while (left != null && right != null)
        {
            if (left.val != right.val)
            {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    /**
     * Creates a linked list from an array.
     */
    private static ListNode createList(int[] values)
    {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        for (int value : values)
        {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    public static void main(String[] args)
    {
        // Test 1: Odd-length palindrome.
        ListNode head1 = createList(new int[]{1, 2, 3, 2, 1});

        System.out.println("Test 1:");
        System.out.println(isPalindrome(head1));
        System.out.println("Expected: true");
        System.out.println();

        // Test 2: Even-length palindrome.
        ListNode head2 = createList(new int[]{1, 2, 2, 1});

        System.out.println("Test 2:");
        System.out.println(isPalindrome(head2));
        System.out.println("Expected: true");
        System.out.println();

        // Test 3: Not a palindrome.
        ListNode head3 = createList(new int[]{1, 2, 3});

        System.out.println("Test 3:");
        System.out.println(isPalindrome(head3));
        System.out.println("Expected: false");
        System.out.println();

        // Test 4: Single node.
        ListNode head4 = createList(new int[]{1});

        System.out.println("Test 4:");
        System.out.println(isPalindrome(head4));
        System.out.println("Expected: true");
        System.out.println();

        // Test 5: Two nodes that are not equal.
        ListNode head5 = createList(new int[]{1, 2});

        System.out.println("Test 5:");
        System.out.println(isPalindrome(head5));
        System.out.println("Expected: false");
    }
}
