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

## Why each state is sufficient

For stock profit, once the current sell day is fixed, the best earlier buy is the minimum price seen before that day. No other earlier price can produce a larger profit for this sell day. Scanning every sell day while maintaining that minimum therefore considers the best legal transaction for each possible sell.

For Kadane's recurrence, any non-empty subarray ending at the current value has only two possibilities: it begins here, or it extends a subarray that ended at the previous value. The best of those is the best ending here. Taking the maximum of all such endings gives the best subarray anywhere.

For products, keeping only the maximum ending product is not enough. A negative value reverses ordering: a very negative product can become the largest after multiplication by another negative. The minimum ending product must be retained too. Every product ending at the current value either starts at that value or extends one of the previous maximum or minimum products.

For example, with [-2, 3, -4], the ending maximum/minimum pairs are (-2, -2), then (3, -6), then (24, -12). The negative -6 becomes the maximum when multiplied by -4. This is why both states are necessary.
## Practice links

[Best Time to Buy and Sell Stock](../BestTimeToBuyAndSellStock.java) · [Maximum Subarray](../MaximumSubarray.java) · [Maximum Product Subarray](../MaximumProductSubarray.java)
