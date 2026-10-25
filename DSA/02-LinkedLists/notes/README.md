# Linked Lists notes

These notes use language-neutral pseudocode. A node is an object with a value and one or more links to other nodes. Unless a note says otherwise, assume a singly linked list with a head reference and a null link at the end.

Use the fundamentals note to understand the structure, then study the operation or pointer pattern that matches the problem. The parent [Linked Lists revision guide](../README.md) remains the quick review of the 15 practiced problems.

## Notes and related problems

| Note | Main ideas | Practice |
|---|---|---|
| [Linked-list fundamentals](linked-list-fundamentals.md) | Node and list representations, singly/doubly/circular lists, basic operations and costs | Foundation for the whole section |
| [Reversing a list](reversing-a-list.md) | Reverse every link safely with three moving references | [Reverse Linked List](../ReverseLinkedList.java) |
| [Reversing a sublist and adjacent pairs](reversing-a-sublist-and-pairs.md) | Boundaries, dummy node, reverse a selected segment, swap pairs | [Reverse Linked List II](../ReverseLinkedListII.java), [Swap Nodes in Pairs](../SwapNodesInPairs.java) |
| [Reversing k-group segments](reversing-k-groups.md) | Check a full group, reverse it, reconnect both boundaries | [Reverse Nodes in k-Group](../ReverseNodesInKGroup.java) |
| [Fast and slow pointers](fast-and-slow-pointers.md) | Middle finding and cycle detection | [Middle of the Linked List](../MiddleOfTheLinkedList.java), [Linked List Cycle](../LinkedListCycle.java) |
| [Fixed gap and dummy nodes](fixed-gap-and-dummy-nodes.md) | Keep a fixed distance between pointers; simplify head removal | [Remove Nth Node From End](../RemoveNthNodeFromEnd.java) |
| [Merging and stable partitioning](merging-and-stable-partitioning.md) | Tail pointers, dummy heads, preserve relative order | [Merge Two Sorted Lists](../MergeTwoSortedLists.java), [Partition List](../PartitionList.java) |
| [Splitting, reversing, and weaving](splitting-reversing-and-weaving.md) | Find halves, reverse one half, compare or interleave | [Palindrome Linked List](../PalindromeLinkedList.java), [Reorder List](../ReorderList.java) |
| [Pointer switching for intersection](pointer-switching-for-intersection.md) | Align traversal distances and compare node identity | [Intersection of Two Linked Lists](../IntersectionOfTwoLinkedLists.java) |
| [Copying random-pointer lists](copying-random-pointer-lists.md) | Preserve node identity and remap multiple links | [Copy List with Random Pointer](../CopyListWithRandomPointer.java) |
| [Adding numbers with carry](adding-numbers-with-carry.md) | Traverse digit lists and propagate carry | [Add Two Numbers](../AddTwoNumbers.java) |
| [Rotating a list](rotating-a-list.md) | Reduce rotations by length, choose new boundary, reconnect | [Rotate List](../RotateList.java) |

## A shared pointer-safety rule

Before changing a link, identify every node that must remain reachable and save its reference. State what each pointer means before and after the update. When an operation changes a segment, track the segment's old head and tail: after reversal, those roles switch.

All pseudocode is language-neutral. The complexity of a basic operation depends on which references the list maintains; notes state the assumptions where that matters.
