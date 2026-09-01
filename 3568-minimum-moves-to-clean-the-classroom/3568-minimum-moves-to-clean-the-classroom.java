import java.util.*;
class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }
        int sr = 0, sc = 0;
        int k = 0;
        // Find start and litter
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } 
                else if (ch == 'L') {
                    litterId[r][c] = k++;
                }
            }
        }
        int target = (1 << k) - 1;
        // visited[mask][energy][position]
        boolean[][][] visited =
                new boolean[1 << k][energy + 1][m * n];
        // Queue stores:
        // position, remaining energy, mask
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{
                sr * n + sc,
                energy,
                0
        });
        visited[0][energy][sr * n + sc] = true;
        int moves = 0;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            // Process one BFS level
            while (size-- > 0) {
                int[] cur = queue.poll();
                int pos = cur[0];
                int e = cur[1];
                int mask = cur[2];
                // All litter collected
                if (mask == target) {
                    return moves;
                }
                int r = pos / n;
                int c = pos % n;
                // No energy means cannot move
                if (e == 0) {
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    int ne = e - 1;
                    int nm = mask;
                    char cell = classroom[nr].charAt(nc);
                    // Reset energy
                    if (cell == 'R') {
                        ne = energy;
                    }
                    // Collect litter
                    if (litterId[nr][nc] != -1) {
                        nm |= (1 << litterId[nr][nc]);
                    }
                    int newPos = nr * n + nc;
                    if (!visited[nm][ne][newPos]) {
                        visited[nm][ne][newPos] = true;
                        queue.offer(new int[]{
                                newPos,
                                ne,
                                nm
                        });
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}