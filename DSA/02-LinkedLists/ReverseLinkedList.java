/*
 * LeetCode:
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * Problem:
 * Given the head of a singly linked list, reverse the list
 * and return the new head.
 *
 * Example:
 *
 * Input:
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 *
 * Output:
 * 5 -> 4 -> 3 -> 2 -> 1 -> null
 *
 *
 * ------------------------------------------------------------
 * Brute Force / Alternative Approach
 * ------------------------------------------------------------
 *
 * One possible approach would be to traverse the linked list,
 * store all node values in an array/list, and then reconstruct
 * or reverse the values.
 *
 * However, this does not actually reverse the linked-list
 * structure and requires O(n) additional space.
 *
 *
 * ------------------------------------------------------------
 * Optimized Approach
 * ------------------------------------------------------------
 *
 * Reverse the links between nodes in-place.
 *
 * We maintain three references:
 *
 *     previous
 *     current
 *     next
 *
 * At any point:
 *
 *     previous              current
 *        ↓                     ↓
 *     reversed portion    unprocessed portion
 *
 *
 * For every node:
 *
 * 1. Save current.next in next.
 *    This is critical because we are about to modify current.next.
 *
 * 2. Reverse the current node's link:
 *
 *        current.next = previous
 *
 * 3. Move previous forward:
 *
 *        previous = current
 *
 * 4. Move current forward using the saved next reference:
 *
 *        current = next
 *
 * When current becomes null, previous is the new head.
 *
 *
 * ------------------------------------------------------------
 * Key Insight
 * ------------------------------------------------------------
 *
 * Before modifying a linked-list pointer, ask:
 *
 * "Am I about to destroy the only reference to something
 *  I still need?"
 *
 * That is why we MUST save:
 *
 *     ListNode next = current.next;
 *
 * before doing:
 *
 *     current.next = previous;
 *
 *
 * ------------------------------------------------------------
 * Why It Works
 * ------------------------------------------------------------
 *
 * Initially:
 *
 *     previous = null
 *     current  = head
 *
 * Example:
 *
 *     1 -> 2 -> 3 -> null
 *
 * After processing 1:
 *
 *     1 -> null
 *
 *     2 -> 3 -> null
 *     ^
 *   current
 *
 * After processing 2:
 *
 *     2 -> 1 -> null
 *
 *     3 -> null
 *     ^
 *   current
 *
 * After processing 3:
 *
 *     3 -> 2 -> 1 -> null
 *
 *     current -> null
 *
 * Therefore, previous points to the new head.
 *
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * Every node is processed exactly once and only a constant
 * number of references are maintained.
 *
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Using:
 *
 *        while (current.next != null)
 *
 *    instead of:
 *
 *        while (current != null)
 *
 *    The final node would never be processed.
 *
 * 2. Updating current.next before saving the original next node.
 *
 *        current.next = previous;
 *        next = current.next;       // WRONG
 *
 *    The original forward link has already been lost.
 *
 * 3. Returning current instead of previous.
 *
 *    At the end:
 *
 *        current  -> null
 *        previous -> new head
 *
 *
 * ------------------------------------------------------------
 * Interview Follow-ups
 * ------------------------------------------------------------
 *
 * 1. Can you implement this recursively?
 * 2. What is the space complexity of the recursive solution?
 * 3. How would you reverse only a portion of a linked list?
 * 4. How would you reverse a linked list in groups of k nodes?
 * 5. How would the solution change for a doubly linked list?
 */

public class ReverseLinkedList
{
    /*
     * Basic singly linked-list node.
     */
    static class ListNode
    {
        int val;
        ListNode next;

        ListNode(int val)
        {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }

    /*
     * Reverses the linked list in-place.
     */
    public ListNode reverseList(ListNode head)
    {
        ListNode current = head;
        ListNode previous = null;

        while (current != null)
        {
            /*
             * Save the next node before changing current.next.
             *
             * Otherwise, changing current.next would destroy
             * our reference to the remaining unprocessed list.
             */
            ListNode next = current.next;

            /*
             * Reverse the direction of the current node's link.
             */
            current.next = previous;

            /*
             * Move previous forward.
             * The current node is now part of the reversed portion.
             */
            previous = current;

            /*
             * Move current forward into the unprocessed portion.
             */
            current = next;
        }

        /*
         * current is now null.
         * previous is the new head of the reversed list.
         */
        return previous;
    }

    /*
     * Utility method to print a linked list.
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

        System.out.println(" -> null");
    }

    /*
     * Utility method to build a linked list from an array.
     */
    private static ListNode buildList(int[] values)
    {
        if (values.length == 0)
        {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++)
        {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public static void main(String[] args)
    {
        ReverseLinkedList solution = new ReverseLinkedList();

        /*
         * Test 1:
         *
         * 1 -> 2 -> 3 -> 4 -> 5
         *
         * Expected:
         *
         * 5 -> 4 -> 3 -> 2 -> 1
         */
        ListNode list1 = buildList(
                new int[]{1, 2, 3, 4, 5}
        );

        ListNode reversed1 = solution.reverseList(list1);

        System.out.println("Test 1:");
        printList(reversed1);


        /*
         * Test 2:
         *
         * Single node.
         *
         * Expected:
         *
         * 1 -> null
         */
        ListNode list2 = buildList(
                new int[]{1}
        );

        ListNode reversed2 = solution.reverseList(list2);

        System.out.println("Test 2:");
        printList(reversed2);


        /*
         * Test 3:
         *
         * Empty list.
         *
         * Expected:
         *
         * null
         */
        ListNode list3 = buildList(
                new int[]{}
        );

        ListNode reversed3 = solution.reverseList(list3);

        System.out.println("Test 3:");

        if (reversed3 == null)
        {
            System.out.println("null");
        }
        else
        {
            printList(reversed3);
        }
    }
}
