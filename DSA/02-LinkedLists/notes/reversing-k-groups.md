# Reversing nodes in k-group

## Goal and decision point

Reverse each complete block of k nodes while keeping the order within an incomplete final block unchanged. The key decision is whether a full group exists before changing any of its links.

## Group invariant

Maintain the tail of the already processed prefix. From that boundary, locate the kth node. If fewer than k nodes remain, stop and leave the suffix alone. Otherwise, save the node after the group, reverse exactly the group, and reconnect both ends.

    dummy.next = head
    previousGroupTail = dummy

    while true:
        groupKth = previousGroupTail
        repeat k times:
            groupKth = groupKth.next
            if groupKth is null:
                return dummy.next
        groupFirst = previousGroupTail.next
        afterGroup = groupKth.next

        previous = afterGroup
        current = groupFirst
        repeat k times:
            nextNode = current.next
            current.next = previous
            previous = current
            current = nextNode

        previousGroupTail.next = groupKth
        previousGroupTail = groupFirst

    return dummy.next

Starting previous at afterGroup makes the last reversed link point to the untouched suffix. After reversal, groupKth is the new group head and groupFirst is its new tail. The old tail becomes the boundary for finding the next group.

## Why the pre-check matters

If the remaining suffix is shorter than k, the operation must not reverse it. Checking the boundary first avoids partially mutating a group that should remain unchanged.

For k = 3 and A → B → C → D → E, first reconnect C → B → A → D → E. Only D and E remain, so they are left as they were.

## Complexity and edge cases

Each node is visited a constant number of times: O(n) time and O(1) extra space. Handle k = 1, an empty list, and a final partial group. Ensure the group lookup returns “not enough nodes” rather than dereferencing null.

## Practice link

[Reverse Nodes in k-Group](../ReverseNodesInKGroup.java)
