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

### Example: Capacity To Ship Packages Within D Days

Search the ship capacity. The heaviest package is a necessary lower bound because a package cannot be split. The sum of all weights is a sufficient upper bound because that capacity can carry every package in one day. For example, with weights `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]` and five days, capacity `15` ships the packages in five consecutive groups: `[1..5]`, `[6..7]`, `[8]`, `[9]`, `[10]`. Capacity `14` needs six days, so `15` is the minimum.

For one candidate capacity, greedily add each package to the current day until the next package would exceed the capacity; then start a new day with that package. No valid schedule can fit a longer initial prefix on day one than the greedy schedule; applying the same reasoning to each following day shows that greedy packing uses the fewest days. If that count is at most the allowed days, the capacity and every larger capacity are feasible.

The direct bounds need one pass over the packages, then binary search costs `O(n log S)`, where `S` is the total weight. The feasibility scan can stop as soon as it uses more than the allowed days. A separate reference implementation also finds a feasible upper bound by repeatedly doubling capacity; that approach is handy when a guaranteed upper bound is not obvious. See [LeetCode 1011 implementation](../CapacityToShipPackagesWithinDDays.java).

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

### Example: Allocate Minimum Pages

Books must remain in their original order, and each student receives a contiguous group. Search the maximum page total allowed per student. The lower bound is the thickest book, which must fit by itself; the upper bound is the total pages, which lets one student take every book. If there are more students than books, allocation is impossible because every student must receive at least one book.

For a candidate limit, greedily give each student as many consecutive books as fit, then start a new student before the next book would exceed the limit. This greedy process uses the fewest students for that limit. The candidate is feasible when it needs **at most** `k` students. If it needs fewer, split one or more contiguous groups until exactly `k` non-empty groups exist; `k <= n` guarantees enough books to do this. Requiring the greedy count to equal `k` would reject valid candidates—for example, a large page limit may let greedy fit all books in one group even though the books can still be split among several students.

For `[12, 34, 67, 90]` and `k = 2`, a limit of `113` yields groups `[12, 34, 67]` and `[90]`, so it is feasible. A smaller limit that requires three students is infeasible. Since feasible limits form a suffix, save a feasible midpoint and search left for the minimum. Use `long` for the total and running page count to avoid overflow. See the [GeeksforGeeks implementation](../AllocateMinimumNumberOfPages.java).

### Example: Koko Eating Bananas

Search the integer speed from `1` through the largest pile. For a candidate speed `k`, each pile needs `pile / k` full hours, plus one more hour when `pile % k != 0`. Add those hours across piles; if the total exceeds `h`, this speed is too slow.

For `[3, 6, 7, 11]` at speed `4`, the piles take `1 + 2 + 2 + 3 = 8` hours. At speed `3`, they take `1 + 2 + 3 + 4 = 10` hours, so speed `3` is infeasible for an 8-hour limit while speed `4` is feasible. Faster speeds stay feasible, making the hours check monotone.

The largest-pile speed takes one hour per pile, so it is feasible under the problem guarantee `h >= piles.length`. The hours check is `O(n)`, giving total complexity `O(n log M)` for largest pile size `M`. See the saved [LeetCode 875 implementation](../KokoEatingBananas.java).

### Example: Smallest Divisor Given a Threshold

Search divisors from `1` to `max(nums)`. For each candidate divisor `d`, sum `ceil(nums[i] / d)` across the array. A larger divisor never increases any rounded-up quotient, so once the sum is within the threshold, every larger divisor is feasible too.

For `nums = [1, 2, 5, 9]` and `threshold = 6`, divisor `4` gives `1 + 1 + 2 + 3 = 7`, which is too large. Divisor `5` gives `1 + 1 + 1 + 2 = 5`, which is feasible. Since `4` fails and `5` works, the smallest feasible divisor is `5`.

The upper bound `max(nums)` gives a quotient of `1` for every number, so it is feasible when `threshold >= nums.length`. The feasibility scan is `O(n)`, giving total time `O(n log M)` for `M = max(nums)`. See the saved [LeetCode 1283 implementation](../SmallestDivisorGivenAThreshold.java).

### Example: Minimum Days to Make m Bouquets

Search days from the earliest to the latest bloom day. On a candidate day, walk through the flowers in garden order. A flower is available when `bloomDay[i] <= day`; an unavailable flower breaks the current adjacent run. A run of length `L` contributes `L / k` bouquets, and bouquets from separate runs cannot share flowers.

For `bloomDay = [7, 7, 7, 7, 12, 7, 7]`, `m = 2`, and `k = 3`, day `7` gives runs of lengths `4` and `2`, so it yields `4 / 3 + 2 / 3 = 1` bouquet. Day `12` makes all seven flowers available in one run, giving `7 / 3 = 2` bouquets. Thus day `12` is feasible. Once a day is feasible, every later day is also feasible, so binary search finds the first feasible day.

Check `(long) m * k` against the number of flowers before searching; this both detects impossible requests and avoids `int` overflow. The feasibility scan is `O(n)`, so total time is `O(n log D)` for `D = max(bloomDay) - min(bloomDay) + 1`. See the saved [LeetCode 1482 implementation](../MinimumNumberOfDaysToMakeMBouquets.java).

## 4. Find the last feasible value

Some problems ask for the largest value that still satisfies a condition. Here feasibility often changes from true to false as the candidate increases:

```text
works  works  works  |  too large  too large
                    ^ last feasible answer
```

When `mid` works, save it and search right for a larger feasible value. When it fails, search left:

### Example: Aggressive Cows

This problem asks for the largest minimum distance that still allows all cows to be placed. After sorting stall positions, test one proposed distance by putting the first cow in the first stall, then greedily putting each next cow in the earliest stall at least that far from the previous cow. If all cows fit, that distance is feasible and every smaller distance is feasible too. If they do not fit, larger distances cannot work.

This is a last-feasible binary search: save the distance when placement succeeds and continue to the right; on failure, move left. Search from `1` to `arr[n - 1] - arr[0]`, the full span of the sorted stalls. For `[1, 2, 4, 8, 9]` and three cows, distance `3` works by choosing stalls at `1`, `4`, and `8`; distance `4` does not fit three cows, so the maximum feasible gap is `3`. See the [GeeksforGeeks implementation](../AggressiveCows.java).

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
