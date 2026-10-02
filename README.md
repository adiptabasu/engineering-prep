```markdown
# Switch

A structured preparation repository for my next career move.

The goal is not simply to solve interview questions, but to build the
depth of understanding required to perform well in senior-level
software engineering interviews.

---

## Goals

Primary goal:

> Prepare for senior software engineering interviews at top-tier
> technology companies.

The preparation is focused on:

- Data Structures & Algorithms
- System Design
- Java
- Backend Engineering
- SQL
- AWS
- Interview Communication
- Problem Solving
- Behavioral / Leadership Interviews

The emphasis is on understanding concepts deeply enough to solve
unfamiliar problems rather than memorizing solutions.

---

# Repository Structure

```text
Switch/
│
├── README.md
│
├── DSA/
│   ├── 00-Complexity/
│   ├── 01-Arrays/
│   └── README.md
│
├── SystemDesign/
│   └── README.md
│
├── Java/
│   └── README.md
│
├── SQL/
│   └── README.md
│
├── Backend/
│   └── README.md
│
├── AWS/
│   └── README.md
│
└── Interview/
    └── README.md
```

---

# Preparation Philosophy

The preparation follows a teacher/interviewer approach.

For DSA problems, the general process is:

```text
Understand
    ↓
Derive
    ↓
Implement
    ↓
Solve
    ↓
Explain
    ↓
Re-solve
```

The objective is to understand:

- Why the solution works
- Why the chosen data structure is appropriate
- What invariant is being maintained
- How the algorithm scales
- What trade-offs exist
- What happens when constraints change
- How the interviewer could modify the problem

---

# DSA

## Progress

### Foundations

- 🟢 Complexity Analysis / Algorithmic Thinking

### Arrays / Hashing

| # | Problem | Pattern / Concept | Status |
|---|---|---|---|
| 1 | Two Sum | HashMap / Complement Lookup | 🟢 Done |
| 2 | Contains Duplicate | HashSet / Membership | 🟢 Done |
| 3 | Valid Anagram | Frequency Counting | 🟢 Done |
| 4 | Group Anagrams | Canonical Signature / HashMap Grouping | 🟢 Done |
| 5 | Top K Frequent Elements | Frequency Map / Min-Heap / PriorityQueue | 🟢 Done |

### Next

- ⬜ Continue Arrays / Hashing
- ⬜ Two Pointers
- ⬜ Sliding Window
- ⬜ Linked Lists
- ⬜ Stacks & Queues
- ⬜ Binary Search
- ⬜ Recursion & Backtracking
- ⬜ Trees / BST
- ⬜ Heaps
- ⬜ Graphs
- ⬜ Greedy
- ⬜ Dynamic Programming
- ⬜ Tries
- ⬜ Bit Manipulation
- ⬜ Advanced Data Structures

---

# DSA Concepts Learned

## Complexity Analysis

- Big-O notation
- Time complexity
- Space complexity
- Sequential loops
- Nested loops
- Logarithmic algorithms
- Amortized analysis
- Distinguishing `O(n)` from `O(log n)`
- Understanding constant factors

---

## HashMap

Used for:

- Key-value lookup
- Complement lookup
- Frequency counting
- Canonical-signature grouping

Typical expected complexity:

```text
get()  -> O(1)
put()  -> O(1)
```

Worst-case behavior can differ depending on implementation and
hash distribution, so interview explanations should normally refer
to HashMap operations as expected / average O(1).

---

## HashSet

Used when we only need to know whether a value has already been seen.

Typical expected complexity:

```text
add()      -> O(1)
contains() -> O(1)
```

Example pattern:

```java
if(!set.add(value))
{
    return true;
}
```

`Set.add()` returns:

```text
true  -> value was not already present
false -> value was already present
```

---

## Frequency Counting

Many problems can be transformed into:

```text
value -> frequency
```

or, for a fixed alphabet:

```text
frequency[value]
```

Examples:

- Valid Anagram
- Group Anagrams
- Top K Frequent Elements

---

## Fixed-Size Frequency Array

When the input alphabet is known and small, a fixed-size array can
be more efficient than a HashMap.

Example for lowercase English letters:

```java
int[] frequency = new int[26];

