# Linked-list fundamentals

## What a linked list is

A linked list is a sequence of nodes connected by references. A node stores a value and one or more links to other nodes. The list is reached from a head reference; the nodes do not need to be addressed by numeric positions.

In a singly linked list, each node points forward:

    head → [value | next] → [value | next] → null

The list's order comes from following links, not from the values stored in the nodes. Two nodes may hold equal values and still be different nodes.

## Common forms

### Singly linked

Each node has a value and a next link. The final node points to null. It uses one link per node and supports forward traversal. To remove a node from the middle, you usually need its predecessor.

### Doubly linked

Each node has both previous and next links:

    null ← [prev | value | next] ⇄ [prev | value | next] → null

Traversal can go in either direction. If you already have a node reference, unlinking it is constant time by reconnecting its neighbors. Every insertion or deletion must update both directions consistently. It needs more space per node.

### Circular singly linked

The final node's next link points back to the first node instead of null. A traversal must stop when it returns to its starting node; waiting for null would loop forever. An empty circular list has no head. A one-node circle points back to itself.

### Circular doubly linked

Each node has previous and next links, and the last and first nodes point to one another. This supports traversal in both directions around a ring. The same boundary care applies when inserting or deleting the first or only node.

## Creating and traversing singly linked nodes

The following abstract constructor creates one isolated node:

    makeNode(value):
        node.value = value
        node.next = null
        return node

A forward traversal follows each link exactly once:

    current = head
    while current is not null:
        visit(current.value)
        current = current.next

The traversal is O(n) time and O(1) extra space. If the list is circular, use a return-to-start stopping condition instead.

## Basic singly linked operations

### Insert at the head

    node = makeNode(value)
    node.next = head
    head = node
    if tail is null:
        tail = node

This is O(1). The old head remains reachable through node.next. The empty-list case also makes the new node the tail when a tail reference is maintained.

### Append at the tail

When maintaining both head and tail, append as follows:

    node = makeNode(value)
    if tail is null:
        head = node
        tail = node
    else:
        tail.next = node
        tail = node

This is O(1). Without a tail reference, find the last node first, which costs O(n). For an empty list, both head and tail become the new node.

### Insert after a known node

    node = makeNode(value)
    node.next = current.next
    current.next = node
    if tail is current:
        tail = node

The rewiring is O(1) once current is known. If current was found by searching from head, the overall operation is O(n). Update tail when the known node was the final node.

### Delete after a known predecessor

    victim = predecessor.next
    predecessor.next = victim.next

If the victim was the tail, update the tail to predecessor. The predecessor is needed because its link must bypass the victim. Deleting the head is a separate boundary case; update head directly.

## Implementing doubly and circular forms

### Doubly linked insertion and removal

For a non-circular doubly linked list, inserting after a known node updates both directions. The tail changes when the inserted node becomes last.

    node = makeDoublyNode(value)
    node.prev = current
    node.next = current.next
    if current.next is not null:
        current.next.prev = node
    else:
        tail = node
    current.next = node

To remove a known node, connect its neighbors around it. Update head or tail when one neighbor is absent.

    if node.prev is not null:
        node.prev.next = node.next
    else:
        head = node.next

    if node.next is not null:
        node.next.prev = node.prev
    else:
        tail = node.prev

These operations are O(1) when the node reference is already known. The important invariant is that every forward link and backward link agrees after each operation.

### Circular singly linked append

A circular singly linked list can be represented by its tail; the head is tail.next. In the empty case the new node points to itself.

    node = makeNode(value)
    if tail is null:
        node.next = node
        tail = node
    else:
        node.next = tail.next
        tail.next = node
        tail = node

Append takes O(1). Traverse from head until you return to head, not until a link becomes null.

### Circular doubly linked append

In a circular doubly linked list, the head and tail always link to each other. For the first node, both links point to itself.

    node = makeDoublyNode(value)
    if head is null:
        node.next = node
        node.prev = node
        head = node
        tail = node
    else:
        node.prev = tail
        node.next = head
        tail.next = node
        head.prev = node
        tail = node

The update preserves the circular boundary in both directions. Appending is O(1) when tail is maintained.

## Operation costs

| Operation | Singly linked list |
|---|---:|
| Read or update the head | O(1) |
| Access the item at position i | O(i) |
| Search by value | O(n) |
| Insert or remove at head | O(1) |
| Append with a maintained tail | O(1) |
| Append without a tail reference | O(n) |
| Insert after a known node | O(1) |
| Remove after a known predecessor | O(1) |

Linked lists make local rewiring cheap when the relevant node references are already available. They do not provide constant-time indexed access.

## Pointer identity and safe mutation

A reference identifies a particular node, not its value. Intersection questions ask whether two links reach the same node; matching values alone do not prove intersection.

Before overwriting a link, save any successor that is still needed. For example, when reversing a list, save current.next before assigning current.next to the previous node. Otherwise the unprocessed suffix becomes unreachable.

A dummy or sentinel node is an ordinary temporary node placed before the real head. It gives head-changing operations a predecessor and lets the same rewiring logic handle the first node and interior nodes. It is an algorithmic helper, not a distinct linked-list type.

## Tradeoffs

Linked lists are useful when an algorithm needs to splice, reorder, or delete nodes and already has the relevant references. Arrays are usually better when frequent indexed access or cache-friendly sequential storage matters. Choose based on the required operations rather than assuming one structure is universally faster.
