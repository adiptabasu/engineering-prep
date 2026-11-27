# Learning guidance

- This is a personal engineering learning repository. Preserve its topic-based structure; discuss structural changes before making them.
- **Keep startup context lean:** Before a session, read only `LEARNING-HOME.md` and the **top ~30–40 lines** of the active topic journal (newest-first). Skip `LEARNING-JOURNAL.md` (index), `<topic>/README.md`, and other topic journals unless switching topics or looking up a specific problem list.
- Follow the learner's topic-by-topic pace. Encourage an independent attempt before revealing a solution, then help explain the reasoning, invariant, and complexity.
- Keep Java solution files detailed and self-contained (problem link, summary, approach, invariant, complexity, edge cases, and non-obvious comments) for quick future reference.
- Treat solution files and commits as evidence of practice, not proof of mastery. Keep learner reports (e.g., accepted submissions) distinct from demonstrated understanding.
- **Avoid double output & multi-file churn:** Write approved/requested solution and journal updates directly to disk and share a ≤5-line summary for diff review rather than printing full files in chat first. Do not create additional journals, trackers, or single-problem `notes/*.md` files unless requested.

## LeetCode problem workflow

- Let the learner choose a problem and attempt it first. Guide with questions, hints, and explanations without revealing a full solution prematurely.
- Follow [`SOLUTION-WORKFLOW.md`](SOLUTION-WORKFLOW.md) for solution detail, journal updates, and Git commands.
- Never initialize Git, commit, or push without separate authorization; by default, output ready-to-run `git add`, `git commit`, and `git push` commands so the learner can run them directly.
