/*
 * LeetCode Problem: 86. Partition List
 * URL: https://leetcode.com/problems/partition-list/
 *
 * Topic: Linked Lists
 * Pattern: Two Linked Lists + Dummy Nodes + Tail Pointers
 *
 * Problem:
 * Given the head of a linked list and a value x, partition the list
 * so that all nodes with values less than x appear before nodes with
 * values greater than or equal to x.
 *
 * The relative order of nodes within each partition must be preserved.
 *
 * Example:
 * Input:  head = [1, 4, 3, 2, 5, 2], x = 3
 * Output: [1, 2, 2, 4, 3, 5]
 *
 * Brute Force:
 * Store the nodes in an auxiliary collection, separate them into
 * two groups, and reconstruct the list.
 *
 * This requires O(n) additional space.
 *
 * Optimized Approach:
 * Maintain two linked lists while traversing the original list:
 *
 * 1. Smaller list: nodes whose values are less than x.
 * 2. Larger list: nodes whose values are greater than or equal to x.
 *
 * Use a dummy node and a tail pointer for each list.
 *
 * For every original node:
 * 1. Save its next node before modifying any links.
 * 2. Append the current node to the appropriate partition.
 * 3. Detach the current node from the original chain.
 * 4. Advance the corresponding tail pointer.
 * 5. Advance the traversal pointer using the saved next node.
 *
 * Finally, connect the smaller partition to the larger partition.
 *
 * Key Insight:
 * We do not need to sort the list.
 *
 * We only need to classify each node into one of two groups while
 * preserving the order in which the nodes are encountered.
 *
 * Why Dummy Nodes?
 * A dummy node provides a stable starting point for each partition.
 * This eliminates special handling for the first node inserted
 * into an otherwise empty partition.
 *
 * Why Save current.next?
 * We modify current.next while detaching nodes.
 * Saving the original next reference first ensures that we can
 * continue traversing the remaining nodes without losing them.
 *
 * Why Advance the Tail?
 * tail.next = current connects the new node to the partition.
 * tail = current advances the tail to the newly appended node.
 *
 * These operations are different:
 * - Changing tail.next modifies a linked-list connection.
 * - Changing tail moves a reference to another node.
 *
 * Common Mistakes:
 * 1. Assigning tail = current without connecting tail.next first.
 *    This moves the pointer but does not append the node.
 *
 * 2. Advancing current before saving current.next.
 *    Modifying links prematurely can lose access to the remaining list.
 *
 * 3. Setting current.next = current.
 *    This creates a cycle in the linked list.
 *
 * 4. Connecting the partitions to the larger dummy node rather than
 *    to largerGroup.next.
 *    This accidentally includes the dummy node in the result.
 *
 * 5. Using current.val > x instead of current.val >= x for the
 *    second partition. Values equal to x belong in the second group.
 *
 * 6. Forgetting to detach each node before continuing.
 *    Stale connections can produce unintended links or cycles.
 *
 * Complexity:
 * Time: O(n), because each node is processed exactly once.
 * Auxiliary Space: O(1), because only a constant number of pointers
 * and dummy nodes are used. The original nodes are reused.
 *
 * Interview Follow-ups:
 * 1. Can this be solved without dummy nodes?
 * 2. Why must the relative order within each partition be preserved?
 * 3. What happens if every node is less than x?
 * 4. What happens if every node is greater than or equal to x?
 * 5. How would the approach change if the nodes were stored in an
 *    array rather than a linked list?
 */

public class PartitionList {

