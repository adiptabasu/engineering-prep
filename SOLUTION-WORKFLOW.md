# LeetCode Solution Workflow

Use this workflow whenever the learner asks to save a completed LeetCode solution.

## 1. Guide the learning session

- Let the learner choose the problem and make an independent attempt first.
- Use questions, small examples, and graduated hints to help them reason. Do not reveal a full solution before they settle on an approach.
- Treat a learner-reported accepted submission as useful evidence, but not proof of independent mastery or long-term retention.

## 2. Prepare one reviewable solution

- Use the correct topic folder and follow the neighboring Java file and class naming conventions.
- Put the problem number and URL near the top of the file.
- Include a concise problem summary, approach, key insight or invariant, why the approach works, time and space complexity, and relevant pitfalls or edge cases.
- Add comments that explain non-obvious reasoning; avoid narrating every line.
- Keep the change focused on this solution and its learning record. Do not modify other solutions or make unrelated cleanup changes.
- Show the learner the complete proposed file or diff and the proposed journal entry before committing.

## 3. Record the learning evidence

For every submitted solution, add a concise dated entry to that topic's `LEARNING-JOURNAL.md` (for example, `DSA/03-StackQueue/LEARNING-JOURNAL.md`). The root `LEARNING-JOURNAL.md` is an index; add a link there only when a topic journal is created. Each topic entry records:

- the problem and link to its repository solution;
- the main approach the learner proposed;
- what guidance or corrections were needed;
- whether the learner reports the LeetCode submission was accepted; and
- one useful point to revisit, when applicable.

Keep the entry factual and distinguish learner reports from observations in the discussion. Do not claim mastery based only on an accepted submission or a committed file. Show the entry for learner review with the solution. Update that topic's problem implementation history and counts when needed.

## 4. Approval, commit, and push

- Do not create the solution file or journal entry until the learner is happy with the approach and asks to save it. Show the proposed result before committing.
- The learner's approval of the prepared solution and journal entry authorizes adding those reviewed changes, committing them, and pushing them to the verified upstream for the current branch. This authorization covers that solution workflow only; keep unrelated changes out.
- Before committing, inspect the branch, upstream, status, and exact staged files. If the destination is missing or ambiguous, ask before pushing.
- Use this commit-message format:

  `dsa(<topic>): add <problem name in lowercase>`

  Examples:

  - `dsa(stack-queue): add next greater element ii`
  - `dsa(linked-lists): add reverse linked list`

- After committing, push to the inspected upstream and verify the resulting commit and working-tree status.
