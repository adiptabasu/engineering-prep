/**
 * LeetCode: Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * Problem:
 * Given the head of a singly linked list, return the middle node.
 *
 * If the list contains an even number of nodes, return the SECOND middle node.
 *
 * Example:
 *
 * Odd length:
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 *             ^
 *           middle
 *
 * Even length:
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 *                 ^
 *              2nd middle
 *
 * ------------------------------------------------------------
 * Brute Force Approach:
 * ------------------------------------------------------------
 * 1. Traverse the list once to count the number of nodes.
 * 2. Traverse again until reaching index length / 2.
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * This works, but requires two passes.
 *
 * ------------------------------------------------------------
 * Optimized Approach:
 * ------------------------------------------------------------
 * Use two pointers:
 *
 * slow -> moves one node at a time
 * fast -> moves two nodes at a time
 *
 * When fast reaches the end, slow will be at the middle.
 *
 * Example:
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 *
 * Initially:
 * slow = 1
 * fast = 1
 *
 * Step 1:
 * slow = 2
 * fast = 3
 *
 * Step 2:
 * slow = 3
 * fast = 5
 *
 * Step 3:
 * fast reaches null
 *
 * Therefore:
 * slow = 3
 *
 * ------------------------------------------------------------
 * Why does this return the SECOND middle for even-sized lists?
 * ------------------------------------------------------------
 *
 * Example:
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 *
 * Initially:
 * slow = 1
 * fast = 1
 *
 * Step 1:
 * slow = 2
 * fast = 3
 *
 * Step 2:
 * slow = 3
 * fast = 5
 *
 * Step 3:
 * slow = 4
 * fast = null
 *
 * Therefore slow points to 4, which is the second middle.
 *
 * ------------------------------------------------------------
 * Key Insight:
 * ------------------------------------------------------------
 *
 * Fast moves twice as quickly as slow.
 *
 * Therefore, by the time fast has traversed the entire list,
 * slow has traversed approximately half of the list.
 *
 * ------------------------------------------------------------
 * Invariant:
 * ------------------------------------------------------------
 *
 * Throughout the traversal:
 *
 * fast has moved approximately twice as many nodes as slow.
 *
 * Therefore, when fast reaches the end, slow is positioned
 * at the middle.
 *
 * ------------------------------------------------------------
 * Why no dummy node?
 * ------------------------------------------------------------
 *
 * We are not modifying the linked list.
 *
 * We only need to find and return an existing node.
 *
 * Therefore, there is no need for a sentinel/dummy node.
 *
 * Dummy nodes are particularly useful when operations may modify
 * the head of a linked list, such as:
 *
 * - Remove Nth Node From End
 * - Merge Two Sorted Lists
 * - Insert/Delete at the beginning
 *
 * ------------------------------------------------------------
 * Complexity:
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * The list is traversed once and only two pointers are used.
 *
 * ------------------------------------------------------------
 * Common Mistakes:
 * ------------------------------------------------------------
 *
 * 1. Using:
 *
 *    while (fast.next != null && fast.next.next != null)
 *
 *    This can produce the WRONG middle depending on the required
 *    definition of middle.
 *
 *    LeetCode wants the SECOND middle for even-sized lists.
 *
 * 2. Forgetting to check:
 *
 *    fast != null
 *
 *    before accessing fast.next.
 *
 * 3. Moving slow by two nodes instead of one.
 *
 * 4. Returning fast instead of slow.
 *
 * ------------------------------------------------------------
 * Interview Follow-ups:
 * ------------------------------------------------------------
 *
 * Q: How would you find the FIRST middle for an even-sized list?
 *
 * A: Change the pointer initialization/loop condition so that slow
 *    stops one position earlier.
 *
 * Q: Can this be done with O(1) extra space?
 *
 * A: Yes. The fast/slow pointer approach already uses O(1) space.
 *
 * Q: Why can't we simply use the list length?
 *
 * A: We can, but that requires an additional traversal. Fast/slow
 *    finds the middle in a single traversal.
 */
public class MiddleOfTheLinkedList
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
     * Returns the middle node of the linked list.
     *
     * For an even-sized list, returns the second middle node.
     */
    public static ListNode middleNode(ListNode head)
    {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null)
        {
            // Fast moves two nodes.
            fast = fast.next.next;

            // Slow moves one node.
            slow = slow.next;
        }

        return slow;
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

    /**
     * Prints a linked list starting from the given node.
     */
    private static void printList(ListNode head)
    {
        ListNode current = head;

        while (current != null)
        {
            System.out.print(current.val);

            if (current.next != null)
            {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args)
    {
        // Test 1: Odd number of nodes.
        //
        // 1 -> 2 -> 3 -> 4 -> 5
        //          ^
        //        middle
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode middle1 = middleNode(head1);

        System.out.println("Test 1:");
        printList(middle1);
        System.out.println("Expected: 3");
        System.out.println();

        // Test 2: Even number of nodes.
        //
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        //               ^
        //            2nd middle
        ListNode head2 = createList(new int[]{1, 2, 3, 4, 5, 6});
        ListNode middle2 = middleNode(head2);

        System.out.println("Test 2:");
        printList(middle2);
        System.out.println("Expected: 4");
        System.out.println();

        // Test 3: Single node.
        ListNode head3 = createList(new int[]{1});
        ListNode middle3 = middleNode(head3);

        System.out.println("Test 3:");
        printList(middle3);
        System.out.println("Expected: 1");
        System.out.println();

        // Test 4: Two nodes.
        //
        // LeetCode expects the second middle.
        ListNode head4 = createList(new int[]{1, 2});
        ListNode middle4 = middleNode(head4);

        System.out.println("Test 4:");
        printList(middle4);
        System.out.println("Expected: 2");
    }
}
