
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int MOD = 1000000007;

        long[][] dp = new long[m][n];
        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long ways = dp[i][j];

                if (i > 0)
                    ways = (ways + down[i][j]) % MOD;

                if (j > 0)
                    ways = (ways + right[i][j]) % MOD;

                if (i == 0 && j == 0)
                    ways = 1;

                if (grid[i][j] == 1) {
                    if (i + 1 < m)
                        down[i + 1][j] =
                            (down[i + 1][j] + right[i][j]) % MOD;

                    if (j + 1 < n)
                        right[i][j + 1] =
                            (right[i][j + 1] + down[i][j]) % MOD;
                } else {
                    if (j + 1 < n)
                        right[i][j + 1] =
                            (right[i][j + 1] + ways) % MOD;

                    if (i + 1 < m)
                        down[i + 1][j] =
                            (down[i + 1][j] + ways) % MOD;
                }
            }
        }

        return (int) waysAtEnd(dp, right, down, m, n, MOD);
    }

    private long waysAtEnd(long[][] dp, long[][] right,
                           long[][] down, int m, int n, int MOD) {
        return (dp[m - 1][n - 1] +
                right[m - 1][n - 1] +
                down[m - 1][n - 1]) % MOD;
    }
}
