# Stack simulation and parsing

These problems are easier when you first say what the stack represents. A stack is not just a container: it can represent unfinished work, surviving items, or context that must be restored after a nested part finishes.

## Matching nested brackets

When you read an opening bracket, remember it by pushing it. When you read a closing bracket, it must match the most recent unmatched opener at the top. Pop the opener after a match. If there is no opener or it has the wrong type, the string is invalid. At the end, no unmatched openers may remain.

For `([])`:

| Read | Stack after reading | What happened |
|---|---|---|
| `(` | `(` | Remember opener |
| `[` | `(`, `[` | Remember nested opener |
| `]` | `(` | Matches and removes `[` |
| `)` | empty | Matches and removes `(` |

The order matters. A counter could tell how many openers there are, but it cannot detect a mismatch such as `([)]`.

## Cancellation and collisions

In a cancellation problem, treat the stack as the items that have survived so far. For each new item, compare it with the top. If the problem says they cancel or collide, remove the top and keep checking: the new item may interact with another survivor. Add the new item only if it survives.

For asteroid collisions, direction matters. An incoming left-moving asteroid can collide with a right-moving asteroid at the top. For `[5, 10, -5]`, `-5` meets `10` and is destroyed, so the survivors are `[5, 10]`. The `5` and `10` never meet because they move in the same direction.

For string reductions, the rule is different but the stack role is similar: the top is the most recent character that might cancel with the next character. For backspaces, processing `ab#c` left to right gives `ab`, then `a` after `#`, then `ac` after `c`.

## Evaluating postfix expressions

In a postfix (Reverse Polish) expression, an operator follows its operands. Read tokens from left to right. Push numbers. When an operator appears, pop the right operand first and then the left operand, calculate, and push the result.

For `2 1 + 3 *`:

| Token | Action | Stack after action |
|---|---|---|
| `2` | Push 2 | `2` |
| `1` | Push 1 | `2, 1` |
| `+` | Pop 1 and 2; push `2 + 1` | `3` |
| `3` | Push 3 | `3, 3` |
| `*` | Pop 3 and 3; push `3 * 3` | `9` |

Operand order is important for subtraction and division. For `a b -`, the result is `a - b`, although `b` is popped first. A valid expression leaves one result at the end.

## Simplifying an absolute path

Split the path into directory components and process them in order. Ignore empty components and `.` (the current directory). For `..` (the parent directory), remove the most recent ordinary directory if one exists. Push normal directory names.

For `/a/./b/../../c/`:

```text
start: []
a:     [a]
.:     [a]       ignored
b:     [a, b]
..:    [a]       remove b
..:    []        remove a
c:     [c]
result: /c
```

An absolute path cannot move above its root, so `..` on an empty stack leaves it empty.

## Decoding nested repetition

For an encoding such as `3[a2[c]]`, a closing bracket means the current inner part is complete. Before entering a nested part, save the repetition count and the string built so far. When the closing bracket arrives, repeat the inner part and attach it to the saved prefix.

The stack preserves context while the parser goes deeper. A frame needs both the count and the prefix; either one alone is not enough to reconstruct the outer string.

## Before coding

Ask:

1. What does one stack entry represent?
2. Which new item can interact with the top?
3. Can one item cause a chain of removals?
4. What context must be saved before parsing a nested part?
5. What should remain on the stack at the end?

## Practice links

[Valid Parentheses](../ValidParentheses.java) · [Asteroid Collision](../AsteroidCollision.java) · [Evaluate Reverse Polish Notation](../EvaluateReversePolishNotation.java) · [Simplify Path](../SimplifyPath.java) · [Decode String](../DecodeString.java) · [Backspace String Compare](../BackspaceStringCompare.java)
