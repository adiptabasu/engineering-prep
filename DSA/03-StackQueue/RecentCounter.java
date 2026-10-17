import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 933: Number of Recent Calls
 *
 * Problem URL:
 * https://leetcode.com/problems/number-of-recent-calls/
 *
 * Approach: Queue + Sliding Time Window
 *
 * Maintain a queue of request timestamps in increasing order.
 *
 * For every ping(t):
 * 1. Add the current timestamp to the queue.
 * 2. Remove timestamps that fall outside the window [t - 3000, t].
 * 3. Return the number of remaining timestamps.
 *
 * Since timestamps arrive in strictly increasing order, the oldest
 * timestamps are always at the front of the queue.
 *
 * Complexity:
 * - ping(): O(1) amortized
 * - Worst-case time for one ping(): O(n)
 * - Space: O(n), where n is the maximum number of stored timestamps
 */
public class RecentCounter {

    private final Queue<Integer> requestQueue;

    /**
     * Initializes an empty request counter.
     */
    public RecentCounter() {
        requestQueue = new ArrayDeque<>();
    }

    /**
     * Records a request at timestamp t and returns the number
     * of requests in the inclusive time window [t - 3000, t].
     *
     * @param t timestamp of the current request
     * @return number of requests in the last 3000 milliseconds,
     *         including the current request
     *
     * Time Complexity: O(1) amortized
     */
    public int ping(int t) {

        // Add the current request to the rear of the queue.
        requestQueue.offer(t);

        // Remove requests older than the start of the valid window.
        // The boundary is inclusive, so timestamps equal to t - 3000
        // must remain in the queue.
        while (!requestQueue.isEmpty()
                && requestQueue.peek() < t - 3000) {

            requestQueue.poll();
        }

        // Every remaining timestamp belongs to the valid time window.
        return requestQueue.size();
    }

    /**
     * Local test cases.
     *
     * LeetCode invokes the constructor and ping() directly.
     * This main() method is provided for local testing.
     */
    public static void main(String[] args) {

        RecentCounter counter = new RecentCounter();

        // Test 1: First request.
        check(counter.ping(1), 1, "ping(1)");

        // Test 2: Both requests are within the time window.
        check(counter.ping(100), 2, "ping(100)");

        // Test 3: Timestamp 1 is exactly at the lower boundary.
        // Valid window: [1, 3001].
        check(counter.ping(3001), 3, "ping(3001)");

        // Test 4: Timestamp 1 is now outside the window.
        // Valid window: [2, 3002].
        check(counter.ping(3002), 3, "ping(3002)");

        // Test 5: Multiple old timestamps must be removed.
        // Valid window: [1001, 4001].
        check(counter.ping(4001), 2, "ping(4001)");

        // Test 6: All previous timestamps are outside the window.
        // Valid window: [5001, 8001].
        check(counter.ping(8001), 1, "ping(8001)");

        System.out.println("All test cases passed!");
    }

    /**
     * Verifies the actual result against the expected result.
     */
    private static void check(int actual, int expected, String description) {
        if (actual != expected) {
            throw new AssertionError(
                description + ": expected " + expected + ", got " + actual
            );
        }
    }
}
