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

Compare node references, not node values. Two different nodes may store the same value.

## Complexity and checks

Both methods take O(n) time and O(1) extra space. Check null and one-node lists before assuming fast.next exists. For middle finding, confirm which middle the problem expects for even-length input.

## Practice links

[Middle of the Linked List](../MiddleOfTheLinkedList.java) · [Linked List Cycle](../LinkedListCycle.java)
