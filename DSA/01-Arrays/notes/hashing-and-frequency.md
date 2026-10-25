# Hashing and frequency counting

## When this pattern helps

Use a set or map when the problem asks whether something has appeared, how often it appears, or which earlier value would complete a condition. Hash-based lookup is typically constant time on average, so one scan can replace repeated searches.

## Membership and complements

For a duplicate check, remember values already seen. For a target-sum lookup, each current value x needs the complement target minus x. Check for the complement before inserting x so the same array position cannot be used twice.

    seen = empty set
    for each value x in values:
        if target - x is in seen:
            return a matching pair
        add x to seen

Expected time is O(n); space is O(n). Hash lookup is average-case, not a universal worst-case guarantee.

## Frequency maps

A frequency map stores how many times each key occurs.

    count = empty map with default value 0
    for each item x in items:
        count[x] = count[x] + 1

Two collections are anagrams when every symbol has the same count in both. For a known small alphabet, a fixed-size count vector can replace a map. For grouping, use a canonical key derived from the counts or from sorted symbols. The key must be unambiguous.

## Why lookup and counting work

Before processing an item, the seen set contains exactly the earlier values. For Two Sum, if the current value is x, any valid partner must equal target minus x. Finding that complement in seen proves a pair with two distinct positions; not finding it means no earlier item can pair with this x. Adding x afterward preserves the invariant for the next position.

For example, with values [4, 1, 5] and target 6, the first two items add 4 and 1 to seen. At 5, the required complement is 1, which is already present. The search does not compare 5 with every earlier value.

A frequency map is a compact summary of a multiset: for every possible item, it records how many copies occur. Two strings are anagrams exactly when every symbol has the same count in both. The order of symbols is irrelevant, so a sorted representation or fixed-order count vector can serve as a canonical grouping key.

Expected O(n) time comes from one pass with average-case constant-time map or set operations. It relies on the data structure's expected lookup behavior, not on a guarantee that every lookup is worst-case constant time.
## Reasoning checks

- Is equality based on values, symbol counts, or object identity?
- Do duplicates matter?
- Does the key preserve all information needed to decide a match?
- Can a value be matched with itself, or must the two positions differ?
- If the alphabet is not fixed, what assumptions does a count vector make?

## Common pitfalls

- Insert before checking in Two Sum, allowing one item to match itself.
- Forget that a frequency map records multiplicity, while a set records only membership.
- Build ambiguous signatures by concatenating counts without separators or fixed-width encoding.
- Claim worst-case O(1) lookup for a hash table; the usual analysis is expected O(1).

## Practice links

[Two Sum](../TwoSum.java) · [Contains Duplicate](../ContainsDuplicate.java) · [Valid Anagram](../ValidAnagram.java) · [Group Anagrams](../GroupAnagrams.java)
