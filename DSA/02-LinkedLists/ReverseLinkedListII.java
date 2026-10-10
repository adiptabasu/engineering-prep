/*
 * LeetCode Problem: Reverse Linked List II
 * URL: https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * Problem:
 * Given the head of a singly linked list and two positions, left and right,
 * reverse the nodes from position left to position right, inclusive.
 *
 * Positions are 1-indexed.
 *
 * Example:
 * Input:
 *   head = 1 -> 2 -> 3 -> 4 -> 5
 *   left = 2
 *   right = 4
 *
 * Output:
 *   1 -> 4 -> 3 -> 2 -> 5
 *
 * ---------------------------------------------------------------
 * APPROACH 1: IN-PLACE SUBLIST REVERSAL
 * ---------------------------------------------------------------
 *
 * Idea:
 * Reverse the required section in place by repeatedly moving the node
 * immediately after the beginning of the section to the front.
 *
 * Steps:
 * 1. Create a dummy node pointing to the original head.
 * 2. Find the node immediately before the reversal section.
 * 3. Keep a pointer to the first node of the section.
 * 4. Repeatedly extract the node after the section's current head and
 *    insert it immediately after the node before the section.
 *
 * Key Insight:
 * Instead of reversing the entire sublist and reconnecting it afterward,
 * move one node at a time to the front of the sublist.
 *
 * Why It Works:
 * Each iteration moves the next unreversed node to the front of the
 * reversed portion. After right - left iterations, the selected section
 * is reversed, and the rest of the list remains connected.
 *
 * Complexity:
 * Time: O(n)
 * Auxiliary Space: O(1)
 *
 * ---------------------------------------------------------------
 * APPROACH 2: SPLIT -> REVERSE -> RECONNECT
 * ---------------------------------------------------------------
 *
 * This is the approach developed during learning.
 *
 * Steps:
 * 1. Use a dummy node to simplify handling left == 1.
 * 2. Find the node immediately before the section.
 * 3. Separate the first section from the reversal section.
 * 4. Find the end of the reversal section and preserve the remaining list.
 * 5. Reverse the middle section using the standard linked-list reversal.
 * 6. Reconnect the reversed section to the last section.
 * 7. Reconnect the first section to the reversed section.
 *
 * Key Insight:
 * The original first node of the reversed section becomes its tail.
 * The dummy node ensures there is always a node before the section.
 *
 * Why It Works:
 * The three sections are preserved independently, the middle section
 * is reversed, and the saved references allow all sections to be joined.
 *
 * Complexity:
 * Time: O(n)
 * Auxiliary Space: O(1)
 *
 * ---------------------------------------------------------------
 * COMMON MISTAKES
 * ---------------------------------------------------------------
 *
 * 1. Forgetting that positions are 1-indexed.
 * 2. Losing access to the node after the reversal section.
 * 3. Forgetting to reconnect the first section.
 * 4. Returning the original head when the first node has changed.
 * 5. Setting the wrong node's next pointer to null.
 * 6. Traversing until current == null and then attempting current.next.
 *
 * ---------------------------------------------------------------
 * INTERVIEW FOLLOW-UPS
 * ---------------------------------------------------------------
 *
 * 1. Why is a dummy node useful?
 * 2. Can the sublist be reversed without explicitly splitting the list?
 * 3. What happens when left == 1?
 * 4. What happens when left == right?
 * 5. Can the solution use O(1) auxiliary space?
 */

public class ReverseLinkedListII {

    /*
     * LeetCode supplies this class automatically.
     * It is included here so the examples can run locally.
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
     * APPROACH 1: IN-PLACE SUBLIST REVERSAL
     *
     * This is the recommended interview solution.
     *
     * Rather than separating the list into three sections, we repeatedly
     * move the node after the sublist's current head to the front.
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        // The dummy node handles reversal beginning at position 1.
        ListNode sentinel = new ListNode(0, head);

        // Find the node immediately before the reversal section.
        ListNode beforeSection = sentinel;

        for (int i = 1; i < left; i++) {
            beforeSection = beforeSection.next;
        }

        // This node remains the tail of the reversed section.
        ListNode sectionHead = beforeSection.next;

        /*
         * Move the node after sectionHead to the front of the section.
         *
         * Before:
         * beforeSection -> 2 -> 3 -> 4 -> 5
         *
         * After one iteration:
         * beforeSection -> 3 -> 2 -> 4 -> 5
         *
         * After two iterations:
         * beforeSection -> 4 -> 3 -> 2 -> 5
         */
        for (int i = 0; i < right - left; i++) {

            ListNode nodeToMove = sectionHead.next;

            // Remove nodeToMove from its current position.
            sectionHead.next = nodeToMove.next;

            // Insert nodeToMove immediately after beforeSection.
            nodeToMove.next = beforeSection.next;
            beforeSection.next = nodeToMove;
        }

