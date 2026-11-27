# LeetCode Solution Workflow

Use this workflow whenever the learner asks to save a completed LeetCode or GeeksforGeeks solution.

## 1. Guide the learning session

- Let the learner choose the problem and make an independent attempt first.
- Use questions, small examples, and graduated hints to help them reason. Do not reveal a full solution before they settle on an approach.
- Treat a learner-reported accepted submission as useful evidence, but not proof of independent mastery or long-term retention.

## 2. Prepare a detailed, self-contained Java solution

Keep `.java` files thorough so the learner can understand each solution at a glance later:

- Use the correct topic folder and follow neighboring Java file and class naming conventions.
- Put the problem number and URL near the top of the file.
- Include a clear problem summary, approach, key insight or invariant, why the approach works, time and space complexity, and relevant pitfalls or edge cases.
- Add comments explaining non-obvious reasoning; avoid narrating trivial lines.
- Keep the change focused on this solution and its topic journal. Do not modify unrelated solutions or manually update `README.md`, `DSA/README.md`, `DSA/LeetCode.md`, or `<topic>/README.md` per problem.
- Skip creating a separate `notes/*.md` file for a single problem unless the learner asks for one or it covers a reusable cross-problem pattern.
- **Save tokens on review:** Once the learner asks to save, write the `.java` file and topic journal entry directly to disk and reply with a **≤5-line summary** so the learner can review the diff in their editor instead of printing the full file in chat first.

## 3. Record learning evidence (newest-first, single entry)

For every submitted solution, add **one** concise dated row/entry at the **top (newest-first)** of that topic's `LEARNING-JOURNAL.md` (for example, `DSA/04-BinarySearch/LEARNING-JOURNAL.md`). Do not duplicate the same problem in both a narrative section and a second bottom table. Record:

- date, problem link, and link to the repository `.java` file;
- the main approach the learner proposed and any guidance/corrections needed;
- whether the learner reports the submission was accepted (`yes` / `no` / `not stated`); and
- one useful point to revisit independently.

Keep the entry factual and distinguish learner reports from observed understanding. Do not claim mastery from an accepted submission or committed file.

## 4. Commit and push commands

- Do not create the solution file or journal entry until the learner is happy with the approach and asks to save it.
- Do not run `git commit` or `git push` unless the learner explicitly asks you to run them. By default, output ready-to-run `git add`, `git commit`, and `git push` commands using this subject format:

  `dsa(<topic>): add <problem name in lowercase>`

  with a detailed body summarizing the approach, key invariant/complexity, and checks run (or none run).
- Examples:
  - `dsa(stack-queue): add next greater element ii`
  - `dsa(linked-lists): add reverse linked list`
- If explicitly authorized to commit and push, inspect branch, upstream, status, and staged files first, then commit, push, and verify working-tree status.
