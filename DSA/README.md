# Data Structures & Algorithms

This directory contains the DSA learning progression, implementations, revision notes, and problem index.

## Current Progress

| Section | Status | Implemented |
|---|---:|---:|
| 01 — Arrays & Hashing | Complete | 24 / 24 |
| 02 — Linked Lists | Complete | 15 / 15 |
| 03 — Stack & Queue | Planned | — |
| 04 — Binary Search | Planned | — |
| 05 — Trees | Planned | — |
| 06 — Heap / Priority Queue | Planned | — |
| 07 — Graphs | Planned | — |
| 08 — Backtracking | Planned | — |
| 09 — Dynamic Programming | Planned | — |

> Future folders will be created when the corresponding section begins. The roadmap can evolve as the learning progression develops.

## Learning Method

Each problem follows:

**Understand → Derive → Implement → Test → Explain → Commit → Re-solve**

During first attempts, the pattern is not revealed unless needed. The goal is to derive the solution from:

- Input constraints
- Ordering properties
- Mathematical relationships
- Required operations
- State / invariants
- Data-structure behavior

## Completed: Arrays & Hashing

The first section currently contains **24 implemented LeetCode problems**.

The problems cover:

- Hashing and lookup
- Frequency counting
- Sets
- Sorting + Two Pointers
- Fixed Sliding Window
- Variable Sliding Window
- Prefix Sum
- Prefix Remainders
- Prefix/Suffix techniques
- Running-state algorithms
- Dynamic Programming

See [`01-Arrays/README.md`](01-Arrays/README.md) for the at-a-glance revision sheet.

See [`LeetCode.md`](LeetCode.md) for all canonical LeetCode links in one place.

## Completed: Linked Lists

The second section contains **15 implemented LeetCode problems**. It develops pointer reasoning through traversal, reversal, fast and slow pointers, dummy nodes, cycle detection, intersection, deep copy, and list rearrangement.

See [`02-LinkedLists/README.md`](02-LinkedLists/README.md) for the problem list and revision notes. The LRU Cache is maintained separately under [`03-Design/`](03-Design/) because it combines a hash map with a doubly linked list as a design problem.

## Git Practice

Every completed problem should be:

1. Added to the correct section
2. Committed with a focused commit message
3. Pushed before moving to the next problem

Reference implementations include the original LeetCode URL, explanation, complexity, pitfalls, interview follow-ups, and local tests where appropriate.
