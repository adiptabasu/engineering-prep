# Engineering Learning Repository

A personal repository for studying, practicing, and documenting software engineering concepts through hands-on implementation.

The goal of this repository is to build **strong fundamentals, practical understanding, and long-term engineering knowledge** rather than simply collecting solutions.

---

## 📚 What This Repository Contains

### Data Structures & Algorithms

Systematic study and implementation of common data structures, algorithms, problem-solving patterns, and their trade-offs.

```text
DSA/
├── 01-Arrays/
├── 02-LinkedLists/
└── 03-Design/
```

Each problem focuses on understanding:

- The underlying problem
- Brute-force approaches
- Optimized approaches
- Algorithmic patterns
- Time and space complexity
- Why the optimization works
- Common mistakes
- Alternative approaches
- Potential variations and follow-up questions

Where applicable, implementations include links to the original problem for reference.

Detailed DSA progress and section-level notes are maintained in [`DSA/README.md`](DSA/README.md).

Topic revision guides are available in [`DSA/01-Arrays/README.md`](DSA/01-Arrays/README.md) and [`DSA/02-LinkedLists/README.md`](DSA/02-LinkedLists/README.md).

All completed LeetCode problem links are collected in [`DSA/LeetCode.md`](DSA/LeetCode.md).

---

### System Design

Study of designing scalable and maintainable software systems.

Topics will include:

- Requirements gathering
- Capacity estimation
- API design
- Database design
- Caching
- Messaging and asynchronous processing
- Distributed systems
- Scalability
- Reliability
- Availability
- Consistency
- Observability
- Security
- System architecture and trade-offs

---

### Java

Deepening knowledge of Java beyond day-to-day application development.

Areas include:

- Core Java
- Object-Oriented Programming
- Collections
- Generics
- Streams
- Concurrency
- JVM fundamentals
- Memory management
- Performance
- Modern Java features
- Design patterns

---

### Backend Engineering

Practical backend engineering concepts and implementations.

Topics may include:

- REST APIs
- Spring Boot
- Database interactions
- Transaction management
- Authentication and authorization
- API design
- Error handling
- Performance
- Distributed services
- Backend architecture

---

### SQL

Practice and notes covering:

- SQL fundamentals
- Joins
- Aggregations
- Subqueries
- Window functions
- Common Table Expressions
- Query optimization
- Database design
- Transactions
- Indexing

---

### AWS & Cloud

Notes, experiments, and implementations related to cloud engineering.

Topics may include:

- AWS fundamentals
- Compute
- Storage
- Databases
- Networking
- IAM
- Serverless architecture
- Monitoring
- Deployment
- Infrastructure concepts

---

### Interview & Engineering Notes

A collection of reusable notes covering:

- Problem-solving approaches
- Technical concepts
- Engineering trade-offs
- Architecture discussions
- Common interview topics
- Behavioral preparation
- Lessons learned from practice

---

## 🧠 Learning Philosophy

The emphasis is on **understanding rather than memorization**.

For algorithmic problems, the general workflow is:

```text
Understand the problem
        ↓
Attempt the problem independently
        ↓
Develop a brute-force solution
        ↓
Identify the bottleneck
        ↓
Look for useful input properties
        ↓
Derive the underlying pattern
        ↓
Derive the optimized solution
        ↓
Analyze complexity
        ↓
Implement
        ↓
Test
        ↓
Commit & push
        ↓
Explore variations
        ↓
Re-solve later
```

A particular emphasis is placed on **pattern recognition**.

The goal is not simply to remember that a particular problem uses a HashMap, Sliding Window, Prefix Sum, Stack, or Dynamic Programming.

Instead, the goal is to recognize **why** a particular technique is appropriate based on:

- The constraints
- The structure of the input
- The operations required
- Mathematical relationships
- State that needs to be maintained
- Invariants that remain true during the algorithm

The same principle is applied to system design and software engineering topics:

> Understand the problem first, then understand why a particular solution works.

---

## 🛠️ Primary Technologies

The repository primarily uses:

- Java
- SQL
- Spring Boot
- JavaScript / TypeScript
- React
- Node.js
- AWS

Additional technologies will be added as they become relevant to the learning process.

---

## 📈 Progress

Progress is tracked by topic rather than by simply counting problems.

### Data Structures & Algorithms

- 🟢 Complexity Analysis
- 🟢 Arrays & Hashing — Complete
- 🟢 Linked Lists — Complete
- ⚪ Stacks & Queues
- ⚪ Binary Search
- ⚪ Recursion & Backtracking
- ⚪ Trees & BST
- ⚪ Heaps
- ⚪ Graphs
- ⚪ Greedy Algorithms
- ⚪ Dynamic Programming
- ⚪ Tries
- ⚪ Bit Manipulation
- ⚪ Advanced Topics

### Arrays & Hashing

**24 problems implemented and pushed.**

The section currently covers:

- HashMap / HashSet
- Frequency counting
- Two Pointers
- Sliding Window
- Prefix Sum
- Prefix Remainders
- Prefix / Suffix techniques
- Heap / Priority Queue
- Kadane's Algorithm
- Dynamic Programming
- Stack-based reasoning

The problems are documented individually in the Java source files and summarized in the [Arrays & Hashing revision guide](DSA/01-Arrays/README.md).

### Linked Lists

**15 problems implemented and pushed.** The section covers traversal, reversal, fast and slow pointers, dummy nodes, cycle detection, intersection, deep copying, list rearrangement, and grouped operations. The [Linked Lists revision guide](DSA/02-LinkedLists/README.md) records the problem set and the key reasoning points to revisit.

The LRU Cache implementation is in [`DSA/03-Design/LRUCache.java`](DSA/03-Design/LRUCache.java) and is indexed as a design problem in [`DSA/LeetCode.md`](DSA/LeetCode.md).

The progress indicators represent the current state of the learning journey and may change as topics are revisited at greater depth.

---

## 🔗 Problem References

For problems originating from external platforms, the implementation includes a reference link near the beginning of the source file.

A central index of completed LeetCode problems is also maintained in [`DSA/LeetCode.md`](DSA/LeetCode.md).

This keeps the repository useful as a long-term reference while making it easy to revisit the original problem and its constraints.

---

## 🎯 Purpose

This repository is primarily a **personal learning and engineering knowledge base**.

It is intentionally built incrementally:

- Learn a concept
- Attempt it independently
- Implement it
- Understand the trade-offs
- Document the important ideas
- Commit and push the work
- Revisit difficult areas
- Re-solve previously completed problems
- Build upon previous knowledge

The repository will evolve as new concepts are learned and existing ones are revisited at greater depth.