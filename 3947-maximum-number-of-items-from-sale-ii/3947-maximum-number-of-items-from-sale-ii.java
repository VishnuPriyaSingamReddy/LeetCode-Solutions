import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] count = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        // Count frequency of each factor.
        for (int[] item : items) {
            count[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count how many factors are divisible by each factor.
        int[] multiples = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                multiples[i] += count[j];
            }
        }

        // Sort items by price.
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remaining = budget;
        long ans = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Buying this item is not better than
            // buying two cheapest items.
            if (price > 2L * minPrice) {
                break;
            }

            // Exclude the item itself.
            int freeCopies = multiples[factor] - 1;

            // Buy as many bonus-producing copies as possible.
            long take = Math.min(
                (long) freeCopies,
                remaining / price
            );

            ans += 2L * take;
            remaining -= take * price;
        }

        // Spend the remaining budget on cheapest items.
        ans += remaining / minPrice;

        return (int) ans;
    }
}