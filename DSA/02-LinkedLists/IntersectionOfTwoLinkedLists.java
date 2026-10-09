/*
 * LeetCode Problem: Intersection of Two Linked Lists
 * URL: https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * Problem:
 * Given the heads of two singly linked lists, return the node at which
 * the two lists intersect. If they do not intersect, return null.
 *
 * Intersection means that both lists reference the SAME NODE IN MEMORY.
 * Two nodes with equal values are not necessarily the same node.
 *
 * Example:
 *
 * List A: A1 -> A2 --------\
 *                           -> C1 -> C2 -> C3 -> null
 * List B: B1 -> B2 -> B3 --/
 *
 * The intersection node is C1.
 *
 * Brute Force Approach:
 * For every node in List A, traverse List B and check whether both
 * pointers reference the same node.
 *
 * Time Complexity: O(m * n)
 * Auxiliary Space: O(1)
 *
 * Optimized Approach: Two-Pointer Switching
 *
 * 1. Initialize pointerA at headA and pointerB at headB.
 * 2. Move both pointers one node at a time.
 * 3. When pointerA reaches null, redirect it to headB.
 * 4. When pointerB reaches null, redirect it to headA.
 * 5. Continue until both pointers reference the same node.
 *
 * Key Insight:
 * The lists may have different lengths before their intersection.
 * By switching lists, each pointer traverses both lists, compensating
 * for the difference in their lengths.
 *
 * Why It Works:
 * Each pointer traverses the same combined path length:
 *
 *   Pointer A: List A followed by List B
 *   Pointer B: List B followed by List A
 *
 * If an intersection exists, the pointers become aligned and meet at
 * the shared node.
 *
 * If no intersection exists, both pointers eventually reach null.
 * Since null == null, the loop terminates.
 *
 * Complexity:
 * Time: O(m + n)
 * Auxiliary Space: O(1)
 *
 * Common Mistakes:
 * 1. Comparing node values instead of node references.
 * 2. Switching to the other pointer's current position rather than
 *    the other list's head.
 * 3. Advancing pointerA using pointerB.next or vice versa.
 * 4. Modifying the original lists unnecessarily.
 * 5. Forgetting that both pointers can terminate at null.
 *
 * Interview Follow-ups:
 * 1. Can you solve this using the lengths of both lists?
 * 2. Why does pointer switching handle different list lengths?
 * 3. What happens if one or both lists are empty?
 * 4. Why must we compare references rather than values?
 * 5. Can you solve it without modifying either list?
 */

public class IntersectionOfTwoLinkedLists {

    /*
     * LeetCode provides this ListNode definition automatically.
     * This nested class allows the solution to run independently.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
     * Returns the intersection node of two linked lists.
     * Returns null if the lists do not intersect.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode currentA = headA;
        ListNode currentB = headB;

        while (currentA != currentB) {

            // Traverse List A, then switch to List B.
            currentA = currentA == null ? headB : currentA.next;

            // Traverse List B, then switch to List A.
            currentB = currentB == null ? headA : currentB.next;
        }

        return currentA;
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
     * Local tests.
     *
     * The first test constructs two lists sharing the exact same nodes.
     * The second test constructs two independent lists.
     */
    public static void main(String[] args) {

        IntersectionOfTwoLinkedLists solution =
                new IntersectionOfTwoLinkedLists();

        // Test 1: Lists intersect after different-length prefixes.
        //
        // List A: 1 -> 2 -> 8 -> 9
        // List B: 3 -> 4 -> 5 -> 8 -> 9
        //
        // Both lists share the exact same nodes containing 8 and 9.

        ListNode shared = buildList(new int[]{8, 9});

        ListNode headA = new ListNode(1,
                new ListNode(2, shared));

        ListNode headB = new ListNode(3,
                new ListNode(4,
                        new ListNode(5, shared)));

        ListNode intersection =
                solution.getIntersectionNode(headA, headB);

        System.out.print("Test 1 - List A: ");
        printList(headA);

        System.out.print("Test 1 - List B: ");
        printList(headB);

        System.out.println(
                "Test 1 - Intersection value: "
                        + (intersection == null
                        ? "null"
                        : intersection.val));

        // Expected intersection value: 8

        // Test 2: Lists do not intersect.

        ListNode listC = buildList(new int[]{1, 2, 3});
        ListNode listD = buildList(new int[]{4, 5});

        ListNode noIntersection =
                solution.getIntersectionNode(listC, listD);

        System.out.println(
                "Test 2 - Intersection: "
                        + (noIntersection == null
                        ? "null"
                        : noIntersection.val));

        // Expected: null

        // Test 3: Both lists are empty.

        ListNode emptyIntersection =
                solution.getIntersectionNode(null, null);

        System.out.println(
                "Test 3 - Intersection: "
                        + (emptyIntersection == null
                        ? "null"
                        : emptyIntersection.val));

        // Expected: null
    }
}
