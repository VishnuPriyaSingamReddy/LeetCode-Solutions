import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int best = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int r = 0; r < d; r++) {
                        int left = r;
                        int right = len - d + r;

                        while (left < right) {
                            if (s.charAt(i + left) != s.charAt(i + right)) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][i + len - 1] = best;
            }
        }

        int INF = 1000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int groups = 1; groups <= k; groups++) {
            for (int i = groups * 2; i <= n; i++) {
                for (int j = (groups - 1) * 2; j <= i - 2; j++) {
                    if (dp[groups - 1][j] == INF) continue;

                    dp[groups][i] = Math.min(
                        dp[groups][i],
                        dp[groups - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}