frequency[character - 'a']++;
```

Because the array always contains 26 elements:

```text
Space = O(1)
```

even if the input string becomes extremely large.

---

## Canonical Representation

A canonical representation converts equivalent objects into the
same representation.

Example:

```text
eat
tea
ate
```

All have the same character-frequency distribution.

Therefore they can share the same canonical signature.

This allows:

```text
signature -> group of equivalent objects
```

This pattern is particularly important for HashMap-based grouping.

---

## Java Arrays as HashMap Keys

Java arrays use identity-based `equals()` and `hashCode()`.

Therefore:

```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};

a.equals(b); // false
```

even though their contents are identical.

For content-based comparison:

```java
Arrays.equals(a, b);
Arrays.hashCode(a);
```

can be used.

Mutable objects should also be treated carefully when used as
HashMap keys because changing a key after insertion can break the
map's ability to locate the entry.

---

# TreeMap

TreeMap maintains its keys in sorted order.

Typical operation complexity:

```text
put()    -> O(log n)
get()    -> O(log n)
remove() -> O(log n)
```

A custom comparator can be used to define ordering.

Example:

```java
new TreeMap<>((a, b) -> ...)
```

Used in:

```text
Top K Frequent Elements
```

to sort elements by their frequency.

---

# PriorityQueue / Heap

Java's:

```java
PriorityQueue
```

provides heap functionality.

By default:

```java
PriorityQueue<Integer> heap = new PriorityQueue<>();
```

creates a min-heap.

Therefore:

```java
heap.peek();
```

returns the smallest element.

And:

```java
heap.poll();
```

removes the smallest element.

---

# Top-K Pattern

When looking for the K largest elements, a useful pattern is:

```text
Maintain a MIN-HEAP of size K
```

Why?

Suppose the heap contains the current K strongest candidates.

The weakest candidate is the one with the smallest value/frequency.

A min-heap keeps that weakest candidate at the top.

When a new candidate arrives:

```text
Add candidate
     ↓
Heap size > K?
     ↓
Yes
     ↓
Remove minimum
```

This maintains the invariant:

> The heap contains the K strongest candidates encountered so far.

Typical complexity:

```text
O(n + m log k)
```

where:

```text
n = total number of elements
m = number of distinct candidates
k = requested number of results
```

This can be significantly better than sorting all candidates when:

```text
k << m
```

---

# Completed Problem Notes

## 1. Two Sum

Pattern:

```text
HashMap
Complement lookup
```

Core insight:

```text
required = target - current
```

Check whether the required value has already been seen.

Complexity:

```text
Time  -> O(n) expected
Space -> O(n)
```

Important detail:

Check the complement before inserting the current value.

This correctly handles duplicates such as:

```text
[3, 3], target = 6
```

---

## 2. Contains Duplicate

Patterns:

```text
HashSet
HashMap
```

Preferred approach:

```java
if(!set.add(num))
{
    return true;
}
```

Complexity:

```text
Time  -> O(n) expected
Space -> O(n)
```

Alternative:

Sort the array and compare adjacent elements.

Complexity:

```text
Time -> O(n log n)
```

This demonstrates a time/space trade-off.

---

## 3. Valid Anagram

Patterns:

```text
Frequency Counting
HashMap
Fixed-size Array
```

General approach:

```text
frequency(s) - frequency(t)
```

Every final frequency must equal zero.

For lowercase English letters:

```java
int[] frequency = new int[26];
```

Complexity:

```text
HashMap:
Time  -> O(n) expected
Space -> O(k)

int[26]:
Time  -> O(n)
Space -> O(1)
```

Important constraint:

The `int[26]` approach only works when characters are guaranteed
to be lowercase English letters.

---

## 4. Group Anagrams

Pattern:

```text
Canonical Representation
        ↓
HashMap
        ↓
Groups
```

For each string:

```text
String
   ↓
Frequency Array
   ↓
Canonical Signature
   ↓
HashMap Key
```

Example:

```text
eat
tea
ate
```

all produce the same frequency signature.

Complexity:

```text
Time  -> O(n * k)
Space -> O(n) auxiliary for signatures/groups
Output -> O(n * k)
```

Alternative:

Sort every string and use the sorted string as the key.

Complexity:

```text
O(n * k log k)
```

---

## 5. Top K Frequent Elements

Two approaches implemented.

### TreeMap

```text
Frequency Map
      ↓
