import java.util.Arrays;

/**
 * LeetCode 61: Rotate List
 *
 * Problem URL:
 * https://leetcode.com/problems/rotate-list/
 *
 * ============================================================
 * PROBLEM STATEMENT
 * ============================================================
 *
 * Given the head of a singly linked list, rotate the list to
 * the right by k positions.
 *
 * Example 1:
 *
 * Input:
 *   head = 1 -> 2 -> 3 -> 4 -> 5
 *   k = 2
 *
 * Output:
 *   4 -> 5 -> 1 -> 2 -> 3
 *
 * Explanation:
 *   The last two nodes move to the beginning of the list.
 *
 *
 * Example 2:
 *
 * Input:
 *   head = 0 -> 1 -> 2
 *   k = 4
 *
 * Output:
 *   2 -> 0 -> 1
 *
 * Explanation:
 *   The list has three nodes.
 *   Rotating right by four positions is equivalent to
 *   rotating right by one position because 4 % 3 = 1.
 *
 *
 * ============================================================
 * APPROACH 1: BRUTE FORCE
 * ============================================================
 *
 * One possible approach is to rotate the list one position
 * at a time, repeating the process k times.
 *
 * Each rotation requires finding the current tail and moving
 * it to the front.
 *
 * Time Complexity: O(n * k)
 * Auxiliary Space: O(1)
 *
 * This approach becomes inefficient when k is very large.
 *
 * We can improve it by observing that rotating a list of
 * length n by n positions produces the original list.
 *
 *
 * ============================================================
 * APPROACH 2: OPTIMIZED FAST-AND-SLOW POINTERS
 * ============================================================
 *
 * We use the length of the list and two pointers to identify
 * the new tail and new head.
 *
 * Step 1:
 *   Handle an empty list or a single-node list.
 *
 * Step 2:
 *   Calculate the length of the list.
 *
 * Step 3:
 *   Normalize k using k % length.
 *
 * Step 4:
 *   Return the original head if the effective rotation is zero.
 *
 * Step 5:
 *   Initialize fast and slow at the sentinel node.
 *
 * Step 6:
 *   Move fast forward by k positions.
 *
 * Step 7:
 *   Move both pointers until fast reaches the original tail.
 *
 * Step 8:
 *   At this point, slow points to the node immediately before
 *   the new head.
 *
 * Step 9:
 *   Break the list after slow.
 *
 * Step 10:
 *   Connect the original tail to the original head.
 *
 * Step 11:
 *   Return the new head.
 *
 *
 * ============================================================
 * WHY NORMALIZING k WORKS
 * ============================================================
 *
 * Rotating a list of length n by n positions returns the
 * original list.
 *
 * Therefore:
 *
 *   effectiveRotation = k % n
 *
 * Examples:
 *
 *   n = 5, k = 2  -> effective rotation = 2
 *   n = 5, k = 5  -> effective rotation = 0
 *   n = 5, k = 7  -> effective rotation = 2
 *   n = 3, k = 10 -> effective rotation = 1
 *
 * This prevents unnecessary work and ensures that we never
 * attempt to advance a pointer beyond the list.
 *
 *
 * ============================================================
 * WHY THE FAST-AND-SLOW POINTER APPROACH WORKS
 * ============================================================
 *
 * Consider:
 *
 *   sentinel -> 1 -> 2 -> 3 -> 4 -> 5
 *
 * Let k = 2.
 *
 * Initially:
 *
 *   fast = sentinel
 *   slow = sentinel
 *
 * Move fast forward by k positions:
 *
 *   fast -> 2
 *   slow -> sentinel
 *
 * The pointers now have a gap of two nodes.
 *
 * Advance both pointers until fast reaches the original tail:
 *
 *   sentinel -> 1 -> 2 -> 3 -> 4 -> 5
 *                        ^         ^
 *                       slow      fast
 *
 * Therefore:
 *
 *   slow.next is node 4, which becomes the new head.
 *   fast is node 5, which is the original tail.
 *
 * The reconnection operations are:
 *
 *   ListNode newHead = slow.next;
 *   slow.next = null;
 *   fast.next = originalHead;
 *
 * The result is:
 *
 *   4 -> 5 -> 1 -> 2 -> 3
 *
 *
 * ============================================================
 * IMPORTANT POINTER INVARIANTS
 * ============================================================
 *
 * sentinel:
 *   A dummy node placed before the original head.
 *   It simplifies pointer initialization and head management.
 *
 * fast:
 *   Initially advances k positions.
 *   Subsequently moves until it reaches the original tail.
 *
 * slow:
 *   Moves together with fast.
 *   When fast reaches the tail, slow identifies the new tail.
 *
 * originalHead:
 *   Preserves access to the original beginning of the list.
 *
 * newHead:
 *   Stores slow.next before the list is disconnected.
 *
 *
 * ============================================================
 * COMMON MISTAKES AND LESSONS LEARNED
 * ============================================================
 *
 * 1. NOT HANDLING k GREATER THAN THE LIST LENGTH
 *
 *    Incorrect approach:
 *
 *       for (int i = 0; i < k; i++) {
 *           fast = fast.next;
 *       }
 *
 *    Without first normalizing k, fast may become null.
 *    Accessing fast.next afterward would throw a
 *    NullPointerException.
 *
 *    Solution:
 *
 *       k = k % length;
 *
 *
 * 2. NOT HANDLING AN EMPTY LIST
 *
 *    If head is null, there are no nodes to rotate.
 *
 *    Return head before attempting any traversal.
 *
 *
 * 3. NOT HANDLING A SINGLE-NODE LIST
 *
 *    Rotating a single node cannot change the list.
 *
 *    Return head immediately.
 *
 *
 * 4. NOT HANDLING ZERO EFFECTIVE ROTATION
 *
 *    If k is zero or a multiple of the list length, the list
 *    should remain unchanged.
 *
 *    After calculating k % length, check whether k == 0.
 *
 *    This also prevents the original pointer logic from
 *    accidentally disconnecting the list when k is zero.
 *
 *
 * 5. LOSING THE ORIGINAL HEAD
 *
 *    Before changing links, save:
 *
 *       ListNode originalHead = sentinel.next;
 *
 *    This ensures that the original list remains accessible
 *    when the tail is connected to the original head.
 *
 *
 * 6. BREAKING THE LIST AT THE WRONG NODE
 *
 *    slow must point to the node immediately before the new
 *    head.
 *
 *    Therefore, the new head is slow.next.
 *
 *    We break the list using:
 *
 *       slow.next = null;
 *
 *
 * 7. FORGETTING TO CONNECT THE ORIGINAL TAIL
 *
 *    After breaking the list, connect the original tail to
 *    the original head:
 *
 *       fast.next = originalHead;
 *
 *    Otherwise, the two sections remain disconnected.
 *
 *
 * 8. RETURNING THE ORIGINAL HEAD
 *
 *    The original head is not necessarily the new head.
 *
 *    Return the saved newHead instead.
 *
 *
 * ============================================================
 * OPTIMIZATION NOTES
 * ============================================================
 *
 * The optimized algorithm runs in O(n) time and O(1)
 * auxiliary space.
 *
 * We cannot improve the asymptotic time complexity beyond
 * O(n) for this general linked-list problem because we may
 * need to inspect the list to determine its length and
 * locate the rotation boundary.
 *
 * The fast-and-slow pointer technique does not require
 * additional collections or arrays.
 *
 *
 * ============================================================
 * CORRECTNESS REASONING
 * ============================================================
 *
 * Let n be the list length and r = k % n.
 *
 * If r == 0, the original list is returned unchanged.
 *
 * Otherwise, advancing fast by r positions establishes the
 * required gap between fast and slow.
 *
 * When fast reaches the original tail, slow is positioned
 * immediately before the node that should become the new head.
 *
 * Breaking the link after slow divides the list into two
 * sections.
 *
 * Connecting the original tail to the original head places
 * the final r nodes before the original first n-r nodes.
 *
 * Therefore, the resulting list is correctly rotated right
 * by r positions.
 *
 *
 * ============================================================
 * COMPLEXITY ANALYSIS
 * ============================================================
 *
 * Time Complexity: O(n)
 *
 * We traverse the list to calculate its length and traverse
 * it again to locate the rotation boundary.
 *
 * Auxiliary Space: O(1)
 *
 * We use a constant number of pointers and integer variables.
 * The rotation is performed in place.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * 1. Why is k % length necessary?
 *
 * 2. Can this problem be solved by temporarily connecting
 *    the original tail to the original head?
 *
 * 3. How would you rotate a list to the left?
 *
 * 4. Why is a sentinel node useful?
 *
 * 5. How would you handle a doubly linked list?
 *
 * 6. What is the difference between rotating a list and
 *    reversing a list?
 *
 *
 * ============================================================
 * LOCAL TESTING
 * ============================================================
 *
 * The main method allows the implementation to be tested
 * locally without submitting it to LeetCode.
 */

