/**
 * LeetCode: Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * Problem:
 * Given a linked list where each node has:
 *
 *     1. val    - the node's value
 *     2. next   - pointer to the next node
 *     3. random - pointer to any node in the list or null
 *
 * Return a deep copy of the linked list.
 *
 * A deep copy must create entirely new nodes. Both next and random
 * pointers in the copied list must refer to copied nodes, not original nodes.
 *
 * ------------------------------------------------------------
 * Brute Force Approach:
 * ------------------------------------------------------------
 *
 * Use a HashMap<Node, Node> to map each original node to its copy.
 *
 * First pass:
 *     Create a copy of every node and store it in the map.
 *
 * Second pass:
 *     Assign the next and random pointers using the map.
 *
 * Time:  O(n)
 * Space: O(n)
 *
 * ------------------------------------------------------------
 * Optimized Approach: Interleaving
 * ------------------------------------------------------------
 *
 * We can solve this without a HashMap by temporarily interleaving
 * original nodes with their copies.
 *
 * The algorithm has three phases.
 *
 * PHASE 1: Create and interleave copies
 * ------------------------------------------------------------
 *
 * Original:
 *
 *     1 → 2 → 3 → null
 *
 * After inserting each copy immediately after its original:
 *
 *     1 → 1' → 2 → 2' → 3 → 3' → null
 *
 * Each original node's next pointer now points to its copy.
 *
 * PHASE 2: Assign random pointers
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 *     1.random = 3
 *
 * After interleaving:
 *
 *     1 → 1' → 2 → 2' → 3 → 3'
 *
 * Because 3.next points to 3', we can assign:
 *
 *     1'.random = 1.random.next
 *
 * In general:
 *
 *     original.next.random = original.random.next
 *
 * provided original.random is not null.
 *
 * If original.random is null, the copied node's random pointer
 * remains null.
 *
 * PHASE 3: Separate the two lists
 * ------------------------------------------------------------
 *
 * Before separation:
 *
 *     1 → 1' → 2 → 2' → 3 → 3' → null
 *
 * After separation:
 *
 *     Original: 1 → 2 → 3 → null
 *
 *     Copy:     1' → 2' → 3' → null
 *
 * For each original node:
 *
 * 1. Save its duplicate.
 * 2. Restore the original node's next pointer.
 * 3. Connect the duplicate to the next duplicate.
 * 4. Advance to the next original node.
 *
 * ------------------------------------------------------------
 * Key Insight
 * ------------------------------------------------------------
 *
 * Interleaving creates a temporary relationship:
 *
 *     original.next = copy
 *
 * Therefore, the copy of any node can be found through the
 * original node's next pointer.
 *
 * This lets us assign random pointers without a HashMap.
 *
 * ------------------------------------------------------------
 * Why It Works
 * ------------------------------------------------------------
 *
 * Phase 1 creates exactly one new node per original node.
 *
 * Phase 2 uses the interleaved structure to ensure every copied
 * random pointer points to the corresponding copied node.
 *
 * Phase 3 restores the original list and extracts the copied list.
 *
 * ------------------------------------------------------------
 * Complexity
 * ------------------------------------------------------------
 *
 * Time:  O(n)
 * Space: O(1) auxiliary space
 *
 * We traverse the list a constant number of times.
 * Only a constant number of pointer variables are used.
 *
 * The copied nodes themselves require O(n) memory, but this is
 * output storage rather than auxiliary working space.
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Assigning copy.random = original.random.
 *
 *    This incorrectly points into the original list.
 *
 * 2. Forgetting that original.random.next points to the copy
 *    of the random target only after interleaving is complete.
 *
 * 3. Advancing to the next node incorrectly during interleaving.
 *
 * 4. Forgetting to restore the original list during separation.
 *
 * 5. Forgetting to connect each copied node to the next copied node.
 *
 * 6. Returning the original head instead of the copied head.
 *
 * ------------------------------------------------------------
 * Interview Follow-ups
 * ------------------------------------------------------------
 *
 * Q: How would you solve this using a HashMap?
 *
 * A: Map every original node to its copy, then assign next and random
 *    pointers using the map.
 *
 * Q: Why is the interleaving approach O(1) auxiliary space?
 *
 * A: It uses the original list itself to establish the mapping between
 *    original nodes and their copies.
 *
 * Q: Does the algorithm modify the original list?
 *
 * A: Temporarily, yes. The separation phase restores its original
 *    next-pointer structure.
 *
 * Q: What happens when head is null?
 *
 * A: Return null immediately.
 */
