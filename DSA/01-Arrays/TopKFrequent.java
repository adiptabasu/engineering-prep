import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Top K Frequent Elements
 *
 * LeetCode:
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Problem:
 * Given an integer array nums and an integer k, return the k most
 * frequent elements.
 *
 * The answer may be returned in any order.
 *
 * Example:
 *
 * Input:
 * nums = [1,1,1,2,2,3]
 * k = 2
 *
 * Output:
 * [1,2]
 *
 * ------------------------------------------------------------
 * Core Idea
 * ------------------------------------------------------------
 *
 * First calculate the frequency of every number:
 *
 *     number -> frequency
 *
 * Then select the K numbers with the highest frequencies.
 *
 * This can be solved using:
 *
 * 1. TreeMap
 * 2. Min-Heap / PriorityQueue
 *
 * ------------------------------------------------------------
 * Approach 1: TreeMap
 * ------------------------------------------------------------
 *
 * Build a frequency HashMap and then use a TreeMap with a custom
 * comparator to order entries by frequency in descending order.
 *
 * If two numbers have the same frequency, the number itself is
 * used as a tie-breaker.
 *
 * Once everything is sorted, take the first K elements.
 *
 * Time:
 *
 *     Frequency calculation -> O(n)
 *     TreeMap insertions     -> O(m log m)
 *
 *     Overall -> O(n + m log m)
 *
 * Space:
 *
 *     O(m)
 *
 * where:
 *
 *     n = total number of elements
 *     m = number of distinct elements
 *
 * ------------------------------------------------------------
 * Approach 2: Min-Heap
 * ------------------------------------------------------------
 *
 * We do not actually need to sort all M distinct elements if we
 * only want the top K.
 *
 * Maintain a MIN-HEAP containing at most K elements.
 *
 * For every frequency entry:
 *
 *     1. Add the entry to the heap.
 *     2. If heap size becomes greater than K:
 *            remove the minimum-frequency entry.
 *
 * The invariant is:
 *
 *     The heap always contains the K most frequent elements
 *     encountered so far.
 *
 * ------------------------------------------------------------
 * Why a MIN-Heap?
 * ------------------------------------------------------------
 *
 * We want the K largest frequencies.
 *
 * Once the heap contains K candidates, a new candidate can only
 * replace the weakest member of the current K.
 *
 * The weakest member is the one with the smallest frequency.
 *
 * Therefore, the smallest frequency must be immediately accessible.
 *
 * A min-heap gives us exactly that:
 *
 *     heap.peek() -> smallest frequency
 *
 * When the heap grows to K + 1:
 *
 *     heap.poll() -> removes the weakest candidate
 *
 * The heap therefore remains at size K.
 *
 * ------------------------------------------------------------
 * Complexity of Min-Heap Approach
 * ------------------------------------------------------------
 *
 * Frequency calculation:
 *
 *     O(n)
 *
 * There are M distinct elements and the heap never grows beyond
 * K + 1 elements.
 *
 * Each heap operation costs:
 *
 *     O(log K)
 *
 * Therefore:
 *
 *     Time  -> O(n + m log k)
 *     Space -> O(m + k)
 *
 * Since m <= n, this is often expressed as:
 *
 *     Time -> O(n log k)
 *
 * ------------------------------------------------------------
 * Why is this better than sorting everything?
 * ------------------------------------------------------------
 *
 * If:
 *
 *     m = 1,000,000
 *     k = 10
 *
 * sorting all distinct elements requires work proportional to:
 *
 *     O(m log m)
 *
 * while the heap only maintains approximately 10 elements:
 *
 *     O(m log k)
 *
 * This is the important Top-K pattern:
 *
 *     Need top K largest
 *         -> maintain a min-heap of size K
 *
 * ------------------------------------------------------------
 * Common Mistakes
 * ------------------------------------------------------------
 *
 * 1. Using a max-heap of all elements.
 *    This works, but performs unnecessary work when K is small.
 *
 * 2. Sorting all elements when only K are required.
 *
 * 3. Using a min-heap but allowing it to grow beyond K.
 *
 * 4. Forgetting that PriorityQueue is a min-heap by default.
 *
 * 5. Assuming the heap extraction order must match the required
 *    output order. LeetCode allows any order for this problem.
 *
 * ------------------------------------------------------------
 * Interview Follow-up
 * ------------------------------------------------------------
 *
 * Q: Why does a min-heap find the top K largest elements?
 *
 * A:
 * We keep exactly K candidates. When a new candidate arrives,
 * the smallest member of the current K is the only candidate
 * that can be removed. A min-heap keeps that weakest candidate
 * at the root, allowing efficient removal in O(log K).
 *
 * Q: What if the problem asks for the result in sorted order?
 *
 * A:
 * The heap gives the remaining K elements, but additional sorting
 * may be required depending on the requested ordering.
 */
