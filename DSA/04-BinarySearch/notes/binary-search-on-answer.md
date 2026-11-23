# Binary Search on an Answer

## 1. The answer may be a number, not an index

Some problems ask for the smallest capacity, speed, time, or distance that satisfies a condition. There may be no sorted array to search. Instead, imagine all possible answers arranged from smallest to largest and ask whether each candidate answer is feasible.

Binary search applies if feasibility changes only once. For a minimum feasible answer, it often looks like:

```text
too small  too small  too small  |  works  works  works
                                 ^ first feasible answer
```

If a candidate works, every larger value may also work. If it fails, smaller candidates cannot work either. That monotonic relationship lets us discard half of the answer range.

## 2. A concrete example: shipping capacity

Suppose packages must be shipped in order within a fixed number of days. Increasing the ship's capacity can never require more days, so:

- a capacity that is too small is infeasible;
- once a capacity is sufficient, every larger capacity is sufficient too.

For weights `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]` and 5 days, the minimum capacity is at least the heaviest package (`10`) and at most the sum of all weights (`55`). These bounds reduce the candidates to a finite numeric interval.

To check one candidate capacity, simulate loading packages in order. If adding the next package would exceed capacity, start another day. The feasibility check is linear in the number of packages.

## 3. Find the first feasible value

Use this template when `feasible(x)` changes from false to true as `x` increases:

```java
long low = smallestPossibleAnswer;
long high = largestPossibleAnswer; // establish that high is feasible
long answer = high;

while (low <= high) {
    long mid = low + (high - low) / 2;

    if (feasible(mid)) {
        answer = mid;       // mid works; see if a smaller value works
        high = mid - 1;
    } else {
        low = mid + 1;      // mid fails; try larger values
    }
}

return answer;
```

The saved `answer` is the best feasible value found so far. The upper bound should be known feasible; otherwise a no-solution case needs explicit handling.

## 4. Find the last feasible value

Some problems ask for the largest value that still satisfies a condition. Here feasibility often changes from true to false as the candidate increases:

```text
works  works  works  |  too large  too large
                    ^ last feasible answer
```

When `mid` works, save it and search right for a larger feasible value. When it fails, search left:

```java
long low = smallestPossibleAnswer;
long high = largestPossibleAnswer;
long answer = smallestPossibleAnswer;

while (low <= high) {
    long mid = low + (high - low) / 2;

    if (feasible(mid)) {
        answer = mid;
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}

return answer;
```

This version assumes the lower bound is feasible. If no value may work, choose and check an appropriate no-solution representation.

## 5. Design the feasibility check

Before writing the binary-search loop, answer these questions:

1. What does a candidate answer mean in the original problem?
2. Given a candidate, how can I check if it works?
3. Does a larger candidate make feasibility easier or harder?
4. Is feasibility monotone—does it change at most once?
5. What are safe lower and upper bounds, and is either endpoint guaranteed feasible?

The check does not have to be constant time. If it takes `O(n)`, the full algorithm may take `O(n log R)`, where `R` is the number of possible answer values.

## 6. How to find answer bounds

Bounds should come from the problem, not arbitrary guesses. Examples:

- **Minimum capacity to carry items:** at least the heaviest single item; at most the sum of all items.
- **Minimum eating or processing speed:** at least `1`; an upper bound can be the largest pile or workload.
- **Maximum minimum distance:** at least `0`; an upper bound can be the full coordinate span.
- **Minimum time:** often at least `0` or `1`; find a safe time by considering the slowest or fastest allowed process.

Use `long` when sums, products, coordinate spans, or candidate times can exceed `int`. The midpoint also needs to use the same wide type.

## Integer square root: largest feasible candidate

For `Sqrt(x)`, the candidate answer is an integer root `r`. The predicate `r * r <= x` changes once as `r` increases: smaller candidates are feasible, larger ones are too large. We want the **largest feasible** root, so when a midpoint works, save it and continue searching right.

```java
public int mySqrt(int x) {
    int low = 1;
    int high = x;
    int answer = 0;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        long square = (long) mid * mid;

        if (square <= x) {
            answer = mid;
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return answer;
}
```

The cast must come before multiplication. Writing `(long) (mid * mid)` would cast only after the `int` product might already have overflowed. For `x == 0`, the initial interval is empty, so the default answer `0` is returned. See the saved [LeetCode 69 implementation](../SqrtX.java).

## 7. Complexity

If there are `R` possible integer answers, binary search checks `O(log R)` candidates. If `feasible(mid)` takes `C` time, total time is **O(C log R)**. The search loop itself uses **O(1)** extra space, not counting space used by the feasibility check.

## 8. Common mistakes

- **Assuming monotonicity without proving it.** If feasible and infeasible values alternate, binary search is not valid.
- **Searching the input array when the answer is a capacity or time.** The search range is the possible answer values.
- **Using the wrong direction.** Minimum feasible and maximum feasible have opposite updates when a candidate works.
- **Bad bounds.** If the true answer lies outside `[low, high]`, binary search cannot find it.
- **No feasible answer or endpoint assumptions.** Decide what the method should return if no candidate works.
- **Overflow in the check.** Accumulated sums and products may overflow even when each item fits in an `int`.

## 9. Recognition checklist

This pattern is a candidate when the prompt asks for a minimum or maximum numeric value and you can write a yes/no check for any proposed value. Ask whether the check has a one-way transition before choosing binary search.

## Related problems

- [LeetCode 69 — Sqrt(x)](https://leetcode.com/problems/sqrtx/)
- [LeetCode 1011 — Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)
- [LeetCode 875 — Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)
- [LeetCode 1552 — Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/)
