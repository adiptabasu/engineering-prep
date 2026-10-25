# Running state and subarray dynamic programming

## Keep only the past information that can affect the future

A linear scan can often replace repeated comparison with a small state summary. At each position, ask what information from the prefix is sufficient to decide the best answer that ends here or uses this position.

## Best Time to Buy and Sell Stock

Before processing a sell price, the minimum earlier price is enough to calculate the best profit ending today.

    minimumSoFar = first price
    bestProfit = 0
    for each later price:
        bestProfit = max(bestProfit, price - minimumSoFar)
        minimumSoFar = min(minimumSoFar, price)

The minimum must come from an earlier day. The order of updates preserves that condition. Time is O(n); extra space is O(1).

## Maximum Subarray: Kadane's recurrence

Let bestEndingHere be the best sum of a non-empty subarray that ends at the current position. Either start a new subarray here or extend the previous one.

    bestEndingHere = first value
    bestOverall = first value
    for each later value x:
        bestEndingHere = max(x, bestEndingHere + x)
        bestOverall = max(bestOverall, bestEndingHere)

Initialize from an actual value so all-negative arrays work. Time is O(n); extra space is O(1).

## Maximum Product Subarray

For products, keep both the largest and smallest product ending at the current position. Multiplying by a negative can swap their roles. Save the old values before updating.

    maximumEndingHere = first value
    minimumEndingHere = first value
    bestOverall = first value
    for each later value x:
        oldMaximum = maximumEndingHere
        oldMinimum = minimumEndingHere
        maximumEndingHere = max(x, x * oldMaximum, x * oldMinimum)
        minimumEndingHere = min(x, x * oldMaximum, x * oldMinimum)
        bestOverall = max(bestOverall, maximumEndingHere)

A zero naturally allows a new subarray to begin at the next position. Time is O(n); extra space is O(1).

## Practice links

[Best Time to Buy and Sell Stock](../BestTimeToBuyAndSellStock.java) · [Maximum Subarray](../MaximumSubarray.java) · [Maximum Product Subarray](../MaximumProductSubarray.java)
