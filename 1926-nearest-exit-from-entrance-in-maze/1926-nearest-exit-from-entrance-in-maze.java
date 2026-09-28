import java.util.*;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {

        int n = maze.length;
        int m = maze[0].length;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{entrance[0], entrance[1], 0});

        // Mark entrance visited
        maze[entrance[0]][entrance[1]] = '+';

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int steps = cur[2];

            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                // Outside maze
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                // Wall or already visited
                if (maze[nr][nc] != '.') {
                    continue;
                }

                int newSteps = steps + 1;

                // Mark visited
                maze[nr][nc] = '+';

                // Check whether this is an exit
                if (nr == 0 || nr == n - 1 ||
                    nc == 0 || nc == m - 1) {
                    return newSteps;
                }

                q.offer(new int[]{nr, nc, newSteps});
            }
        }

        return -1;
    }
}