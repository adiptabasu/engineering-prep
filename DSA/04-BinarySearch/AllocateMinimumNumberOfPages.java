/**
 * GeeksforGeeks: Allocate Minimum Pages
 * https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
 *
 * Problem:
 * Allocate books in their original order to k students. Each student must
 * receive at least one book and each student's books must be contiguous.
 * Minimize the largest page total assigned to any one student.
 *
 * Approach:
 * Binary-search the allowed maximum pages. For one candidate, greedily assign
 * each student the longest next contiguous group that stays within the limit.
 * The check is feasible when this uses at most k students.
 *
 * Important condition:
 * The greedy check computes the fewest students needed for a page limit. If
 * that count is less than k, a group can be split into smaller contiguous
 * groups until exactly k students each have at least one book. Therefore the
 * feasibility check uses <= k, not == k.
 *
 * Bounds:
 * The largest single book is the minimum possible limit; the sum of all pages
 * is a guaranteed feasible upper bound. long is used because the total can
 * exceed the int range even when the final answer fits in int.
 *
 * Complexity:
 * O(n log S) time, where S is the sum of pages; O(1) auxiliary space.
 */
public class AllocateMinimumNumberOfPages {

    public int findPages(int[] arr, int k) {
        int numberOfBooks = arr.length;
        if (k > numberOfBooks) {
            return -1;
        }

        long low = 0;
        long high = 0;
        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }

        long minOfMax = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (isPossibleWithMaxPageCount(arr, k, mid)) {
                // mid works. Save it, then search left for a smaller limit.
                minOfMax = mid;
                high = mid - 1;
            } else {
                // mid needs too many students. A larger limit can fit more
                // books per student and may reduce the number of groups.
                low = mid + 1;
            }
        }

        // The problem guarantees that the minimum answer fits in an int.
        return (int) minOfMax;
    }

    /**
     * Returns whether maxPages is enough to allocate all books to k students.
     *
     * Greedy section logic: keep adding consecutive books to the current
     * student until the next book would exceed maxPages. Then start the next
     * student with that book. This makes each group as long as possible, so it
     * uses the fewest students for this page limit.
     *
     * We accept studentsNeeded <= k, not only studentsNeeded == k. If greedy
     * needs fewer than k groups, those groups can be split while keeping the
     * books contiguous; because k <= arr.length, we can split until each of
     * the k students has at least one book. If greedy needs more than k groups,
     * the candidate limit is too small.
     */
    private boolean isPossibleWithMaxPageCount(
            int[] arr, int numberOfStudents, long maxPages) {
        int studentsAllocatedTo = 1;
        long currentMaxPages = 0;

        for (int pages : arr) {
            if (currentMaxPages + pages <= maxPages) {
                currentMaxPages += pages;
            } else {
                // The next contiguous book would exceed the current student's
                // limit, so start a new student's allocation here.
                studentsAllocatedTo++;
                currentMaxPages = pages;

                // No later book can reduce the number already required.
                if (studentsAllocatedTo > numberOfStudents) {
                    return false;
                }
            }
        }

        return studentsAllocatedTo <= numberOfStudents;
    }
}
