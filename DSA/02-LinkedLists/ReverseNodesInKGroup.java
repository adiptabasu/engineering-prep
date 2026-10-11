import java.util.Arrays;

/**
 * LeetCode 25: Reverse Nodes in k-Group
 *
 * Problem URL:
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * ============================================================
 * PROBLEM STATEMENT
 * ============================================================
 *
 * Given the head of a singly linked list, reverse the nodes
 * of the list k at a time and return the modified list.
 *
 * If the number of remaining nodes is less than k, those nodes
 * must remain in their original order.
 *
 * IMPORTANT:
 * We must modify the actual node links, not merely swap the
 * values stored in the nodes.
 *
 * Example 1:
 *
 * Input:
 *   head = 1 -> 2 -> 3 -> 4 -> 5
 *   k = 2
 *
 * Output:
 *   2 -> 1 -> 4 -> 3 -> 5
 *
 * Explanation:
 *   Group 1: 1 -> 2 becomes 2 -> 1
 *   Group 2: 3 -> 4 becomes 4 -> 3
 *   Remaining node 5 stays unchanged.
 *
 *
 * Example 2:
 *
 * Input:
 *   head = 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
 *   k = 3
 *
 * Output:
 *   3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7
 *
 * Explanation:
 *   Group 1: 1 -> 2 -> 3 becomes 3 -> 2 -> 1
 *   Group 2: 4 -> 5 -> 6 becomes 6 -> 5 -> 4
 *   Remaining node 7 stays unchanged.
 *
 *
 * ============================================================
 * APPROACH 1: BRUTE FORCE
 * ============================================================
 *
 * One possible approach would be to:
 *
 * 1. Store the node references in an array or list.
 * 2. Identify groups of k nodes.
 * 3. Rearrange the references within each complete group.
 * 4. Reconstruct the linked list.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(n)
 *
 * Although this approach can work, it requires additional
 * memory to store the node references.
 *
 *
 * ============================================================
 * APPROACH 2: OPTIMIZED IN-PLACE REVERSAL
 * ============================================================
 *
 * We reverse each group directly in the linked list.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1)
 *
 * The algorithm processes one group at a time.
 *
 * For each group:
 *
 * 1. Identify the first and last nodes of the group.
 * 2. Confirm that exactly k nodes are available.
 * 3. Save the first node after the group.
 * 4. Temporarily isolate the group.
 * 5. Reverse the isolated group.
 * 6. Connect the previous section to the reversed group.
 * 7. Connect the reversed group's tail to the remaining section.
 * 8. Advance to the next group.
 *
 *
 * ============================================================
 * KEY POINTERS
 * ============================================================
 *
 * sentinel:
 *   A dummy node placed before the original head.
 *
 *   It simplifies reconnection when the first group is reversed,
 *   because the head of the entire list may change.
 *
 * groupPrev:
 *   The node immediately before the group currently being
 *   processed.
 *
 * startOfReverse:
 *   The first node of the group before reversal.
 *
 *   IMPORTANT:
 *   After reversal, this node becomes the TAIL of the group.
 *
 * lastNodeOfReverse:
 *   The last node of the group before reversal.
 *
 * remainingSection:
 *   The first node immediately after the current group.
 *
 * reversedSection:
 *   The new head of the reversed group.
 *
 *
 * ============================================================
 * WHY THE ORDER OF OPERATIONS MATTERS
 * ============================================================
 *
 * Consider:
 *
 * sentinel -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *
 * With k = 3, we want:
 *
 * sentinel -> 3 -> 2 -> 1 -> 4 -> 5 -> 6
 *
 * STEP 1: SAVE THE REMAINING SECTION
 *
 * remainingSection = lastNodeOfReverse.next;
 *
 * This preserves our reference to node 4.
 *
 * If we overwrite node 3's next pointer before saving this
 * reference, we risk losing access to the remaining nodes.
 *
 *
 * STEP 2: ISOLATE THE CURRENT GROUP
 *
 * lastNodeOfReverse.next = null;
 *
 * The group becomes:
 *
 * 1 -> 2 -> 3 -> null
 *
 * The remaining section is still accessible through
 * remainingSection:
 *
 * remainingSection -> 4 -> 5 -> 6
 *
 *
 * STEP 3: REVERSE THE ISOLATED GROUP
 *
 * reversedSection = reverse(startOfReverse);
 *
 * The isolated group becomes:
 *
 * 3 -> 2 -> 1 -> null
 *
 * reversedSection points to node 3.
 *
 * startOfReverse still points to node 1, which is now the tail.
 *
 *
 * STEP 4: CONNECT THE PREVIOUS SECTION
 *
 * groupPrev.next = reversedSection;
 *
 * This connects the node before the group to its new head.
 *
 * For the first group, groupPrev is the sentinel.
 *
 *
 * STEP 5: CONNECT THE REMAINING SECTION
 *
 * startOfReverse.next = remainingSection;
 *
 * Because startOfReverse now points to the tail of the reversed
 * group, this reconnects the reversed group to the rest of
 * the list.
 *
 *
 * STEP 6: ADVANCE TO THE NEXT GROUP
 *
 * groupPrev = startOfReverse;
 *
 * The original group head becomes the new tail after reversal.
 * Therefore, it is immediately before the next group.
 *
 *
 * ============================================================
 * COMMON MISTAKES AND LESSONS LEARNED
 * ============================================================
 *
 * 1. OFF-BY-ONE ERRORS WHILE FINDING THE GROUP END
 *
 *    We start lastNodeOfReverse at startOfReverse.
 *
 *    Since the first node is already counted, we move forward
 *    only k - 1 times.
 *
 *    The loop is:
 *
 *       for (int i = 1; i < k; i++)
 *
 *    After these movements, lastNodeOfReverse points to the
 *    last node inside the group.
 *
 *
 * 2. NOT DETECTING AN INCOMPLETE GROUP
 *
 *    Consider:
 *
 *       1 -> 2
 *
 *    with k = 3.
 *
 *    The algorithm must not reverse this group.
 *
 *    We check whether lastNodeOfReverse or its next node
 *    is null before advancing.
 *
 *    If there are not enough nodes, fullGroup becomes false,
 *    and we stop processing without modifying the group.
 *
 *
 * 3. SAVING THE REMAINING SECTION TOO LATE
 *
 *    Always save:
 *
 *       remainingSection = lastNodeOfReverse.next;
 *
 *    before severing the link at the end of the group.
 *
 *    Otherwise, we could lose the reference to the remaining
 *    nodes.
 *
 *
 * 4. DISCONNECTING groupPrev UNNECESSARILY
 *
 *    Setting:
 *
 *       groupPrev.next = null;
 *
 *    is unnecessary for this implementation.
 *
 *    Although a temporary disconnection can be made safely
 *    if all references are preserved and the list is correctly
 *    reconnected, it introduces another opportunity to lose
 *    track of the list.
 *
 *    We only need to terminate the group by setting:
 *
 *       lastNodeOfReverse.next = null;
 *
 *
 * 5. FORGETTING TO RECONNECT BOTH SIDES
 *
 *    Reversing the group is not enough.
 *
 *    We must reconnect:
 *
 *       groupPrev.next = reversedSection;
 *       startOfReverse.next = remainingSection;
 *
 *    The first statement connects the previous section to the
 *    reversed group's head.
 *
 *    The second statement connects the reversed group's tail
 *    to the remaining section.
 *
 *
 * 6. ADVANCING groupPrev TO THE WRONG NODE
 *
 *    Incorrect:
 *
 *       groupPrev = remainingSection;
 *
 *    This skips the node immediately before the next group.
 *
 *    Correct:
 *
 *       groupPrev = startOfReverse;
 *
 *    The original group head becomes the tail after reversal.
 *
 *
 * 7. USING AN INAPPROPRIATE OUTER LOOP CONDITION
 *
 *    We use:
 *
 *       while (groupPrev != null && groupPrev.next != null)
 *
 *    This prevents processing another group when no nodes
 *    remain.
 *
 *    The sentinel ensures groupPrev initially refers to a
 *    valid node, and after each successful iteration it moves
 *    to the tail of the reversed group.
 *
 *
 * 8. FORGETTING THE k = 1 CASE
 *
 *    When k = 1, every group contains one node.
 *
 *    The algorithm leaves the list unchanged.
 *
 *    It performs unnecessary work in this case, but remains
 *    correct. A special-case optimization could return head
 *    immediately when k == 1.
 *
 *
 * ============================================================
 * CORRECTNESS REASONING
 * ============================================================
 *
 * At the start of each iteration:
 *
 * 1. groupPrev points to the node immediately before the
 *    next group.
 *
 * 2. The portion of the list before groupPrev is already
 *    correctly processed.
 *
 * 3. The unprocessed suffix remains connected and accessible.
 *
 * If fewer than k nodes remain, the algorithm stops without
 * modifying those nodes.
 *
 * Otherwise:
 *
 * 1. It reverses exactly k nodes.
 * 2. It reconnects the previous section to the new group head.
 * 3. It reconnects the group tail to the remaining suffix.
 * 4. It advances groupPrev to the new group tail.
 *
 * Therefore, every complete group is reversed exactly once,
 * the order within incomplete final groups is preserved,
 * and the entire list remains connected.
 *
 *
 * ============================================================
 * COMPLEXITY ANALYSIS
 * ============================================================
 *
 * Let n be the number of nodes.
 *
 * Time Complexity: O(n)
 *
 * Each node is examined a constant number of times during
 * group identification and reversal.
 *
 * Auxiliary Space: O(1)
 *
 * The algorithm uses a constant number of node references.
 * Reversal happens in place without an additional collection.
 *
 * The output is the modified linked list itself, so no
 * separate output collection is required.
 *
 *
 * ============================================================
 * INTERVIEW FOLLOW-UPS
 * ============================================================
 *
 * 1. What changes if the final incomplete group must also
 *    be reversed?
 *
 * 2. Can the algorithm be implemented recursively?
 *    What would the recursive space complexity be?
 *
 * 3. How would you reverse nodes between positions left and
 *    right?
 *
 * 4. How is this problem related to Swap Nodes in Pairs?
 *
 *    When k = 2, this problem reduces to reversing each pair
 *    of nodes.
 *
 * 5. Why is a sentinel/dummy node useful?
 *
 *    It provides a stable node before the head, simplifying
 *    reconnection when the original head changes.
 *
 * 6. Can we avoid isolating the group by setting its tail's
 *    next pointer to null?
 *
 *    Yes. Other implementations reverse a fixed number of
 *    nodes while maintaining a reference to the remaining
 *    section. However, explicitly isolating the group makes
 *    the boundaries and reuse of the reverse() helper easier
 *    to understand.
 *
 *
 * ============================================================
 * LOCAL TESTING
 * ============================================================
 *
 * The main() method below allows the algorithm to be tested
 * without submitting it to LeetCode.
 */

