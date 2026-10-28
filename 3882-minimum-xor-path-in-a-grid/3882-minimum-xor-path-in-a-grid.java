class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m * n][1024];

        dp[0][grid[0][0]] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int idx = i * n + j;
                int val = grid[i][j];

                for (int x = 0; x < 1024; x++) {
                    if (i > 0 && dp[idx - n][x] == 1)
                        dp[idx][x ^ val] = 1;

                    if (j > 0 && dp[idx - 1][x] == 1)
                        dp[idx][x ^ val] = 1;
                }
            }
        }

        int last = m * n - 1;

        for (int x = 0; x < 1024; x++) {
            if (dp[last][x] == 1) return x;
        }

        return -1;
    }
}