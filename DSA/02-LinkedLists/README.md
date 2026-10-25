# 02 — Linked Lists

**Status: 15 / 15 implemented**

This section builds precise pointer reasoning through common singly linked list operations and patterns. Re-solve problems from the prompt before opening the existing implementation. Explain pointer states, boundaries, and invariants as you work.

Language-neutral explanations of the data structure and pointer patterns are in the [Linked Lists notes](notes/README.md).

## Problem List

| # | Problem | Main pattern to recall | Solution |
|---:|---|---|---|
| 1 | Reverse Linked List | Three pointers; preserve the successor before rewiring | [View](./ReverseLinkedList.java) |
| 2 | Merge Two Sorted Lists | Dummy head and advancing the smaller node | [View](./MergeTwoSortedLists.java) |
| 3 | Linked List Cycle | Fast and slow pointers | [View](./LinkedListCycle.java) |
| 4 | Remove Nth Node From End of List | Dummy node and a fixed fast/slow gap | [View](./RemoveNthNodeFromEnd.java) |
| 5 | Middle of the Linked List | Fast and slow pointers; choose the second middle | [View](./MiddleOfTheLinkedList.java) |
| 6 | Palindrome Linked List | Find midpoint, reverse second half, compare | [View](./PalindromeLinkedList.java) |
| 7 | Reorder List | Split, reverse, and weave two halves | [View](./ReorderList.java) |
| 8 | Copy List with Random Pointer | Hash map or interleaving copies; preserve node identity | [View](./CopyListWithRandomPointer.java) |
| 9 | Add Two Numbers | Digit-by-digit traversal with carry | [View](./AddTwoNumbers.java) |
| 10 | Intersection of Two Linked Lists | Compare node references; pointer switching aligns distances | [View](./IntersectionOfTwoLinkedLists.java) |
| 11 | Reverse Linked List II | Dummy node and reconnect a sublist | [View](./ReverseLinkedListII.java) |
| 12 | Swap Nodes in Pairs | Rewire adjacent nodes through a predecessor | [View](./SwapNodesInPairs.java) |
| 13 | Reverse Nodes in k-Group | Verify a complete group, reverse, then reconnect | [View](./ReverseNodesInKGroup.java) |
| 14 | Rotate List | Measure length, reduce `k`, close and break the cycle | [View](./RotateList.java) |
| 15 | Partition List | Two stable chains with dummy heads and tail pointers | [View](./PartitionList.java) |

Use [`../LeetCode.md`](../LeetCode.md) for the canonical problem links.

## Reasoning Checklist

Before changing a `next` pointer, identify which references must be saved so the rest of the list remains reachable. For each loop, state what the pointers represent before and after one iteration. When reconnecting sections, identify the new head and tail of each section and the boundary that must be preserved.

For problems involving shared lists, compare node references rather than values: equal values do not imply the same node. For incomplete groups or edge cases, verify the boundary before mutating links.

When a variation is unfamiliar, derive a solution from the constraints and required invariants before reaching for a remembered pattern. In particular, keep practicing precise boundary and reconnection explanations for sublist and k-group reversal.

## Re-solving Checklist

1. Draw the nodes and mark each pointer.
2. State the invariant the algorithm must maintain.
3. Preserve references before overwriting links.
4. Check empty, single-node, and boundary cases.
5. Explain time and auxiliary space from the operations used.
6. Re-solve from scratch before consulting the implementation.
