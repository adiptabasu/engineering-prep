# Queue design and streams

A queue handles items in the order they arrive: the oldest waiting item leaves first. Design problems ask how to represent that rule with the operations and storage available.

## Queue using two stacks

A stack removes its newest item first, which is the opposite of a queue. Two stacks reverse the order twice:

- The **input** stack receives new items.
- The **output** stack provides the next item to remove.
- When output is empty, move everything from input to output.

Suppose `A` arrives before `B`:

```text
enqueue A, enqueue B:
input  (top at right): [A, B]
output:                []

transfer because output is empty:
input:                 []
output (top at right): [B, A]

dequeue:
pop output top → A    (the oldest item)
```

Do not transfer input while output still has items. If `C` arrives after `A` and `B`, the output stack must finish returning `A`, then `B`, before `C`.

Each item moves from input to output once. So a long sequence of queue operations takes O(n) total transfer work, or amortized O(1) per operation. One dequeue may still take O(n) if it triggers a transfer.

## Stack using a queue

To make a queue act like a stack, one option is to put each new item at the back, then rotate the older items behind it. The new item becomes the front and can be removed first. This makes pop simple but can make push take O(n) time. Always check which operations the problem requires to be efficient.

## Circular queue with an array

A circular queue reuses an array after items are removed. Instead of shifting every remaining item, keep:

- `head`: the index of the oldest item;
- `size`: how many items are stored;
- `capacity`: how many slots exist.

The next tail slot is `(head + size) % capacity`. The `%` operation wraps an index back to zero after it reaches the capacity.

Example with capacity 3:

```text
enqueue A, B, C → slots [A, B, C], full
dequeue A      → head moves from 0 to 1; size becomes 2
enqueue D      → tail wraps around and uses slot 0
logical queue  → B, C, D
```

Check `size == 0` before removal or front access, and `size == capacity` before insertion. Tracking size distinguishes a full queue from an empty one even when the indices wrap.

## Recent-event windows

Some queues keep timestamps rather than jobs. If events arrive in increasing time order and only the recent time interval matters, add the new timestamp at the back and remove old timestamps from the front.

For a time `t` and a window of 3000 units that includes both endpoints, keep timestamps satisfying:

```text
t - 3000 <= timestamp <= t
```

Remove from the front while `timestamp < t - 3000`. The queue size then gives the number of recent events. Pay attention to whether the problem includes the lower endpoint; that determines whether the comparison is `<` or `<=`.

## Design questions to ask

1. Must the next item be the newest or the oldest?
2. Which operations need to be fast?
3. How will the structure represent empty and full states?
4. Can each item be moved or removed only a limited number of times?
5. What happens when an index or array slot wraps around?

## Practice links

[Implement Queue using Stacks](../MyQueue.java) · [Implement Stack using Queues](../MyStack.java) · [Design Circular Queue](../MyCircularQueue.java) · [Number of Recent Calls](../RecentCounter.java)
