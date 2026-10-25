# Rotating a list

## Reduce the rotation before rewiring

A right rotation by k moves the last k nodes to the front. Rotating by the list length returns the same list, so only k modulo n matters, where n is the list length.

    if head is null or head.next is null:
        return head

    find tail and count n
    shift = k mod n
    if shift is 0:
        return head

    newTailPosition = n - shift - 1
    newTail = node at newTailPosition
    newHead = newTail.next
    tail.next = head
    newTail.next = null
    return newHead

For a right rotation, the new tail is n - shift - 1 links from the head. Temporarily connecting the old tail to head makes the list circular; breaking at newTail creates the rotated list.

## Example and edge cases

For 1 → 2 → 3 → 4 → 5 with k = 2, n = 5 and shift = 2. The new tail is 3, so break after it; the result is 4 → 5 → 1 → 2 → 3.

Handle an empty list, a single node, and k divisible by n before using the new-tail position. If negative rotations are allowed, normalize the direction according to the problem definition.

## Complexity

Finding length and tail takes O(n) time. Rewiring takes O(1) extra space.

## Practice link

[Rotate List](../RotateList.java)
