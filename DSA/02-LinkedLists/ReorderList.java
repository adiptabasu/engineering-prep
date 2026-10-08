/**
 * LeetCode: Reorder List
 * https://leetcode.com/problems/reorder-list/
 *
 * Problem:
 * Given the head of a singly linked list:
 *
 *     L0 → L1 → L2 → ... → Ln
 *
 * Reorder it into:
 *
 *     L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
 *
 * The node values must not be changed.
 * The actual linked-list nodes must be rearranged.
 *
 * Example:
 *
 * Input:
 *     1 → 2 → 3 → 4 → 5
 *
 * Output:
 *     1 → 5 → 2 → 4 → 3
 *
 * Example:
 *
 * Input:
 *     1 → 2 → 3 → 4
 *
 * Output:
 *     1 → 4 → 2 → 3
 *
 * ------------------------------------------------------------
 * Brute Force Approach:
 * ------------------------------------------------------------
 *
 * One possible approach is to store all nodes in an array/list.
 *
 * Then use two indexes:
 *
 *     left  → beginning
 *     right → end
 *
 * and repeatedly connect:
 *
 *     left → right → left+1 → right-1 ...
 *
 * Time:  O(n)
 * Space: O(n)
 *
 * This is easier to reason about, but uses additional memory.
 *
 * ------------------------------------------------------------
 * Optimized Approach:
 * ------------------------------------------------------------
 *
 * We can solve this using O(1) auxiliary space by combining
 * three linked-list techniques:
 *
 * 1. Find the middle using fast/slow pointers.
 * 2. Reverse the second half.
 * 3. Interleave the two halves.
 *
 * ------------------------------------------------------------
 * Step 1: Find the middle
 * ------------------------------------------------------------
 *
 * Example:
 *
 *     1 → 2 → 3 → 4 → 5
 *
 * Using:
 *
 *     slow → one node at a time
 *     fast → two nodes at a time
 *
 * slow eventually reaches:
 *
 *     1 → 2 → 3 → 4 → 5
 *             ↑
 *            slow
 *
 * ------------------------------------------------------------
 * Step 2: Split the list
 * ------------------------------------------------------------
 *
 * Save the second half before breaking the connection:
 *
 *     ListNode second = slow.next;
 *     slow.next = null;
 *
 * This gives:
 *
 *     First:
 *     1 → 2 → 3 → null
 *
 *     Second:
 *     4 → 5 → null
 *
 * ------------------------------------------------------------
 * Step 3: Reverse the second half
 * ------------------------------------------------------------
 *
 *     4 → 5
 *
 * becomes:
 *
 *     5 → 4
 *
 * Now we have:
 *
 *     First:  1 → 2 → 3
 *     Second: 5 → 4
 *
 * ------------------------------------------------------------
 * Step 4: Interleave the two halves
 * ------------------------------------------------------------
 *
 * We repeatedly insert one node from the reversed second half
 * after a node from the first half.
 *
 * Starting:
 *
 *     First:  1 → 2 → 3
 *     Second: 5 → 4
 *
 * First iteration:
 *
 *     1 → 5 → 2 → 3
 *
 * Second iteration:
 *
 *     1 → 5 → 2 → 4 → 3
 *
 * Result:
 *
 *     1 → 5 → 2 → 4 → 3
 *
 * ------------------------------------------------------------
 * Important Pointer Rule:
 * ------------------------------------------------------------
 *
 * Before changing a node's next pointer, save the original
 * next node if we still need to traverse it.
 *
 * During interleaving:
 *
 *     ListNode firstRest = correctFirst.next;
 *
 * Then:
 *
 *     correctFirst.next = reverseSecond;
 *
 * We can safely move reverseSecond forward because its original
 * next pointer is still accessible.
 *
 * ------------------------------------------------------------
 * Why does the algorithm work?
 * ------------------------------------------------------------
 *
 * The required ordering is:
 *
 *     first node
 *     last node
 *     second node
 *     second-last node
 *     third node
 *     third-last node
 *
 * Splitting gives us:
 *
 *     L0 → L1 → L2
 *
 * and reversing the second half gives:
 *
 *     Ln → Ln-1 → Ln-2
 *
 * Interleaving them therefore produces exactly the required order.
 *
 * ------------------------------------------------------------
 * Complexity:
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * Finding the middle, reversing the second half, and interleaving
 * each take O(n) time.
 *
 * Only a constant number of pointer variables are used.
 *
 * ------------------------------------------------------------
 * Common Mistakes:
 * ------------------------------------------------------------
 *
 * 1. Reversing starting at slow instead of slow.next.
 *
 *    This causes the middle node to belong to both halves.
 *
 * 2. Forgetting:
 *
 *        slow.next = null;
 *
 *    Without splitting, the two halves remain connected and can
 *    create incorrect links or cycles.
 *
 * 3. Not saving first.next before overwriting it.
 *
 * 4. Advancing reverseSecond incorrectly after changing its links.
 *
 * 5. Reversing the second half again unnecessarily.
 *
 * ------------------------------------------------------------
 * Interview Follow-ups:
 * ------------------------------------------------------------
 *
 * Q: Can this be solved with O(n) extra space?
 *
 * A: Yes. Store all nodes in an array/list and use two pointers.
 *
 * Q: Can we do it in O(1) auxiliary space?
 *
 * A: Yes. The fast/slow + reverse + interleave solution does this.
 *
 * Q: Why do we reverse the second half?
 *
 * A: Because we need nodes from the end of the original list in
 *    descending order of their original positions.
 *
 * Q: What happens if the list has an odd number of nodes?
 *
 * A: The first half contains one additional node. It remains at
 *    the end of the reordered list.
 */
