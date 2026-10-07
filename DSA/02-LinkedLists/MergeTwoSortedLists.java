/*
 * LeetCode:
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Problem:
 * Merge two sorted singly linked lists into one sorted linked list.
 *
 * The existing nodes should be reused rather than creating a new
 * node for every element.
 *
 * Example:
 *
 * list1: 1 -> 2 -> 4 -> null
 * list2: 1 -> 3 -> 4 -> null
 *
 * Result:
 *
 * 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null
 *
 *
 * ------------------------------------------------------------
 * Brute Force / Alternative Approach
 * ------------------------------------------------------------
 *
 * One approach would be to copy all values from both lists into
 * an array/list, sort them, and construct a new linked list.
 *
 * However:
 *
 * 1. We would need O(n + m) additional space.
 * 2. Sorting introduces O((n + m) log(n + m)) time.
 * 3. We would ignore the fact that both input lists are already
 *    sorted.
 *
 *
 * ------------------------------------------------------------
 * Optimized Approach
 * ------------------------------------------------------------
 *
 * Since both lists are already sorted, we can merge them in
 * linear time using two pointers.
 *
 * list1 and list2 always point to the first unprocessed node
 * in their respective lists.
 *
 * We maintain a sentinel/dummy node and a tail pointer.
 *
 *
 *             merged portion
 * sentinel -> 1 -> 1 -> 2 -> ?
 *                         ^
 *                        tail
 *
 * list1 -> first unprocessed node
 * list2 -> first unprocessed node
 *
 *
 * At every iteration:
 *
 * 1. Compare list1.val and list2.val.
 * 2. Attach the smaller node after tail.
 * 3. Advance the list from which the node was selected.
 * 4. Advance tail to the newly added node.
 *
 * Once either list becomes null, the other list is already sorted,
 * so we can attach the entire remaining list directly.
 *
 *
 * ------------------------------------------------------------
 * Why Use a Sentinel / Dummy Node?
 * ------------------------------------------------------------
 *
 * Without a dummy node, we would need special logic for deciding
 * what the head of the merged list should be.
 *
 * The sentinel gives us a permanent node before the real result:
 *
 * sentinel -> 1 -> 2 -> 3 -> ...
 *
 * At the end:
 *
 *     sentinel.next
 *
 * is the actual head of the merged list.
 *
 *
 * ------------------------------------------------------------
 * Key Invariant
 * ------------------------------------------------------------
 *
 * At the beginning of every iteration:
 *
 *     tail
 *       ↓
 * sentinel -> [merged portion]
 *
 *     list1 -> [unprocessed portion of list1]
 *
 *     list2 -> [unprocessed portion of list2]
 *
 * tail is always the last node in the merged portion.
 *
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 *
 * Let:
 *
 *     n = length of list1
 *     m = length of list2
 *
 * Time:  O(n + m)
 *
 * Every node from both lists is processed at most once.
 *
 * Space: O(1)
 *
 * No additional data structure grows with the input size.
 * We reuse the existing nodes.
 *
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Using:
 *
 *        while (list1 != null || list2 != null)
 *
 *    and then accessing both list1.val and list2.val.
 *
 *    Both lists must be non-null while comparing their values.
 *
 * 2. Forgetting to advance the selected list.
 *
 * 3. Forgetting to advance tail.
 *
 * 4. Creating unnecessary new nodes instead of reusing the
 *    existing nodes.
 *
 * 5. Forgetting to attach the remaining list after one list
 *    becomes null.
 *
 * 6. Returning sentinel instead of sentinel.next.
 *
 *
 * ------------------------------------------------------------
 * Interview Follow-ups
 * ------------------------------------------------------------
 *
 * 1. How would you merge k sorted linked lists?
 * 2. How would you solve that using a PriorityQueue?
 * 3. Can you merge two lists recursively?
 * 4. What is the space complexity of the recursive solution?
 * 5. What changes if the lists are sorted in descending order?
 */

public class MergeTwoSortedLists
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
     * Merges two sorted linked lists.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2)
    {
        /*
         * The sentinel is a dummy node that makes handling the
         * first real node straightforward.
         */
        ListNode sentinel = new ListNode();

        /*
         * tail always points to the last node in the merged list.
         */
        ListNode tail = sentinel;

        /*
         * We can compare nodes only while both lists still have
         * nodes remaining.
         */
        while (list1 != null && list2 != null)
        {
            if (list1.val <= list2.val)
            {
                /*
                 * Attach the current node from list1.
                 */
                tail.next = list1;

                /*
                 * Move list1 to its next unprocessed node.
                 */
                list1 = list1.next;

                /*
                 * Move tail to the node we just attached.
                 */
                tail = tail.next;
            }
            else if (list2.val < list1.val)
            {
                /*
                 * Attach the current node from list2.
                 */
                tail.next = list2;

                /*
                 * Move list2 to its next unprocessed node.
                 */
                list2 = list2.next;

                /*
                 * Move tail to the node we just attached.
                 */
                tail = tail.next;
            }
        }

        /*
         * At this point, at least one list is empty.
         *
         * The remaining list is already sorted, so we can attach
         * it directly without processing every remaining node.
         */
        if (list1 != null)
        {
            tail.next = list1;
        }

        if (list2 != null)
        {
            tail.next = list2;
        }

        /*
         * sentinel itself is not part of the result.
         * The real merged list starts at sentinel.next.
         */
        return sentinel.next;
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
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        /*
         * Test 1:
         *
         * 1 -> 2 -> 4
         * 1 -> 3 -> 4
         *
         * Expected:
         *
         * 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null
         */
        ListNode list1 = buildList(
                new int[]{1, 2, 4}
        );

        ListNode list2 = buildList(
                new int[]{1, 3, 4}
        );

        ListNode merged1 = solution.mergeTwoLists(list1, list2);

        System.out.println("Test 1:");
        printList(merged1);


        /*
         * Test 2:
         *
         * One list is empty.
         *
         * Expected:
         *
         * 1 -> 2 -> 3 -> null
         */
        ListNode list3 = null;

        ListNode list4 = buildList(
                new int[]{1, 2, 3}
        );

        ListNode merged2 = solution.mergeTwoLists(list3, list4);

        System.out.println("Test 2:");
        printList(merged2);


        /*
         * Test 3:
         *
         * Both lists are empty.
         *
         * Expected:
         *
         * null
         */
        ListNode merged3 = solution.mergeTwoLists(null, null);

        System.out.println("Test 3:");

        if (merged3 == null)
        {
            System.out.println("null");
        }
        else
        {
            printList(merged3);
        }


        /*
         * Test 4:
         *
         * Duplicate values.
         *
         * Expected:
         *
         * 1 -> 1 -> 1 -> 2 -> 2 -> 3 -> null
         */
        ListNode list5 = buildList(
                new int[]{1, 1, 2}
        );

        ListNode list6 = buildList(
                new int[]{1, 2, 3}
        );

        ListNode merged4 = solution.mergeTwoLists(list5, list6);

        System.out.println("Test 4:");
        printList(merged4);
    }
}
