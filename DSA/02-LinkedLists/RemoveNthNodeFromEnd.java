/*
 * LeetCode:
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Problem:
 * Given the head of a singly linked list, remove the nth node
 * from the end of the list and return the new head.
 *
 * Example:
 *
 * Input:
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 * n = 2
 *
 * Output:
 * 1 -> 2 -> 3 -> 5 -> null
 *
 *
 * ------------------------------------------------------------
 * Brute Force Approach
 * ------------------------------------------------------------
 *
 * 1. Traverse the list once to determine its length.
 * 2. Calculate the position of the node to remove from the
 *    beginning.
 * 3. Traverse again to the node before it.
 * 4. Change its next pointer to skip the target node.
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * This works, but requires two traversals.
 *
 *
 * ------------------------------------------------------------
 * Optimized Approach
 * ------------------------------------------------------------
 *
 * Use two pointers with a fixed gap of n nodes.
 *
 * A dummy/sentinel node is placed before the original head:
 *
 *     dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
 *
 * Both fast and slow start at dummy.
 *
 * First, move fast n steps forward.
 *
 * Then move both fast and slow together until fast reaches the
 * last node.
 *
 * Because fast is always n nodes ahead of slow, when fast reaches
 * the end, slow is positioned immediately before the node that
 * needs to be removed.
 *
 * We then remove the target node using:
 *
 *     slow.next = slow.next.next;
 *
 *
 * ------------------------------------------------------------
 * Key Insight
 * ------------------------------------------------------------
 *
 * Maintain a fixed distance of n nodes between fast and slow.
 *
 * This converts:
 *
 *     "Find the nth node from the end"
 *
 * into:
 *
 *     "Find the node immediately before the target."
 *
 *
 * ------------------------------------------------------------
 * Why the Dummy Node Is Important
 * ------------------------------------------------------------
 *
 * Consider:
 *
 *     1 -> 2 -> 3
 *     n = 3
 *
 * The node to remove is the head.
 *
 * Without a dummy node, there is no node before 1 whose next
 * pointer we can modify.
 *
 * With a dummy:
 *
 *     dummy -> 1 -> 2 -> 3
 *
 * slow starts at dummy and ends at dummy when the head needs
 * to be removed.
 *
 * Then:
 *
 *     slow.next = slow.next.next;
 *
 * produces:
 *
 *     dummy -> 2 -> 3
 *
 * Therefore:
 *
 *     dummy.next
 *
 * is always the correct new head.
 *
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * The list is traversed once and only a constant number of
 * pointers are maintained.
 *
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Returning head instead of dummy.next.
 *
 *    This fails when the original head is removed.
 *
 * 2. Moving fast fewer or more than n steps initially.
 *
 * 3. Using:
 *
 *        while (fast != null)
 *
 *    instead of:
 *
 *        while (fast.next != null)
 *
 *    We want slow to stop immediately before the node being
 *    removed.
 *
 * 4. Forgetting the dummy node.
 *
 * 5. Trying to delete the target node by changing its own
 *    pointer instead of changing the previous node's pointer.
 *
 *
 * ------------------------------------------------------------
 * Interview Follow-ups
 * ------------------------------------------------------------
 *
 * 1. Can you solve this without a dummy node?
 * 2. Why does the fixed gap work?
 * 3. Can you solve it recursively?
 * 4. What happens if n equals the length of the list?
 * 5. How would you remove the kth node from the beginning?
 */

public class RemoveNthNodeFromEnd
{
    /*
     * Basic singly linked-list node.
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

    /*
     * Removes the nth node from the end of the linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        /*
         * Dummy node ensures that every real node, including the
         * original head, has a previous node.
         */
        ListNode dummy = new ListNode();
        dummy.next = head;

        /*
         * Both pointers initially start at the dummy node.
         */
        ListNode fast = dummy;
        ListNode slow = dummy;

        /*
         * Create a gap of n nodes between fast and slow.
         */
        for (int i = 0; i < n; i++)
        {
            fast = fast.next;
        }

        /*
         * Move both pointers until fast reaches the final node.
         *
         * At this point slow will be immediately before the node
         * that needs to be removed.
         */
        while (fast.next != null)
        {
            fast = fast.next;
            slow = slow.next;
        }

        /*
         * Remove the target node by skipping over it.
         */
        slow.next = slow.next.next;

        /*
         * The real head is always immediately after dummy.
         */
        return dummy.next;
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

    public static void main(String[] args)
    {
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();

        /*
         * Test 1:
         *
         * 1 -> 2 -> 3 -> 4 -> 5
         * n = 2
         *
         * Expected:
         *
         * 1 -> 2 -> 3 -> 5 -> null
         */
        ListNode list1 = buildList(
                new int[]{1, 2, 3, 4, 5}
        );

        ListNode result1 = solution.removeNthFromEnd(list1, 2);

        System.out.println("Test 1:");
        printList(result1);


        /*
         * Test 2:
         *
         * Removing the head.
         *
         * 1 -> 2 -> 3
         * n = 3
         *
         * Expected:
         *
         * 2 -> 3 -> null
         */
        ListNode list2 = buildList(
                new int[]{1, 2, 3}
        );

        ListNode result2 = solution.removeNthFromEnd(list2, 3);

        System.out.println("Test 2:");
        printList(result2);


        /*
         * Test 3:
         *
         * Single node.
         *
         * 1 -> null
         * n = 1
         *
         * Expected:
         *
         * null
         */
        ListNode list3 = buildList(
                new int[]{1}
        );

        ListNode result3 = solution.removeNthFromEnd(list3, 1);

        System.out.println("Test 3:");

        if (result3 == null)
        {
            System.out.println("null");
        }
        else
        {
            printList(result3);
        }


        /*
         * Test 4:
         *
         * Remove the last node.
         *
         * 1 -> 2 -> 3
         * n = 1
         *
         * Expected:
         *
         * 1 -> 2 -> null
         */
        ListNode list4 = buildList(
                new int[]{1, 2, 3}
        );

        ListNode result4 = solution.removeNthFromEnd(list4, 1);

        System.out.println("Test 4:");
        printList(result4);
    }
}
