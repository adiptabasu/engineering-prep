# Fast and slow pointers

## The technique

Move one pointer by one link and another by two links. Their relative movement reveals structure that a single traversal cannot show directly. Both pointers usually start at the head, but the stopping condition determines which middle node is returned.

## Finding the middle

    slow = head
    fast = head
    while fast is not null and fast.next is not null:
        slow = slow.next
        fast = fast.next.next
    return slow

Each iteration advances slow by one node and fast by two. When fast reaches the end, slow has traveled about half as far. With an even number of nodes, this version returns the second of the two middle nodes. A different initialization or loop condition may be needed when the problem asks for the first middle.

## Detecting a cycle

    slow = head
    fast = head
    while fast is not null and fast.next is not null:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            return true
    return false

If there is no cycle, fast eventually reaches null. If there is a cycle, both pointers enter it. Fast gains one node per iteration on slow, so their distance around the cycle changes by one each time and eventually becomes zero modulo the cycle length; they meet.

## Finding the cycle entry

After the pointers meet inside a cycle, reset one pointer to the head. Advance both pointers one link at a time; their next meeting point is the cycle entry.

    slow = head
    fast = head
    while fast is not null and fast.next is not null:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            fromHead = head
            fromMeetingPoint = slow
            while fromHead is not fromMeetingPoint:
                fromHead = fromHead.next
                fromMeetingPoint = fromMeetingPoint.next
            return fromHead
    return null

If the distance from head to the entry is `a`, and the meeting point is `b` links into a cycle of length `c`, the first meeting implies `a + b` and `2(a + b)` land at the same cycle position modulo `c`. Thus `a + b` is a multiple of `c`, so advancing `a` links from the meeting point reaches the entry. A pointer from the head also reaches it in `a` links. The two advances therefore meet there. This adds a useful extension to cycle detection; the current practice list has a detection problem but no separate cycle-entry problem.

To find the cycle length after a meeting, hold one pointer fixed and walk another around the cycle until it returns to the meeting node, counting links. This takes O(c) time and O(1) extra space.

Compare node references, not node values. Two different nodes may store the same value.

## Complexity and checks

Both methods take O(n) time and O(1) extra space. Check null and one-node lists before assuming fast.next exists. For middle finding, confirm which middle the problem expects for even-length input.

## Practice links

[Middle of the Linked List](../MiddleOfTheLinkedList.java) · [Linked List Cycle](../LinkedListCycle.java)
