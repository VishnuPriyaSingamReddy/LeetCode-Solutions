class Solution {
    int n, m;
    char[][] grid;
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;

        // Path length must be even
        if ((n + m - 1) % 2 != 0) {
            return false;
        }

        // Must start with '('
        if (grid[0][0] == ')') {
            return false;
        }

        // Must end with ')'
        if (grid[n - 1][m - 1] == '(') {
            return false;
        }

        dp = new Boolean[n][m][n + m];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {

        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid parentheses prefix
        if (balance < 0) {
            return false;
        }

        // Reached destination
        if (r == n - 1 && c == m - 1) {
            return balance == 0;
        }

        // Already calculated
        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        boolean ans = false;

        // Move down
        if (r + 1 < n) {
            ans = dfs(r + 1, c, balance);
        }

        // Move right
        if (!ans && c + 1 < m) {
            ans = dfs(r, c + 1, balance);
        }

        dp[r][c][balance] = ans;

        return ans;
    }
}