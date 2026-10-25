# Merging and stable partitioning

## A tail pointer builds a result incrementally

A dummy head starts an output list. A tail pointer always points to its final node. To append a chosen node, connect tail.next to it and advance tail. The dummy node avoids a separate first-node case.

## Merge two sorted lists

At each step, compare the first unprocessed nodes. Append the smaller one, then advance only the input list that supplied it. The result remains sorted because the chosen node is no larger than any remaining node in either list.

    dummy.next = null
    tail = dummy
    first = headA
    second = headB

    while first is not null and second is not null:
        if first.value <= second.value:
            chosen = first
            first = first.next
        else:
            chosen = second
            second = second.next
        tail.next = chosen
        tail = chosen

    tail.next = first if first is not null else second
    return dummy.next

The remaining suffix can be attached at once because it is already sorted. Choosing from the first list on equality makes the merge stable with respect to the input order.

## Stable partition

To partition around a value x while preserving relative order, build two chains: values below x, and values at least x. Append each node to the appropriate tail as it is encountered, then connect the chains.

    lessDummy.next = null
    otherDummy.next = null
    lessTail = lessDummy
    otherTail = otherDummy

    node = head
    while node is not null:
        nextNode = node.next
        node.next = null
        if node.value < x:
            lessTail.next = node
            lessTail = node
        else:
            otherTail.next = node
            otherTail = node
        node = nextNode

    lessTail.next = otherDummy.next
    return lessDummy.next if it exists else otherDummy.next

Saving nextNode before detaching prevents losing the unprocessed suffix. Detaching each node prevents stale links from making cycles or retaining an old order.

## Complexity

Both operations are O(n) time and O(1) extra space when nodes are relinked rather than copied. The merge assumes both inputs are sorted; the partition assumes the comparison divides values as specified.

## Practice links

[Merge Two Sorted Lists](../MergeTwoSortedLists.java) · [Partition List](../PartitionList.java)