public class ReverseNodesInKGroup {

    /**
     * Definition for a singly linked-list node.
     *
     * The class is nested here so that this file is standalone
     * and can be compiled without another ListNode.java file.
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
     * Reverses the linked list in groups of k nodes.
     *
     * Incomplete groups at the end remain unchanged.
     *
     * @param head head of the original linked list
     * @param k    number of nodes in each group
     * @return head of the modified linked list
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        /*
         * A sentinel simplifies handling the first group.
         *
         * Without it, reversing the first group would require
         * separate logic to update the original head.
         *
         * The problem guarantees k >= 1.
         */
        ListNode sentinel = new ListNode();
        sentinel.next = head;

        /*
         * groupPrev is the node immediately before the group
         * currently being processed.
         *
         * Initially, this is the sentinel.
         */
        ListNode groupPrev = sentinel;

        /*
         * Continue while there is a node after groupPrev.
         *
         * groupPrev remains a valid node throughout the
         * algorithm: it starts at the sentinel and then moves
         * to the tail of each successfully reversed group.
         */
        while (groupPrev != null && groupPrev.next != null) {

            /*
             * Remember the first node of the current group.
             *
             * This node becomes the tail after reversal.
             * We retain its reference because we need it later
             * to reconnect the remaining section.
             */
            ListNode startOfReverse = groupPrev.next;

            /*
             * Initially, the first node is also our candidate
             * for the last node of the group.
             */
            ListNode lastNodeOfReverse = startOfReverse;

            /*
             * Assume the group is complete until we discover
             * that fewer than k nodes remain.
             */
            boolean fullGroup = true;

            /*
             * The first node is already counted.
             *
             * Move forward k - 1 times to reach the last node
             * of a group containing k nodes.
             *
             * Example: k = 3
             *
             * Start: node 1
             * Move 1: node 2
             * Move 2: node 3
             *
             * The candidate now points to the third node.
             *
             * Before advancing, verify that another node exists.
             * This prevents the pointer from moving past the end
             * of an incomplete group.
             */
            for (int i = 1; i < k; i++) {

                if (lastNodeOfReverse == null ||
                    lastNodeOfReverse.next == null) {

                    fullGroup = false;
                    break;
                }

                lastNodeOfReverse = lastNodeOfReverse.next;
            }

            /*
             * If the group is incomplete, stop.
             *
             * Do not reverse the remaining nodes because the
             * problem requires incomplete final groups to retain
             * their original order.
             *
             * No pointers have been modified at this point,
             * so the remaining list is still intact.
             */
            if (!fullGroup) {
                break;
            }

            /*
             * Save the node immediately after the current group.
             *
             * IMPORTANT:
             * Save this reference BEFORE modifying lastNodeOfReverse.next.
             *
             * Example:
             *
             * 1 -> 2 -> 3 -> 4 -> 5 -> 6
             *
             * remainingSection points to node 4.
             *
             * Without this saved reference, terminating the group
             * could cause us to lose access to the remaining nodes.
             */
            ListNode remainingSection = lastNodeOfReverse.next;

            /*
             * Temporarily isolate the group.
             *
             * The group becomes:
             *
             * 1 -> 2 -> 3 -> null
             *
             * The rest of the list remains accessible through
             * remainingSection.
             *
             * We do not need to set groupPrev.next to null.
             * Keeping the existing reference is harmless because
             * the reverse() helper follows the isolated group's
             * own next pointers and stops at null.
             */
            lastNodeOfReverse.next = null;

            /*
             * Reverse the isolated group using the standard
             * three-pointer reversal algorithm.
             *
             * Before:
             * 1 -> 2 -> 3 -> null
             *
             * After:
             * 3 -> 2 -> 1 -> null
             *
             * reversedSection points to node 3.
             *
             * startOfReverse still points to node 1, which is
             * now the tail of the reversed group.
             */
            ListNode reversedSection = reverse(startOfReverse);

            /*
             * Reconnect the previous section to the reversed group.
             *
             * Initially, groupPrev is the sentinel.
             *
             * After processing the first group, groupPrev will
             * point to the tail of the previous reversed group.
             *
             * This connection makes the new head of the current
             * group reachable from the beginning of the list.
             */
            groupPrev.next = reversedSection;

            /*
             * Reconnect the tail of the reversed group to the
             * remaining section.
             *
             * startOfReverse was the original group head.
             * After reversal, it is the group tail.
             *
             * Example:
             *
             * Before reconnection:
             * 3 -> 2 -> 1 -> null
             *
             * remainingSection -> 4 -> 5 -> 6
             *
             * After reconnection:
             * 3 -> 2 -> 1 -> 4 -> 5 -> 6
             *
             * This connection is essential. Without it, the
             * remaining nodes would be disconnected from the
             * reversed group.
             */
            startOfReverse.next = remainingSection;

            /*
             * Advance to the next group.
             *
             * IMPORTANT:
             * Do not assign groupPrev = remainingSection.
             *
             * remainingSection points to the first node of the
             * next group, but groupPrev must point to the node
             * immediately BEFORE that group.
             *
             * The original group head is now its tail, so
             * startOfReverse is the correct next groupPrev.
             */
            groupPrev = startOfReverse;
        }

