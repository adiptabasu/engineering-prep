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
