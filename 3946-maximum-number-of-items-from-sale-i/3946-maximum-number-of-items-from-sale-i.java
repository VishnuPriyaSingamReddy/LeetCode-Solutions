class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        int[] value = new int[n];

        // Calculate free items received from buying each item
        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);

            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    count++;
                }
            }

            value[i] = count + 1;
        }

        // 0/1 Knapsack
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b],
                        dp[b - price] + value[i]);
            }
        }

        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            int extra = (budget - b) / minPrice;
            ans = Math.max(ans, dp[b] + extra);
        }

        return ans;
    }
}