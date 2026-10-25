# Pointer switching for intersection

## Compare node identity

Two lists intersect only when they share the same node object from that point onward. Equal values do not imply intersection.

Suppose the lists have nonshared prefixes of lengths a and b, followed by a shared suffix of length c. The pointer on the first list reaches the shared suffix after a nodes, traverses c nodes to null, switches to the second head, then traverses b nodes to the same shared node. The other pointer travels b + c + a nodes, so they align at the intersection. If there is no shared suffix, each pointer traverses both complete lists and reaches null after the same total distance.

    first = headA
    second = headB
    while first is not second:
        first = headB if first is null else first.next
        second = headA if second is null else second.next
    return first

The first pointer traverses list A then B; the second traverses B then A. Each covers the same total length, removing the initial length difference without computing either length.

## Why it works

Before entering a shared suffix, each pointer has traversed the other list's unmatched prefix as well as its own. The total unmatched distance becomes equal. If there is no shared node, both pointers take m + n steps and become null together.

## Complexity and assumptions

Time is O(m + n); extra space is O(1). This method assumes acyclic lists. For cyclic inputs, intersection has different cases and needs a cycle-aware definition.

## Practice link

[Intersection of Two Linked Lists](../IntersectionOfTwoLinkedLists.java)