public class CopyListWithRandomPointer
{
    /**
     * Definition for a node with next and random pointers.
     */
    static class Node
    {
        int val;
        Node next;
        Node random;

        Node(int val)
        {
            this.val = val;
        }
    }

    /**
     * Creates a deep copy of a linked list with random pointers.
     */
    public static Node copyRandomList(Node head)
    {
        if (head == null)
        {
            return null;
        }

        /*
         * Phase 1:
         * Insert a duplicate immediately after every original node.
         *
         * Original:
         *     1 → 2 → 3
         *
         * Interleaved:
         *     1 → 1' → 2 → 2' → 3 → 3'
         */
        Node current = head;

        while (current != null)
        {
            Node duplicate = new Node(current.val);

            duplicate.next = current.next;
            current.next = duplicate;

            // Move to the next original node, skipping the duplicate.
            current = duplicate.next;
        }

        /*
         * Phase 2:
         * Assign random pointers to the duplicates.
         */
        current = head;

        while (current != null)
        {
            Node duplicate = current.next;

            if (current.random != null)
            {
                // The original random target's next node is its copy.
                duplicate.random = current.random.next;
            }

            // Move to the next original node.
            current = duplicate.next;
        }

        /*
         * Phase 3:
         * Separate the original list from the copied list.
         */
        Node deepCopyHead = head.next;
        current = head;

        while (current != null)
        {
            Node duplicate = current.next;

            // Restore the original node's next pointer.
            current.next = duplicate.next;

            // Connect the duplicate to the next duplicate.
            if (current.next != null)
            {
                duplicate.next = current.next.next;
            }

            // Move to the next original node.
            current = current.next;
        }

        return deepCopyHead;
    }

    /**
     * Creates a linked list from an array of values.
     */
    private static Node createList(int[] values)
    {
        Node dummy = new Node(0);
        Node tail = dummy;

        for (int value : values)
        {
            tail.next = new Node(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    /**
     * Prints node values and the values referenced by random pointers.
     */
    private static void printList(Node head)
    {
        Node current = head;

        while (current != null)
        {
            System.out.print(
                "[val=" + current.val +
                ", random=" +
                (current.random == null ? "null" : current.random.val) +
                "]"
            );

            current = current.next;

            System.out.print(current == null ? " -> END\n" : " -> ");
        }

        if (head == null)
        {
            System.out.println("null");
        }
    }

    /**
     * Basic local tests.
     */
    public static void main(String[] args)
    {
        // Test 1: Empty list.
        Node copy1 = copyRandomList(null);

        System.out.println("Test 1: Empty list");
        printList(copy1);
        System.out.println();

        // Test 2: Single node whose random pointer is null.
        Node head2 = createList(new int[]{1});
        Node copy2 = copyRandomList(head2);

        System.out.println("Test 2: Single node");
        printList(copy2);
        System.out.println();

        // Test 3: Single node whose random pointer points to itself.
        Node head3 = createList(new int[]{7});
        head3.random = head3;

        Node copy3 = copyRandomList(head3);

        System.out.println("Test 3: Self-referencing random pointer");
        printList(copy3);
        System.out.println();

        // Test 4: Multiple nodes with forward, backward, and null random pointers.
        Node head4 = createList(new int[]{1, 2, 3, 4, 5});

        Node n1 = head4;
        Node n2 = n1.next;
        Node n3 = n2.next;
        Node n4 = n3.next;
        Node n5 = n4.next;

        n1.random = n3;
        n2.random = n1;
        n3.random = n5;
        n4.random = null;
        n5.random = n2;

        Node copy4 = copyRandomList(head4);

        System.out.println("Test 4: Multiple random pointers");
        System.out.println("Original:");
        printList(head4);
        System.out.println("Copied:");
        printList(copy4);
    }
}