        return sentinel.next;
    }

    /*
     * APPROACH 2: SPLIT -> REVERSE -> RECONNECT
     *
     * This preserves the approach developed during learning.
     *
     * The list is separated into three sections, the middle is reversed
     * using reverseLinkedList(), and the sections are reconnected.
     */
    public ListNode reverseBetweenSplitReverseReconnect(
            ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        // Find the node immediately before the reversal section.
        ListNode preSplitPoint = sentinel;

        for (int i = 1; i < left; i++) {
            preSplitPoint = preSplitPoint.next;
        }

        // Save the first node of the reversal section.
        ListNode reverseSection = preSplitPoint.next;

        // Separate the first section from the middle section.
        preSplitPoint.next = null;

        // Find the last node of the reversal section.
        ListNode current = reverseSection;

        for (int i = 0; i < right - left; i++) {
            current = current.next;
        }

        // Preserve the section following the reversal.
        ListNode lastSection = current.next;

        // Separate the middle section from the last section.
        current.next = null;

        /*
         * Reverse the middle section.
         *
         * reverseSection is reassigned to the new head.
         * The original first node of the middle section becomes its tail.
         */
        reverseSection = reverseLinkedList(reverseSection);

        /*
         * Find the tail of the reversed section and reconnect the last
         * section.
         */
        current = reverseSection;

        while (current.next != null) {
            current = current.next;
        }

        current.next = lastSection;

        // Reconnect the first section to the reversed section.
        preSplitPoint.next = reverseSection;

        return sentinel.next;
    }

    /*
     * Standard linked-list reversal.
     *
     * This helper is used by Approach 2.
     */
    private ListNode reverseLinkedList(ListNode head) {

        ListNode current = head;
        ListNode previous = null;

        while (current != null) {

            ListNode nextNode = current.next;

            current.next = previous;

            previous = current;
            current = nextNode;
        }

        return previous;
    }

    /*
     * Helper method to build a linked list from an array.
     */
    private static ListNode buildList(int[] values) {

        ListNode sentinel = new ListNode(0);
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
     * Local tests for both approaches.
     */
    public static void main(String[] args) {

        ReverseLinkedListII solution = new ReverseLinkedListII();

        // Test 1: Reverse a middle section.
        // Expected: 1 -> 4 -> 3 -> 2 -> 5

        ListNode head1 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Approach 1, Test 1: ");
        printList(solution.reverseBetween(head1, 2, 4));

        ListNode head2 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Approach 2, Test 1: ");
        printList(
                solution.reverseBetweenSplitReverseReconnect(head2, 2, 4)
        );

        // Test 2: Reverse starting at the head.
        // Expected: 3 -> 2 -> 1 -> 4 -> 5

        ListNode head3 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Approach 1, Test 2: ");
        printList(solution.reverseBetween(head3, 1, 3));

        ListNode head4 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Approach 2, Test 2: ");
        printList(
                solution.reverseBetweenSplitReverseReconnect(head4, 1, 3)
        );

        // Test 3: Reverse the entire list.
        // Expected: 5 -> 4 -> 3 -> 2 -> 1

        ListNode head5 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Approach 1, Test 3: ");
        printList(solution.reverseBetween(head5, 1, 5));

        ListNode head6 = buildList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Approach 2, Test 3: ");
        printList(
                solution.reverseBetweenSplitReverseReconnect(head6, 1, 5)
        );

        // Test 4: left == right; no reversal needed.
        // Expected: 1 -> 2 -> 3

        ListNode head7 = buildList(new int[]{1, 2, 3});

        System.out.print("Approach 1, Test 4: ");
        printList(solution.reverseBetween(head7, 2, 2));

        ListNode head8 = buildList(new int[]{1, 2, 3});

        System.out.print("Approach 2, Test 4: ");
        printList(
                solution.reverseBetweenSplitReverseReconnect(head8, 2, 2)
        );
    }
}
