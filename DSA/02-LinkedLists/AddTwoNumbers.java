/*
 * LeetCode Problem: Add Two Numbers
 * URL: https://leetcode.com/problems/add-two-numbers/
 *
 * Problem:
 * Given two non-empty linked lists representing two non-negative integers,
 * where the digits are stored in reverse order, add the two numbers and
 * return the sum as a linked list.
 *
 * Example:
 * Input:
 *   l1 = 2 -> 4 -> 3  (represents 342)
 *   l2 = 5 -> 6 -> 4  (represents 465)
 *
 * Output:
 *   7 -> 0 -> 8       (represents 807)
 *
 * Approach:
 * 1. Use a sentinel (dummy) node to simplify result-list construction.
 * 2. Maintain a carry variable for the digit carried from the previous sum.
 * 3. Add corresponding digits while both lists have nodes.
 * 4. Process any remaining nodes in either list.
 * 5. Append any remaining carry as additional nodes.
 *
 * Key Insight:
 * Since digits are stored in reverse order, we can add corresponding
 * digits directly from the heads of the lists without reversing them.
 *
 * For each digit:
 *   sumValue = digit1 + digit2 + carry
 *   resultDigit = sumValue % 10
 *   carry = sumValue / 10
 *
 * Why It Works:
 * Each iteration calculates one digit of the sum, starting with the
 * least significant digit. The carry is propagated to the next digit.
 * Processing the remaining nodes and final carry handles numbers of
 * different lengths and sums that produce an extra digit.
 *
 * Brute Force Alternative:
 * Convert both linked lists into integers, add the integers, and convert
 * the result back into a linked list.
 *
 * Why We Avoid It:
 * Large numbers may exceed the range of Java primitive numeric types.
 * Direct linked-list addition avoids this limitation.
 *
 * Complexity:
 * Let m and n be the lengths of the two input lists.
 *
 * Time: O(max(m, n))
 * Auxiliary Space: O(1), excluding the output list.
 * Output Space: O(max(m, n)) in the worst case.
 *
 * Common Mistakes:
 * 1. Forgetting to include the carry in the next digit's sum.
 * 2. Forgetting to process nodes when the lists have different lengths.
 * 3. Forgetting to append a final carry.
 * 4. Returning the sentinel instead of sentinel.next.
 * 5. Confusing the stored reverse-order digits with the normal
 *    left-to-right representation of the number.
 *
 * Interview Follow-ups:
 * 1. How would you implement this using a single loop?
 * 2. How would you add numbers when digits are stored in forward order?
 * 3. How would you handle negative numbers?
 * 4. Why is converting the lists to primitive integers unreliable?
 */

public class AddTwoNumbers {

    /*
     * Definition of a singly linked list node.
     * LeetCode provides this class automatically.
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
     * Adds two numbers represented by linked lists.
     *
     * The digits are stored in reverse order.
     * Example: 2 -> 4 -> 3 represents 342.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode list1 = l1;
        ListNode list2 = l2;

        // Sentinel node simplifies result-list construction.
        ListNode sentinel = new ListNode();
        ListNode sum = sentinel;

        int carry = 0;

        /*
         * Step 1:
         * Process digits while both lists contain nodes.
         */
        while (list1 != null && list2 != null) {

            int sumValue = list1.val + list2.val + carry;

            // The current result digit is the remainder after division by 10.
            ListNode nodeToAdd = new ListNode(sumValue % 10);

            sum.next = nodeToAdd;
            sum = sum.next;

            // The quotient becomes the carry for the next digit.
            carry = sumValue / 10;

            list1 = list1.next;
            list2 = list2.next;
        }

        /*
         * Step 2:
         * Process any remaining digits in the first list.
         */
        while (list1 != null) {

            int sumValue = list1.val + carry;

            ListNode nodeToAdd = new ListNode(sumValue % 10);

            sum.next = nodeToAdd;
            sum = sum.next;

            carry = sumValue / 10;

            list1 = list1.next;
        }

        /*
         * Step 3:
         * Process any remaining digits in the second list.
         */
        while (list2 != null) {

            int sumValue = list2.val + carry;

            ListNode nodeToAdd = new ListNode(sumValue % 10);

            sum.next = nodeToAdd;
            sum = sum.next;

            carry = sumValue / 10;

            list2 = list2.next;
        }

        /*
         * Step 4:
         * Append any remaining carry.
         *
         * Example:
         * 9 -> 9 -> 9 + 1
         * produces 0 -> 0 -> 0 -> 1.
         */
        while (carry > 0) {

            int sumValue = carry;

            ListNode nodeToAdd = new ListNode(sumValue % 10);

            sum.next = nodeToAdd;
            sum = sum.next;

            carry = sumValue / 10;
        }

        // Skip the sentinel and return the actual result list.
        return sentinel.next;
    }

    /*
     * Helper method to build a linked list from an array of digits.
     */
    private static ListNode buildList(int[] digits) {

        ListNode sentinel = new ListNode();
        ListNode current = sentinel;

        for (int digit : digits) {
            current.next = new ListNode(digit);
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
     * These tests can be run locally.
     * LeetCode provides its own ListNode definition and test harness.
     */
    public static void main(String[] args) {

        AddTwoNumbers solution = new AddTwoNumbers();

        // Test 1: Equal-length lists.
        // 342 + 465 = 807
        ListNode l1 = buildList(new int[]{2, 4, 3});
        ListNode l2 = buildList(new int[]{5, 6, 4});

        System.out.print("Test 1: ");
        printList(solution.addTwoNumbers(l1, l2));
        // Expected: 7 -> 0 -> 8

        // Test 2: Different-length lists.
        // 99 + 1 = 100
        l1 = buildList(new int[]{9, 9});
        l2 = buildList(new int[]{1});

        System.out.print("Test 2: ");
        printList(solution.addTwoNumbers(l1, l2));
        // Expected: 0 -> 0 -> 1

        // Test 3: Carry propagates across multiple digits.
        // 999 + 1 = 1000
        l1 = buildList(new int[]{9, 9, 9});
        l2 = buildList(new int[]{1});

        System.out.print("Test 3: ");
        printList(solution.addTwoNumbers(l1, l2));
        // Expected: 0 -> 0 -> 0 -> 1

        // Test 4: No carry.
        // 12 + 34 = 46
        l1 = buildList(new int[]{2, 1});
        l2 = buildList(new int[]{4, 3});

        System.out.print("Test 4: ");
        printList(solution.addTwoNumbers(l1, l2));
        // Expected: 6 -> 4
    }
}