public class RotateList {

    /**
     * Definition for a singly linked-list node.
     *
     * Nested here to make this file standalone.
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

    /**
     * Rotates the linked list to the right by k positions.
     *
     * @param head head of the original linked list
     * @param k    number of positions to rotate
     * @return head of the rotated linked list
     */
    public ListNode rotateRight(ListNode head, int k) {

        /*
         * Edge cases:
         *
         * An empty list has nothing to rotate.
         * A single-node list remains unchanged.
         *
         * Handle these cases before attempting pointer traversal.
         */
        if (head == null || head.next == null) {
            return head;
        }

        /*
         * Create a sentinel node before the original head.
         *
         * This makes pointer initialization convenient and
         * provides a stable reference before the list.
         */
        ListNode sentinel = new ListNode();
        sentinel.next = head;

        /*
         * Calculate the list length.
         *
         * Start tail at head and advance until it becomes null.
         * The length is used to normalize k.
         */
        int length = 0;
        ListNode tail = head;

        while (tail != null) {
            length++;
            tail = tail.next;
        }

        /*
         * Normalize the rotation count.
         *
         * Rotating by length positions returns the original list.
         * Therefore, only the remainder is relevant.
         */
        k = k % length;

        /*
         * No rotation is required if k is zero after normalization.
         *
         * Returning head here also prevents unnecessary pointer
         * manipulation when k is a multiple of the list length.
         */
        if (k == 0) {
            return head;
        }

        /*
         * Initialize both pointers at the sentinel.
         *
         * We use the sentinel as the starting position so that
         * slow can eventually identify the node immediately
         * before the new head.
         */
        ListNode fast = sentinel;
        ListNode slow = sentinel;

        /*
         * Move fast forward by k positions.
         *
         * This establishes a gap of k nodes between fast and slow.
         */
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        /*
         * Move both pointers together until fast reaches the
         * original tail.
         *
         * Since fast started k positions ahead, slow will finish
         * at the node immediately before the new head.
         */
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        /*
         * Preserve the original head before changing any links.
         */
        ListNode originalHead = sentinel.next;

        /*
         * Save the new head before breaking the list.
         *
         * The node immediately after slow becomes the new head.
         */
        ListNode newHead = slow.next;

        /*
         * Break the list immediately before the new head.
         *
         * The original first section now ends at slow.
         */
        slow.next = null;

        /*
         * Connect the original tail to the original head.
         *
         * This moves the final k nodes before the original
         * first section.
         */
        fast.next = originalHead;

        /*
         * Update the sentinel to reference the new head.
         *
         * Return the actual head, not the sentinel.
         */
        sentinel.next = newHead;

        return sentinel.next;
    }