        /*
         * The sentinel itself is not part of the original list.
         * Return the actual head of the modified list.
         */
        return sentinel.next;
    }

    /**
     * Reverses a linked list in place.
     *
     * This helper is used after the current group has been
     * isolated by setting its last node's next pointer to null.
     *
     * @param head head of the section to reverse
     * @return new head of the reversed section
     */
    private ListNode reverse(ListNode head) {

        /*
         * prev is initially null because the original head
         * becomes the tail of the reversed list.
         */
        ListNode prev = null;

        ListNode current = head;

        while (current != null) {

            /*
             * Save the next node before reversing the current link.
             *
             * If we overwrite current.next without saving it,
             * we lose access to the rest of the list.
             */
            ListNode temp = current.next;

            /*
             * Reverse the current node's link.
             */
            current.next = prev;

            /*
             * Advance prev and current.
             */
            prev = current;
            current = temp;
        }

        /*
         * When current becomes null, prev points to the new head.
         */
        return prev;
    }

    // =========================================================
    // LOCAL TESTING UTILITIES
    // =========================================================

    /**
     * Constructs a linked list from an array of values.
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
     * Converts a linked list into an array for easier comparison.
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
     * Executes a test and throws an exception if the result
     * differs from the expected output.
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
     * Standalone test cases.
     *
     * Run this main method to verify the implementation locally.
     */
    public static void main(String[] args) {

        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

        /*
         * Test 1:
         *
         * Even number of nodes, k = 2.
         *
         * Input:  1 -> 2 -> 3 -> 4 -> 5
         * Output: 2 -> 1 -> 4 -> 3 -> 5
         *
         * The final node remains unchanged because it does not
         * belong to a complete group of two.
         */
        ListNode test1 = buildList(new int[]{1, 2, 3, 4, 5});

        assertListEquals(
            "Even group size, incomplete final group",
            solution.reverseKGroup(test1, 2),
            new int[]{2, 1, 4, 3, 5}
        );

        /*
         * Test 2:
         *
         * Seven nodes, k = 3.
         *
         * Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
         * Output: 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7
         *
         * The final node must remain unchanged.
         */
        ListNode test2 = buildList(
            new int[]{1, 2, 3, 4, 5, 6, 7}
        );

        assertListEquals(
            "Multiple groups with an incomplete final group",
            solution.reverseKGroup(test2, 3),
            new int[]{3, 2, 1, 6, 5, 4, 7}
        );

        /*
         * Test 3:
         *
         * Fewer than k nodes.
         *
         * Input:  1 -> 2
         * k = 3
         *
         * Expected output: 1 -> 2
         *
         * No group is reversed because the list is too short.
         */
        ListNode test3 = buildList(new int[]{1, 2});

        assertListEquals(
            "Fewer nodes than k",
            solution.reverseKGroup(test3, 3),
            new int[]{1, 2}
        );

        /*
         * Test 4:
         *
         * k = 1.
         *
         * Each group contains one node, so the list must remain
         * unchanged.
         */
        ListNode test4 = buildList(new int[]{1, 2, 3, 4});

        assertListEquals(
            "k equals one",
            solution.reverseKGroup(test4, 1),
            new int[]{1, 2, 3, 4}
        );

        /*
         * Test 5:
         *
         * Empty list.
         *
         * The result must also be an empty list.
         */
        ListNode test5 = null;

        assertListEquals(
            "Empty list",
            solution.reverseKGroup(test5, 3),
            new int[]{}
        );

        /*
         * Test 6:
         *
         * Exactly one complete group.
         *
         * Input:  1 -> 2 -> 3
         * k = 3
         *
         * Output: 3 -> 2 -> 1
         */
        ListNode test6 = buildList(new int[]{1, 2, 3});

        assertListEquals(
            "Exactly one complete group",
            solution.reverseKGroup(test6, 3),
            new int[]{3, 2, 1}
        );

        System.out.println("\nAll tests passed.");
    }
}
