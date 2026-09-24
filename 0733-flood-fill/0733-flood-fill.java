class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        if (originalColor == color) {
            return image;
        }

        dfs(image, sr, sc, originalColor, color);

        return image;
    }

    private void dfs(int[][] image, int r, int c, int originalColor, int color) {
        // Out of bounds
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) {
            return;
        }

        // Different color
        if (image[r][c] != originalColor) {
            return;
        }

        // Change color
        image[r][c] = color;

        // Move up
        dfs(image, r - 1, c, originalColor, color);

        // Move down
        dfs(image, r + 1, c, originalColor, color);

        // Move left
        dfs(image, r, c - 1, originalColor, color);

        // Move right
        dfs(image, r, c + 1, originalColor, color);
    }
}