TreeMap sorted by frequency
      ↓
Take first K
```

Complexity:

```text
Time  -> O(n + m log m)
Space -> O(m)
```

### Min-Heap

```text
Frequency Map
      ↓
Min-Heap of size K
      ↓
Discard weakest candidate
      ↓
Remaining K = answer
```

Complexity:

```text
Time  -> O(n + m log k)
Space -> O(m + k)
```

Important pattern:

> For Top K largest elements, maintain a Min-Heap of size K.

---

# Problem Structure

Every DSA problem committed to this repository should contain:

1. LeetCode URL
2. Problem statement
3. Approach
4. Brute-force approach where relevant
5. Optimized approach
6. Key insight
7. Why the solution works
8. Complexity analysis
9. Common mistakes
10. Interview follow-ups
11. Local `main()` tests

Solutions should use normal Java class names rather than the
LeetCode-specific:

```java
class Solution
```

For example:

```java
public class TwoSum
```

---

# Git Commit Convention

Use descriptive conventional commits.

Examples:

```text
feat(dsa): solve two sum using hashmap

feat(dsa): solve contains duplicate using hashset

feat(dsa): solve valid anagram using frequency counting

feat(dsa): solve group anagrams using frequency signature

feat(dsa): add top k frequent elements solutions
```

---

# System Design

Status:

⬜ Not started

System Design will begin after building a strong DSA foundation.

Planned areas include:

- Requirements gathering
- Functional requirements
- Non-functional requirements
- Capacity estimation
- API design
- Database design
- Caching
- Message queues
- Distributed systems
- Scalability
- Availability
- Consistency
- Reliability
- Observability
- Security
- AWS architecture
- Trade-off analysis

---

# Java

Areas to cover:

- Core Java
- OOP
- Collections
- Generics
- Streams
- Concurrency
- Multithreading
- JVM
- Memory management
- Garbage collection
- Java 8+
- Modern Java features
- Performance
- Design patterns

---

# SQL

Areas to cover:

- SQL fundamentals
- Joins
- Aggregations
- Subqueries
- CTEs
- Window functions
- Indexes
- Query optimization
- Transactions
- Isolation levels
- Database design
- Oracle
- MS SQL

---

# Backend

Areas to cover:

- Spring Boot
- REST APIs
- Spring Security
- JPA / Hibernate
- Database design
- Transactions
- Distributed systems
- Microservices
- API design
- Messaging
- Caching
- Observability
- Performance optimization
- Production troubleshooting

---

# AWS

Areas to cover:

- EC2
- S3
- Lambda
- API Gateway
- RDS
- DynamoDB
- CloudFront
- Route 53
- IAM
- VPC
- SQS
- SNS
- CloudWatch
- ECS / EKS
- Architecture patterns
- Cost optimization
- Security

---

# Interview

Eventually this repository will also contain:

- Mock interviews
- DSA interview explanations
- System Design interviews
- Behavioral questions
- Leadership questions
- Resume preparation
- Project discussions
- STAR-format examples
- Senior-level technical discussions

---

# Learning Standard

The objective is not:

```text
"How many LeetCode problems have I solved?"
```

The objective is:

```text
"Can I solve a new problem I have never seen before?"
```

For every important problem, I should be able to explain:

- What is the brute-force solution?
- Why is it too slow or memory-heavy?
- What observation allows optimization?
- Which data structure supports that optimization?
- What invariant does the algorithm maintain?
- Why is the algorithm correct?
- What is the time complexity?
- What is the space complexity?
- What happens if the constraints change?
- What alternative approaches exist?
- What trade-offs exist between them?

---

# Target

The ultimate goal is to become interview-ready for senior software
engineering roles at top-tier technology companies.

Preparation will prioritize:

```text
Deep Understanding
        ↓
Problem Solving
        ↓
Pattern Recognition
        ↓
Communication
        ↓
Interview Performance
```

The repository will evolve as the preparation progresses.
```

After replacing the README, run:

```bash
git add README.md
git commit -m "docs: update DSA preparation progress"
git push
```

That gives us a clean **single source of truth** for your preparation progress going forward.