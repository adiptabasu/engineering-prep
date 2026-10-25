# Hash table mechanics

## From key to storage location

A hash table uses a hash function to map a key to an integer hash. It then maps that hash to a location in an internal table. A lookup uses the same process to find candidate storage and then checks key equality to identify the requested key.

Hashing narrows the search; it does not prove that two keys are equal. Different keys can produce the same hash or map to the same table location. This is a collision, and the table must resolve it.

## Collision strategies

Two common strategies are:

- **Separate chaining:** Each table location points to a collection of entries. Colliding entries share that location and are searched within that collection.
- **Open addressing:** Entries live in the table itself. When a location is occupied by a different key, a probe sequence checks other locations. Lookup and deletion must follow the same probing rules; deletion often needs a marker so it does not break a probe chain.

These are conceptual strategies. Real implementations may combine optimizations or use other layouts.

## Load factor and resizing

The load factor is the number of stored entries divided by the table capacity. As it grows, collisions or probe lengths tend to increase. A table can allocate a larger capacity and redistribute entries when a chosen threshold is crossed.

One insertion that triggers resizing can take O(n) time because entries are moved. If capacity grows geometrically, the cost of occasional resizing is spread over many insertions: insertion remains amortized O(1), assuming the usual resizing strategy and well-behaved hashing.

## Why lookup is expected O(1), not guaranteed O(1)

With hashes spread well across the table and a controlled load factor, only a small number of candidates are expected per operation. That gives expected O(1) insertion, lookup, and deletion. In the worst case, many keys collide and an operation may take O(n). The exact guarantee depends on the hash function, collision strategy, resizing behavior, and implementation.

This is why a DSA solution using a map or set is usually analyzed as expected O(n) for n operations, not as an unconditional worst-case bound.

## Keys and equality

For a key used in a hash table, equal keys must produce equal hashes. If a key changes in a way that affects its hash or equality while stored, the table may no longer find it in the expected location. Prefer stable keys, and ensure equality checks represent the problem's actual notion of sameness.

For compound keys, preserve every field needed to distinguish states. A canonical key must also be unambiguous: for example, encode separators or use a structured tuple rather than joining variable-length values without boundaries.

## Reasoning checks

- Is the operation lookup, membership, counting, or grouping? Choose a map or set accordingly.
- Does key equality mean equal value, equal contents, or the same object?
- Are you relying on expected constant-time operations? Say so in the complexity analysis.
- Could one input item match itself, or must the map contain only earlier items?
- Does the table key remain unchanged while stored?

See [hashing and frequency](hashing-and-frequency.md) for how these mechanics support common array algorithms.
