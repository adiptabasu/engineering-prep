# Splitting, reversing, and weaving

## One half can stand in for a stack

Some problems need to compare or interleave the first and second halves of a list. Finding the midpoint with slow and fast pointers, then reversing the second half, provides access to both halves from their fronts without storing every node in an auxiliary array.

## Palindrome check

Find the middle. For an odd-length list, skip the middle node because it has no matching partner. Reverse the second half, then compare corresponding values from the front and reversed half.

    result = true
    slow = head
    fast = head
    while fast is not null and fast.next is not null:
        slow = slow.next
        fast = fast.next.next

    secondStart = slow if fast is null else slow.next
    reversedSecond = reverse(secondStart)
    left = head
    right = reversedSecond
    while right is not null:
        if left.value differs from right.value:
            result = false
            break
        left = left.next
        right = right.next

Only the shorter half needs comparison. If the problem requires preserving the input list, reverse the second half again and reconnect it before returning.

## Reorder list

To transform first, last, second, second-last, split the list into halves, reverse the second half, then weave one node from each half. Start fast one node ahead of slow so the first half contains the middle node when the list length is odd and the halves are balanced when it is even. Return immediately for an empty or one-node list.

    if head is null or head.next is null:
        return head
    slow = head
    fast = head.next
    while fast is not null and fast.next is not null:
        slow = slow.next
        fast = fast.next.next

    firstHalf = head
    secondHalf = slow.next
    slow.next = null
    secondHalf = reverse(secondHalf)

    while secondHalf is not null:
        firstNext = firstHalf.next
        secondNext = secondHalf.next
        firstHalf.next = secondHalf
        secondHalf.next = firstNext
        firstHalf = firstNext
        secondHalf = secondNext

For odd length, the first half may contain the middle node. The second half is no longer than the first, so weaving stops safely when the second half ends.

## Complexity and mutation

Both methods use O(n) time and O(1) extra space. Reversing the second half mutates links; restore them if the contract requires the original list unchanged. Preserve the next references before weaving, because each assignment changes the old route.

## Practice links

[Palindrome Linked List](../PalindromeLinkedList.java) · [Reorder List](../ReorderList.java)
