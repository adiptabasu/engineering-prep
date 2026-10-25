# Top K selection with a heap

## When this pattern helps

If there are m distinct candidates and only the best k are needed, a heap can avoid sorting every candidate. First compute the frequency or score for each candidate. Then keep a min-heap of the best k seen so far, ordered by the score that defines quality.

    for each candidate with score:
        add candidate to heap
        if heap size > k:
            remove the candidate with the smallest score

After processing all candidates, the heap contains the k largest scores. Building frequencies takes expected O(n). The heap work is O(m log k), for total O(n + m log k), with O(m + k) space including the frequency map.

## Correctness idea

Whenever the heap exceeds k, its smallest member cannot belong to the best k among everything seen so far: there are already k candidates at least as strong. Removing it preserves the top-k candidates for the processed prefix.

## Choices and pitfalls

- Keep a min-heap so the weakest retained candidate is removed quickly.
- Compare by the actual criterion, such as frequency, not by an unrelated property.
- Consider ties. If any valid top-k answer is accepted, tied elements may appear in different orders; otherwise define a tie-break rule.
- If k is close to m, full sorting may be simpler. If scores are bounded integers, bucket-based approaches may be useful.

## Practice link

[Top K Frequent Elements](../TopKFrequent.java)
