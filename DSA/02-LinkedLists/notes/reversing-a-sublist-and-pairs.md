# Reversing a sublist and adjacent pairs

## Why boundaries matter

Reversing only part of a list has two external connections to preserve: the node before the segment and the node after it. A dummy node gives the first real node a predecessor, which keeps head changes inside the same pattern.

For a segment from position left through right, remember the node before it and the original first node. After reversal, the original first node becomes the segment tail and must reconnect to the node after the segment.

    dummy.next = head
    before = dummy
    repeat left - 1 times:
        before = before.next

    segmentTail = before.next
    current = segmentTail
    previous = null
    repeat right - left + 1 times:
        nextNode = current.next
        current.next = previous
        previous = current
        current = nextNode

    before.next = previous
    segmentTail.next = current
    return dummy.next

During the loop, previous is the reversed part of the segment and current is the unprocessed part. The saved current value after the loop is the node after the segment.

## Swapping adjacent pairs

A pair swap is a fixed-size segment reversal where the order inside each complete pair is reversed. Keep the node before the pair and the node after it.

    dummy.next = head
    before = dummy
    while before.next is not null and before.next.next is not null:
        first = before.next
        second = first.next
        after = second.next

        before.next = second
        second.next = first
        first.next = after
        before = first
    return dummy.next

The loop checks that two nodes exist before changing links. If one node remains at the end, it stays in place.

## Complexity and pitfalls

Both operations take O(n) time and O(1) extra space. Positions and list length must satisfy the problem's assumptions before using the sublist loop. For empty ranges or invalid positions, handle the specified behavior before mutation. Always keep the original segment tail so it can reconnect to the untouched suffix.

## Practice links

[Reverse Linked List II](../ReverseLinkedListII.java) · [Swap Nodes in Pairs](../SwapNodesInPairs.java)
