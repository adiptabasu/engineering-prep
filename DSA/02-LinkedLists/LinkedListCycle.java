/*
 * LeetCode:
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * Problem:
 * Given the head of a linked list, determine whether the linked
 * list contains a cycle.
 *
 * A cycle exists when a node's next pointer points back to a
 * previous node instead of eventually reaching null.
 *
 * Example with a cycle:
 *
 *     1 -> 2 -> 3 -> 4
 *               ^    |
 *               |____|
 *
 * Example without a cycle:
 *
 *     1 -> 2 -> 3 -> 4 -> null
 *
 *
 * ------------------------------------------------------------
 * Brute Force Approach
 * ------------------------------------------------------------
 *
 * Traverse the linked list while storing every visited node in
 * a HashSet.
 *
 * For every node:
 *
 *     If the node is already in the set:
 *         A cycle exists.
 *
 *     Otherwise:
 *         Add it to the set and continue.
 *
 * Time:  O(n)
 * Space: O(n)
 *
 * This works, but we can solve the problem using O(1) space.
 *
 *
 * ------------------------------------------------------------
 * Optimized Approach — Floyd's Cycle Detection
 * ------------------------------------------------------------
 *
 * Use two pointers:
 *
 *     slow -> moves one node at a time
 *     fast -> moves two nodes at a time
 *
 * If there is no cycle, fast will eventually reach null.
 *
 * If there is a cycle, both pointers will eventually enter the
 * cycle. Since fast moves one node faster than slow on every
 * iteration, fast will eventually catch up with slow.
 *
 * Therefore:
 *
 *     slow == fast
 *
 * means a cycle exists.
 *
 *
 * ------------------------------------------------------------
 * Key Insight
 * ------------------------------------------------------------
 *
 * Think of slow and fast as two runners.
 *
 * On a straight path:
 *
 *     slow -> -> -> null
 *     fast -> -> -> -> -> null
 *
 * The faster runner eventually reaches the end.
 *
 * On a circular path:
 *
 *     slow -> -> -> ┐
 *                   ↓
 *                   cycle
 *                   ↑
 *     fast -> -> -> ┘
 *
 * The faster runner repeatedly gains one position relative to
 * the slower runner and eventually catches it.
 *
 *
 * ------------------------------------------------------------
 * Why the Loop Condition Matters
 * ------------------------------------------------------------
 *
 * Before executing:
 *
 *     fast = fast.next.next;
 *
 * we must know that both:
 *
 *     fast
 *
 * and:
 *
 *     fast.next
 *
 * are non-null.
 *
 * Therefore:
 *
 *     while (fast != null && fast.next != null)
 *
 * If either becomes null, the list does not contain a cycle.
 *
 *
 * ------------------------------------------------------------
 * Why We Use == Instead of .equals()
 * ------------------------------------------------------------
 *
 * We need to determine whether fast and slow point to the
 * EXACT SAME NODE object.
 *
 * Therefore:
 *
 *     fast == slow
 *
 * is the correct comparison.
 *
 * We are not comparing node values.
 *
 * Two different nodes can contain the same value:
 *
 *     Node A: val = 5
 *     Node B: val = 5
 *
 * A.val == B.val is true,
 * but A == B is false.
 *
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1)
 *
 * We only use two pointers regardless of the size of the list.
 *
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Using:
 *
 *        while (fast != null)
 *
 *    and then accessing fast.next.next.
 *
 *    fast.next may be null.
 *
 * 2. Comparing node values:
 *
 *        fast.val == slow.val
 *
 *    instead of comparing node references:
 *
 *        fast == slow
 *
 * 3. Checking fast == slow before moving the pointers.
 *
 *    Both pointers start at head, so they would always be equal
 *    initially, even for a list without a cycle.
 *
 * 4. Using a HashSet unnecessarily when O(1) space is possible.
 *
 *
 * ------------------------------------------------------------
 * Interview Follow-ups
 * ------------------------------------------------------------
 *
 * 1. How would you find the node where the cycle begins?
 * 2. Why are two pointers guaranteed to meet inside the cycle?
 * 3. What is the mathematical reasoning behind Floyd's algorithm?
 * 4. Can you detect a cycle in a different data structure?
 * 5. What is the trade-off between the HashSet and Floyd's
 *    algorithm?
 */

public class LinkedListCycle
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
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }

    /*
     * Determines whether the linked list contains a cycle.
     *
     * Uses Floyd's Fast & Slow Pointer algorithm.
     */
    public boolean hasCycle(ListNode head)
    {
        ListNode fast = head;
        ListNode slow = head;

        /*
         * fast needs both itself and fast.next to be non-null
         * before it can safely move two nodes forward.
         */
        while (fast != null && fast.next != null)
        {
            /*
             * Fast moves two nodes.
             */
            fast = fast.next.next;

            /*
             * Slow moves one node.
             */
            slow = slow.next;

            /*
             * If both pointers reference the same node,
             * we have entered a cycle.
             */
            if (fast == slow)
            {
                return true;
            }
        }

        /*
         * fast reached the end of the list,
         * therefore there is no cycle.
         */
        return false;
    }

    public static void main(String[] args)
    {
        LinkedListCycle solution = new LinkedListCycle();

        /*
         * Test 1:
         *
         * 1 -> 2 -> 3 -> 4
         *      ^         |
         *      |_________|
         *
         * Expected: true
         */
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println(
                "Test 1: " + solution.hasCycle(node1)
        );


        /*
         * Test 2:
         *
         * 1 -> 2 -> 3 -> 4 -> null
         *
         * Expected: false
         */
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(3);
        ListNode node8 = new ListNode(4);

        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        System.out.println(
                "Test 2: " + solution.hasCycle(node5)
        );


        /*
         * Test 3:
         *
         * Empty list.
         *
         * Expected: false
         */
        System.out.println(
                "Test 3: " + solution.hasCycle(null)
        );


        /*
         * Test 4:
         *
         * Single node pointing to itself.
         *
         * 1
         * ↑
         * └──┘
         *
         * Expected: true
         */
        ListNode node9 = new ListNode(1);
        node9.next = node9;

        System.out.println(
                "Test 4: " + solution.hasCycle(node9)
        );
    }
}
