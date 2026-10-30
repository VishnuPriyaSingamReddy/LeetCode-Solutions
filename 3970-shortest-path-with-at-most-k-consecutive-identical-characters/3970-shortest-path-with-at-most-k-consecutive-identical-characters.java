
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges)
            adj[e[0]].add(new int[]{e[1], e[2]});

        long[][] dist = new long[n][k + 1];

        for (long[] row : dist)
            Arrays.fill(row, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] cur = pq.poll();

            long d = cur[0];
            int u = (int) cur[1];
            int count = (int) cur[2];

            if (d != dist[u][count])
                continue;

            if (u == n - 1)
                return (int) d;

            for (int[] e : adj[u]) {

                int v = e[0];
                int w = e[1];

                int newCount = (labels.charAt(u) == labels.charAt(v))
                               ? count + 1 : 1;

                if (newCount > k)
                    continue;

                long newDist = d + w;

                if (newDist < dist[v][newCount]) {

                    dist[v][newCount] = newDist;

                    pq.offer(new long[]{
                        newDist, v, newCount
                    });
                }
            }
        }

        return -1;
    }
}