public class ReorderList
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
     * Reorders the linked list in-place.
     */
    public static void reorderList(ListNode head)
    {
        /*
         * Step 1:
         * Find the middle of the linked list.
         */
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }

        /*
         * Step 2:
         * Split the list into two halves.
         *
         * Save the beginning of the second half before breaking
         * the connection.
         */
        ListNode second = slow.next;
        slow.next = null;

        /*
         * Step 3:
         * Reverse the second half.
         *
         * Example:
         *
         *     4 → 5
         *
         * becomes:
         *
         *     5 → 4
         */
        ListNode reverseSecond = reverseLinkedList(second);

        /*
         * Step 4:
         * Interleave the first half and reversed second half.
         *
         * Example:
         *
         * First:
         *     1 → 2 → 3
         *
         * Second:
         *     5 → 4
         *
         * Result:
         *     1 → 5 → 2 → 4 → 3
         */
        ListNode correctFirst = head;

        while (reverseSecond != null)
        {
            // Save the remaining first-half list.
            ListNode firstRest = correctFirst.next;

            // Insert the current second-half node.
            correctFirst.next = reverseSecond;

            // Move to the next second-half node.
            reverseSecond = reverseSecond.next;

            // Connect the inserted node to the remaining first half.
            correctFirst.next.next = firstRest;

            // Move to the next first-half node.
            correctFirst = correctFirst.next.next;
        }
    }

    /**
     * Reverses a singly linked list.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    private static ListNode reverseLinkedList(ListNode head)
    {
        ListNode prev = null;
        ListNode current = head;

        while (current != null)
        {
            // Save the original forward link.
            ListNode temp = current.next;

            // Reverse the pointer.
            current.next = prev;

            // Move prev forward.
            prev = current;

            // Continue using the saved link.
            current = temp;
        }

        return prev;
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
     * Prints a linked list.
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
        // becomes:
        // 1 -> 5 -> 2 -> 4 -> 3
        ListNode head1 = createList(new int[]{1, 2, 3, 4, 5});

        reorderList(head1);

        System.out.println("Test 1:");
        printList(head1);
        System.out.println("Expected: 1 -> 5 -> 2 -> 4 -> 3");
        System.out.println();

        // Test 2: Even number of nodes.
        //
        // 1 -> 2 -> 3 -> 4
        // becomes:
        // 1 -> 4 -> 2 -> 3
        ListNode head2 = createList(new int[]{1, 2, 3, 4});

        reorderList(head2);

        System.out.println("Test 2:");
        printList(head2);
        System.out.println("Expected: 1 -> 4 -> 2 -> 3");
        System.out.println();

        // Test 3: Three nodes.
        //
        // 1 -> 2 -> 3
        // becomes:
        // 1 -> 3 -> 2
        ListNode head3 = createList(new int[]{1, 2, 3});

        reorderList(head3);

        System.out.println("Test 3:");
        printList(head3);
        System.out.println("Expected: 1 -> 3 -> 2");
        System.out.println();

        // Test 4: Two nodes.
        //
        // 1 -> 2
        // remains:
        // 1 -> 2
        ListNode head4 = createList(new int[]{1, 2});

        reorderList(head4);

        System.out.println("Test 4:");
        printList(head4);
        System.out.println("Expected: 1 -> 2");
        System.out.println();

        // Test 5: Single node.
        ListNode head5 = createList(new int[]{1});

        reorderList(head5);

        System.out.println("Test 5:");
        printList(head5);
        System.out.println("Expected: 1");
    }
}