    /*
     * Minimal ListNode definition for local testing.
     *
     * On LeetCode, the platform supplies the ListNode definition.
     * Here, we define it ourselves so this file can run independently.
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
     * Partitions a linked list around x while preserving the relative
     * order of nodes within both partitions.
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(1)
     */
    public ListNode partition(ListNode head, int x) {

        // An empty list or a single-node list is already partitioned.
        if (head == null || head.next == null) {
            return head;
        }

        // Traverse the original list from left to right.
        ListNode current = head;

        /*
         * Dummy node for the partition containing values < x.
         *
         * smallerGroup remains at the beginning of the partition.
         * smallerTail always points to its last actual node, or to
         * the dummy node when the partition is empty.
         */
        ListNode smallerGroup = new ListNode();
        ListNode smallerTail = smallerGroup;

        /*
         * Dummy node for the partition containing values >= x.
         *
         * largerGroup remains at the beginning of the partition.
         * largerTail always points to its last actual node, or to
         * the dummy node when the partition is empty.
         */
        ListNode largerGroup = new ListNode();
        ListNode largerTail = largerGroup;

        while (current != null) {

            /*
             * CRITICAL:
             * Save the next original node before changing current.next.
             *
             * We need this reference to continue traversing the original
             * list after detaching the current node.
             */
            ListNode nextNode = current.next;

            if (current.val < x) {

                // Attach current to the end of the smaller partition.
                smallerTail.next = current;

                /*
                 * Detach current from the remaining original list.
                 *
                 * nextNode preserves access to the unprocessed nodes.
                 */
                current.next = null;

                // The appended node becomes the new tail.
                smallerTail = current;

            } else {

                // Attach current to the end of the larger partition.
                largerTail.next = current;

                // Detach current from the remaining original list.
                current.next = null;

                // The appended node becomes the new tail.
                largerTail = current;
            }

            // Continue with the next node from the original list.
            current = nextNode;
        }

        /*
         * Join the partitions.
         *
         * largerGroup is a dummy node, so largerGroup.next points to
         * the first actual node in the larger partition.
         *
         * If the larger partition is empty, largerGroup.next is null.
         *
         * If the smaller partition is empty, smallerTail is still the
         * smaller dummy node, so the connection also works correctly.
         */
        smallerTail.next = largerGroup.next;

        /*
         * Return the first actual node in the smaller partition.
         * Do not return the dummy node itself.
         */
        return smallerGroup.next;
    }

    /*
     * Helper method for local testing.
     * Constructs a linked list from an integer array.
     */
    private static ListNode buildList(int[] values) {

        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    /*
     * Helper method for local testing.
     * Prints the values in a linked list.
     */
    private static void printList(ListNode head) {

        ListNode current = head;

        while (current != null) {
            System.out.print(current.val);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println(" -> null");
    }

    /*
     * Local test cases.
     */
    public static void main(String[] args) {

        PartitionList solution = new PartitionList();

        // Test 1: Standard example.
        ListNode list1 = buildList(
                new int[]{1, 4, 3, 2, 5, 2}
        );

        System.out.println("Test 1: x = 3");
        printList(solution.partition(list1, 3));
        // Expected: 1 -> 2 -> 2 -> 4 -> 3 -> 5 -> null


        // Test 2: Preserve relative order in both partitions.
        ListNode list2 = buildList(
                new int[]{7, 2, 5, 1, 8, 3, 6, 4}
        );

        System.out.println("Test 2: x = 5");
        printList(solution.partition(list2, 5));
        // Expected: 2 -> 1 -> 3 -> 4 -> 7 -> 5 -> 8 -> 6 -> null


        // Test 3: Every node belongs to the smaller partition.
        ListNode list3 = buildList(
                new int[]{1, 2, 1}
        );

        System.out.println("Test 3: x = 5");
        printList(solution.partition(list3, 5));
        // Expected: 1 -> 2 -> 1 -> null


        // Test 4: Every node belongs to the larger partition.
        ListNode list4 = buildList(
                new int[]{5, 7, 6}
        );

        System.out.println("Test 4: x = 3");
        printList(solution.partition(list4, 3));
        // Expected: 5 -> 7 -> 6 -> null


        // Test 5: Nodes equal to x belong to the larger partition.
        ListNode list5 = buildList(
                new int[]{3, 1, 3, 2, 3}
        );

        System.out.println("Test 5: x = 3");
        printList(solution.partition(list5, 3));
        // Expected: 1 -> 2 -> 3 -> 3 -> 3 -> null


        // Test 6: Empty list.
        System.out.println("Test 6: Empty list");
        printList(solution.partition(null, 3));
        // Expected: null
    }
}
