# Copying a list with random pointers

## Copy nodes, not just values

Each original node has a next link and a random link that may point to any node in the list or to null. A deep copy needs one new node per original node and must make every copied link point to the corresponding copied node. Equal values are not enough to identify that correspondence.

## Method 1: map original nodes to copies

First create a copy for every original node and store the relationship in a map. Then assign each copy's links by looking up the copies of the original targets.

    copies = empty map
    for each original node:
        copies[original] = makeNode(original.value)

    copyOf(node):
        return null if node is null else copies[node]

    for each original node:
        copies[original].next = copyOf(original.next)
        copies[original].random = copyOf(original.random)

    return copyOf(head)

The map preserves node identity. This method takes O(n) time and O(n) auxiliary space.

## Method 2: interleave copies

If temporary mutation is allowed, insert each copy immediately after its original. Then original.random.next is the copy of original.random. Finally, separate the interleaved chain into original and copied lists.

    original = head
    while original is not null:
        copy = makeNode(original.value)
        copy.next = original.next
        original.next = copy
        original = copy.next

    original = head
    while original is not null:
        copy = original.next
        if original.random is not null:
            copy.random = original.random.next
        original = copy.next

    copyHead = head.next if head is not null else null
    original = head
    while original is not null:
        copy = original.next
        nextOriginal = copy.next
        original.next = nextOriginal
        copy.next = nextOriginal.next if nextOriginal is not null else null
        original = nextOriginal

    return copyHead

The adjacency invariant makes each corresponding copy discoverable without a map. This uses O(1) auxiliary space beyond the copied nodes, but temporarily changes the input. It assumes random links target nodes in this same list. Restore the original list exactly during separation.

## Practice link

[Copy List with Random Pointer](../CopyListWithRandomPointer.java)
