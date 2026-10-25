# Fixed-gap pointers and dummy nodes

## Keep a known distance

To find the nth node from the end in one pass, place two pointers a fixed distance apart. When the leading pointer reaches the end, the trailing pointer is at the required boundary.

A dummy node before head handles the case where the head itself must be removed.

    dummy.next = head
    fast = dummy
    slow = dummy

    advance fast by n + 1 links
    while fast is not null:
        fast = fast.next
        slow = slow.next

    slow.next = slow.next.next
    return dummy.next

After fast advances n + 1 links, slow is n + 1 links behind it. When fast reaches null, slow is immediately before the node to remove. For example, with five nodes and n = 2, the gap makes slow stop at the third node, so slow.next is the fourth node, the second from the end.

## Why the dummy node helps

Without a dummy, removing the head requires a separate case because there is no predecessor. With dummy.next = head, the head has a predecessor just like every other node. The returned head is dummy.next after rewiring.

## Complexity and assumptions

The list is traversed once: O(n) time and O(1) extra space. This pseudocode assumes n is valid and between 1 and the list length. If inputs can violate that, validate before moving fast or dereferencing slow.next.

## Common errors

- Advancing fast by n rather than n + 1 when both pointers start at the dummy; this places slow on the target instead of its predecessor.
- Forgetting that deleting the first real node changes dummy.next.
- Dereferencing the node after slow without confirming that n is valid.

## Practice link

[Remove Nth Node From End](../RemoveNthNodeFromEnd.java)
