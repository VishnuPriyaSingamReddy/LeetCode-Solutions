import java.util.*;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{entrance[0], entrance[1], 0});

        maze[entrance[0]][entrance[1]] = '+';

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int d = cur[2];

            // Check whether current cell is an exit
            if ((r == 0 || r == n - 1 || c == 0 || c == m - 1)
                    && !(r == entrance[0] && c == entrance[1])) {
                return d;
            }

            for (int[] x : dir) {
                int nr = r + x[0];
                int nc = c + x[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m
                        && maze[nr][nc] == '.') {

                    maze[nr][nc] = '+';
                    q.offer(new int[]{nr, nc, d + 1});
                }
            }
        }

        return -1;
    }
}