public class TopKFrequent
{
    /**
     * Approach 1:
     * Frequency HashMap + TreeMap sorted by frequency.
     *
     * Time: O(n + m log m)
     * Space: O(m)
     */
    public static int[] topKFrequentTreeMap(int[] nums, int k)
    {
        Map<Integer, Integer> numberDistribution = new HashMap<>();

        // Calculate frequency of every number.
        for(int num : nums)
        {
            numberDistribution.put(
                    num,
                    numberDistribution.getOrDefault(num, 0) + 1
            );
        }

        /*
         * Sort entries by frequency in descending order.
         *
         * If two frequencies are equal, sort the numbers themselves
         * in descending order as a deterministic tie-breaker.
         */
        Map<Integer, Integer> sortedMap = new TreeMap<>(
                (k1, k2) ->
                {
                    int freqCompare =
                            numberDistribution.get(k2)
                                    .compareTo(numberDistribution.get(k1));

                    if(freqCompare == 0)
                    {
                        return k2.compareTo(k1);
                    }

                    return freqCompare;
                }
        );

        sortedMap.putAll(numberDistribution);

        int[] returnArr = new int[k];
        int count = 0;

        for(Map.Entry<Integer, Integer> data : sortedMap.entrySet())
        {
            returnArr[count++] = data.getKey();

            if(count == k)
            {
                break;
            }
        }

        return returnArr;
    }

    /**
     * Approach 2:
     * Frequency HashMap + Min-Heap of size K.
     *
     * Time: O(n + m log k)
     * Space: O(m + k)
     */
    public static int[] topKFrequent(int[] nums, int k)
    {
        Map<Integer, Integer> numberDistribution = new HashMap<>();

        // Calculate frequency of every number.
        for(int num : nums)
        {
            numberDistribution.put(
                    num,
                    numberDistribution.getOrDefault(num, 0) + 1
            );
        }

        /*
         * PriorityQueue is a min-heap by default.
         *
         * The entry with the lowest frequency stays at the top.
         */
        Queue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>(
                        (e1, e2) ->
                                e1.getValue().compareTo(e2.getValue())
                );

        /*
         * Maintain a heap containing only the K most frequent
         * elements encountered so far.
         */
        for(Map.Entry<Integer, Integer> data :
                numberDistribution.entrySet())
        {
            heap.offer(data);

            if(heap.size() > k)
            {
                // Remove the weakest candidate.
                heap.poll();
            }
        }

        /*
         * The min-heap returns elements from lowest frequency
         * to highest frequency.
         *
         * Fill the result backwards so that the output is ordered
         * from highest frequency to lowest frequency.
         *
         * The problem itself does not require this ordering;
         * this is simply a useful deterministic presentation.
         */
        int[] returnArr = new int[k];

        for(int i = k - 1; i >= 0; i--)
        {
            returnArr[i] = heap.poll().getKey();
        }

        return returnArr;
    }

    /**
     * Local tests.
     */
    public static void main(String[] args)
    {
        int[] nums1 = {1, 1, 1, 2, 2, 3};

        int[] nums2 = {1};

        int[] nums3 = {1, 2};

        int[] nums4 = {
                1, 1, 1,
                2, 2,
                3, 3, 3,
                4
        };

        System.out.println("TreeMap Approach:");
        System.out.println(
                java.util.Arrays.toString(
                        topKFrequentTreeMap(nums1, 2)
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        topKFrequentTreeMap(nums2, 1)
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        topKFrequentTreeMap(nums3, 2)
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        topKFrequentTreeMap(nums4, 2)
                )
        );

        System.out.println("\nMin-Heap Approach:");
        System.out.println(
                java.util.Arrays.toString(
                        topKFrequent(nums1, 2)
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        topKFrequent(nums2, 1)
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        topKFrequent(nums3, 2)
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        topKFrequent(nums4, 2)
                )
        );
    }
}
