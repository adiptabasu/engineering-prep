# Data Structures & Algorithms

This directory contains the DSA learning progression, implementations, revision notes, and problem index.

## Current Progress

| Section | Status | Implemented |
|---|---:|---:|
| 01 — Arrays & Hashing | Complete | 24 / 24 |
| 02 — Linked Lists | Complete | 15 / 15 |
| 03 — Stack & Queue | Complete for current problem set | 30 problems recorded |
| 04 — Binary Search | In progress | 17 problems recorded |
| 05 — Trees | Planned | — |
| 06 — Heap / Priority Queue | Planned | — |
| 07 — Graphs | Planned | — |
| 08 — Backtracking | Planned | — |
| 09 — Dynamic Programming | Planned | — |

> Future topic folders will be created when the corresponding section begins. `DSN-Design/` is a separate category for design problems, not DSA topic 03; the numbered roadmap can evolve as the learning progression develops.

## Learning Method

Work through one topic and one pattern at a time, at a pace that fits the available time. Use the topic README for the problem list and revision guide, and its notes index for language-neutral explanations. Current notes are available for [Arrays & Hashing](01-Arrays/notes/README.md), [Linked Lists](02-LinkedLists/notes/README.md), [Stack & Queue](03-StackQueue/notes/README.md), and [Binary Search](04-BinarySearch/notes/README.md).

For a new pattern, first try to explain the relevant idea from memory. Read the theory notes when you need to clarify the model or invariant, then attempt a representative problem without opening its solution. Ask for hints or simpler examples if stuck; after solving, explain why the approach works and its complexity. Revisit the pattern until several representative problems can be solved and explained independently before moving on.

Each problem follows:

**Understand → Derive → Implement → Test → Explain → Commit → Re-solve**

During first attempts, the pattern is not revealed unless needed. The goal is to derive the solution from:

- Input constraints
- Ordering properties
- Mathematical relationships
- Required operations
- State / invariants
- Data-structure behavior

## Topic Guides & Notes

- [01 — Arrays & Hashing](01-Arrays/README.md) ([notes](01-Arrays/notes/README.md), [journal](01-Arrays/LEARNING-JOURNAL.md))
- [02 — Linked Lists](02-LinkedLists/README.md) ([notes](02-LinkedLists/notes/README.md), [journal](02-LinkedLists/LEARNING-JOURNAL.md))
- [03 — Stack & Queue](03-StackQueue/README.md) ([notes](03-StackQueue/notes/README.md), [journal](03-StackQueue/LEARNING-JOURNAL.md))
- [04 — Binary Search](04-BinarySearch/README.md) ([notes](04-BinarySearch/notes/README.md), [journal](04-BinarySearch/LEARNING-JOURNAL.md))
- [Design (`DSN-Design/`)](DSN-Design/LEARNING-JOURNAL.md)
- [Central LeetCode Problem Index](LeetCode.md)

Detailed problem histories and live progress live in each topic's `LEARNING-JOURNAL.md` and [`../LEARNING-HOME.md`](../LEARNING-HOME.md) rather than being duplicated here.

## Git Practice

Every completed problem should be:

1. Added to the correct section
2. Committed with a focused commit message
3. Pushed before moving to the next problem

Reference implementations include the original LeetCode URL, explanation, complexity, pitfalls, interview follow-ups, and local tests where appropriate.
