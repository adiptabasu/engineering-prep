# Adding numbers with carry

## Process the least significant digits first

When each list stores a number in reverse digit order, the head contains the least significant digit. This is the order needed for ordinary addition: add corresponding digits, emit the result digit, and carry the overflow to the next position.

    dummy.next = null
    tail = dummy
    carry = 0

    while first is not null or second is not null or carry is not 0:
        total = carry
        if first is not null:
            total = total + first.value
            first = first.next
        if second is not null:
            total = total + second.value
            second = second.next

        digit = total mod 10
        carry = floor(total / 10)
        append a new node with digit to tail

    return dummy.next

The loop continues while either list has digits or the final carry remains. Starting from a dummy node makes the first output digit follow the same append rule as every later digit.

## Small trace

The number 342 is stored as 2 → 4 → 3, and 465 as 5 → 6 → 4. Add by position:

- 2 + 5 = 7: emit 7, carry 0.
- 4 + 6 = 10: emit 0, carry 1.
- 3 + 4 + 1 = 8: emit 8, carry 0.

The result list 7 → 0 → 8 represents 807.

## Complexity and assumptions

For list lengths m and n, time is O(max(m, n)) and output space is O(max(m, n) + 1). The extra node accounts for a final carry. This form assumes non-negative numbers whose digits are stored in reverse order.

## Practice link

[Add Two Numbers](../AddTwoNumbers.java)
