/*
 * LeetCode Problem: Swap Nodes in Pairs
 * URL: https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Problem:
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * The nodes themselves must be swapped, not just their values.
 *
 * Example:
 * Input:  1 -> 2 -> 3 -> 4
 * Output: 2 -> 1 -> 4 -> 3
 *
 * Example with an odd number of nodes:
 * Input:  1 -> 2 -> 3
 * Output: 2 -> 1 -> 3
 *
 * ---------------------------------------------------------------
 * APPROACH: ITERATIVE POINTER MANIPULATION
 * ---------------------------------------------------------------
 *
 * 1. Create a sentinel node pointing to the original head.
 * 2. Maintain a pointer named current to the node before the pair.
 * 3. Identify the two nodes to swap.
 * 4. Rewire their next pointers.
 * 5. Advance current to the tail of the swapped pair.
 * 6. Repeat while at least two nodes remain.
 *
 * Key Insight:
 * The sentinel provides a stable predecessor for the first pair.
 * After swapping, the original first node becomes the tail of the pair.
 *
 * Pointer Changes:
 *
 * Before:
 * previous -> firstNode -> secondNode -> nextPair
 *
 * After:
 * previous -> secondNode -> firstNode -> nextPair
 *
 * Why It Works:
 * Each iteration swaps exactly two adjacent nodes while preserving
 * the connection to the remainder of the list.
 *
 * The loop condition guarantees that both nodes exist before swapping.
 * If one node remains without a partner, it stays unchanged.
 *
 * Complexity:
 * Time: O(n)
 * Auxiliary Space: O(1)
 *
 * Common Mistakes:
 * 1. Checking current.next but not current.next.next.
 * 2. Losing the connection to the remaining list.
 * 3. Updating pointers in an order that loses access to a node.
 * 4. Advancing current to the wrong node after the swap.
 * 5. Swapping node values instead of the nodes themselves.
 *
 * Interview Follow-ups:
 * 1. How would you implement this recursively?
 * 2. Why is a sentinel node useful?
 * 3. How does the solution handle an odd number of nodes?
 * 4. Can you solve it using O(1) auxiliary space?
 */

public class SwapNodesInPairs {

    /*
     * LeetCode supplies this class automatically.
     * Included here for standalone local testing.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
     * Swaps adjacent nodes in pairs.
     *
     * The nodes themselves are rearranged; their values are not changed.
     */
    public ListNode swapPairs(ListNode head) {

        // Sentinel simplifies handling the first pair.
        ListNode sentinel = new ListNode();
        sentinel.next = head;

        // Points to the node immediately before the pair being swapped.
        ListNode current = sentinel;

        /*
         * Both nodes must exist before we attempt a swap.
         *
         * If fewer than two nodes remain, the loop terminates.
         */
        while (current.next != null && current.next.next != null) {

            ListNode previous = current;
            ListNode firstNode = current.next;
            ListNode secondNode = current.next.next;

            // Connect the predecessor to the second node.
            previous.next = secondNode;

            // Connect the first node to the remainder of the list.
            firstNode.next = secondNode.next;

            // Place the second node before the first node.
            secondNode.next = firstNode;

            /*
             * The original first node is now the tail of the swapped pair.
             * It becomes the predecessor for the next pair.
             */
            current = firstNode;
        }

        return sentinel.next;
    }

    /*
     * Helper method to build a linked list from an array.
     */
    private static ListNode buildList(int[] values) {

        ListNode sentinel = new ListNode();
        ListNode current = sentinel;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return sentinel.next;
    }

    /*
     * Helper method to print a linked list.
     */
    private static void printList(ListNode head) {

        while (head != null) {
            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }

    /*
     * Local test cases.
     */
    public static void main(String[] args) {

        SwapNodesInPairs solution = new SwapNodesInPairs();

        // Test 1: Even number of nodes.
        // Expected: 2 -> 1 -> 4 -> 3
        ListNode list1 = buildList(new int[]{1, 2, 3, 4});

        System.out.print("Test 1: ");
        printList(solution.swapPairs(list1));

        // Test 2: Odd number of nodes.
        // Expected: 2 -> 1 -> 4 -> 3 -> 5
        ListNode list2 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Test 2: ");
        printList(solution.swapPairs(list2));

        // Test 3: Single node.
        // Expected: 1
        ListNode list3 = buildList(new int[]{1});

        System.out.print("Test 3: ");
        printList(solution.swapPairs(list3));

        // Test 4: Empty list.
        // Expected: empty output
        ListNode list4 = null;

        System.out.print("Test 4: ");
        printList(solution.swapPairs(list4));

        // Test 5: Two nodes.
        // Expected: 2 -> 1
        ListNode list5 = buildList(new int[]{1, 2});

        System.out.print("Test 5: ");
        printList(solution.swapPairs(list5));
    }
}