    // =========================================================
    // LOCAL TESTING UTILITIES
    // =========================================================

    /**
     * Constructs a linked list from an array of integers.
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

    /**
     * Converts a linked list into an array for comparison.
     */
    private static int[] toArray(ListNode head) {

        int length = 0;

        for (ListNode current = head;
             current != null;
             current = current.next) {

            length++;
        }

        int[] values = new int[length];
        int index = 0;

        for (ListNode current = head;
             current != null;
             current = current.next) {

            values[index++] = current.val;
        }

        return values;
    }

    /**
     * Checks the actual result against the expected output.
     *
     * Throws an AssertionError if the test fails.
     */
    private static void assertListEquals(
            String testName,
            ListNode actual,
            int[] expected) {

        int[] actualValues = toArray(actual);

        if (!Arrays.equals(actualValues, expected)) {
            throw new AssertionError(
                testName
                + " failed. Expected: "
                + Arrays.toString(expected)
                + ", Actual: "
                + Arrays.toString(actualValues)
            );
        }

        System.out.println(
            "PASS: "
            + testName
            + " -> "
            + Arrays.toString(actualValues)
        );
    }

    /**
     * Standalone tests for the rotation algorithm.
     */
    public static void main(String[] args) {

        RotateList solution = new RotateList();

        /*
         * Test 1:
         *
         * Standard rotation.
         *
         * Input:  1 -> 2 -> 3 -> 4 -> 5
         * k = 2
         *
         * Expected: 4 -> 5 -> 1 -> 2 -> 3
         */
        ListNode test1 = buildList(
            new int[]{1, 2, 3, 4, 5}
        );

        assertListEquals(
            "Standard rotation",
            solution.rotateRight(test1, 2),
            new int[]{4, 5, 1, 2, 3}
        );

        /*
         * Test 2:
         *
         * Rotation count greater than list length.
         *
         * Input:  1 -> 2 -> 3
         * k = 5
         *
         * Effective rotation: 5 % 3 = 2
         *
         * Expected: 2 -> 3 -> 1
         */
        ListNode test2 = buildList(
            new int[]{1, 2, 3}
        );

        assertListEquals(
            "k greater than list length",
            solution.rotateRight(test2, 5),
            new int[]{2, 3, 1}
        );

        /*
         * Test 3:
         *
         * Rotation count equals the list length.
         *
         * Input:  1 -> 2 -> 3
         * k = 3
         *
         * Effective rotation: 3 % 3 = 0
         *
         * Expected: 1 -> 2 -> 3
         */
        ListNode test3 = buildList(
            new int[]{1, 2, 3}
        );

        assertListEquals(
            "k equals list length",
            solution.rotateRight(test3, 3),
            new int[]{1, 2, 3}
        );

        /*
         * Test 4:
         *
         * Zero rotation.
         *
         * The list must remain unchanged.
         */
        ListNode test4 = buildList(
            new int[]{1, 2, 3}
        );

        assertListEquals(
            "Zero rotation",
            solution.rotateRight(test4, 0),
            new int[]{1, 2, 3}
        );

        /*
         * Test 5:
         *
         * Single-node list.
         *
         * Rotating one node cannot change the list.
         */
        ListNode test5 = buildList(
            new int[]{1}
        );

        assertListEquals(
            "Single-node list",
            solution.rotateRight(test5, 10),
            new int[]{1}
        );

        /*
         * Test 6:
         *
         * Empty list.
         *
         * Expected output is also empty.
         */
        assertListEquals(
            "Empty list",
            solution.rotateRight(null, 2),
            new int[]{}
        );

        /*
         * Test 7:
         *
         * Rotation by more than one full cycle.
         *
         * Input:  1 -> 2 -> 3 -> 4 -> 5
         * k = 12
         *
         * Effective rotation: 12 % 5 = 2
         *
         * Expected: 4 -> 5 -> 1 -> 2 -> 3
         */
        ListNode test7 = buildList(
            new int[]{1, 2, 3, 4, 5}
        );

        assertListEquals(
            "Multiple full rotations",
            solution.rotateRight(test7, 12),
            new int[]{4, 5, 1, 2, 3}
        );

        System.out.println("\nAll tests passed.");
    }
}
