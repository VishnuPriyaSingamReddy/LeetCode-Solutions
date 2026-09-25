class Solution {
    private void dfs(int[][] grid, int r, int c) {

        if (r < 0 || c < 0 || 
            r >= grid.length || c >= grid[0].length ||
            grid[r][c] == 0) {
            return;
        }

        // Remove boundary-connected land
        grid[r][c] = 0;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        // Remove land connected to top and bottom
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1)
                dfs(grid, 0, j);

            if (grid[n - 1][j] == 1)
                dfs(grid, n - 1, j);
        }

        // Remove land connected to left and right
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1)
                dfs(grid, i, 0);

            if (grid[i][m - 1] == 1)
                dfs(grid, i, m - 1);
        }

        // Count remaining land
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}