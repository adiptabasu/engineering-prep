# Reversing a list

## Goal and idea

Reverse a singly linked list by changing each node's next link to point to its predecessor. The values and node identities stay the same; only the links change.

The challenge is that changing current.next destroys the old route to the rest of the list. Save the successor before rewiring.

## Pointer roles and invariant

Use three references:

- previous is the already reversed prefix.
- current is the next node to process.
- nextNode saves the unprocessed suffix before current.next changes.

At the start of each iteration, previous is a reversed list containing exactly the nodes processed so far, and current begins the untouched suffix. Each iteration moves one node from that suffix onto the front of the reversed prefix.

    previous = null
    current = head
    while current is not null:
        nextNode = current.next
        current.next = previous
        previous = current
        current = nextNode
    newHead = previous

When current reaches null, every node has been processed and previous is the new head.

## Small trace

For A → B → C → null:

1. Save B, point A back to null. Reversed prefix: A. Current: B → C.
2. Save C, point B to A. Reversed prefix: B → A. Current: C.
3. Save null, point C to B. Reversed prefix: C → B → A. Current: null.

The untouched suffix remains reachable at every step because nextNode is saved first.

## Complexity and checks

Every node is processed once: O(n) time and O(1) extra space.

Check empty and one-node lists. For both, the same loop works: the empty list returns null, and a one-node list remains unchanged. Do not advance current using current.next after rewiring; that link now points backward.

## Practice link

[Reverse Linked List](../ReverseLinkedList.java)
