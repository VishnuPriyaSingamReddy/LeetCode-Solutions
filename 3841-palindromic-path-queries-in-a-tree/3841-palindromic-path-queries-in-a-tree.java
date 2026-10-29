import java.util.*;

class Solution {
    int[][] up;
    int[] depth, tin, tout;
    int timer, LOG;
    List<Integer>[] adj;
    Fenwick bit;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void add(int i, int val) {
            for (; i <= n; i += i & -i)
                tree[i] ^= val;
        }

        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i)
                res ^= tree[i];
            return res;
        }

        void rangeUpdate(int l, int r, int val) {
            add(l + 1, val);
            if (r + 2 <= n)
                add(r + 2, val);
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges,
                                         String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        buildTree(n);

        bit = new Fenwick(n);
        char[] chars = s.toCharArray();

        for (int u = 0; u < n; u++) {
            bit.rangeUpdate(tin[u], tout[u],
                            1 << (chars[u] - 'a'));
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split("\\s+");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                bit.rangeUpdate(tin[u], tout[u], delta);
                chars[u] = c;
            } else {
                int v = Integer.parseInt(p[2]);
                int lca = getLCA(u, v);

                int mask = bit.query(tin[u] + 1)
                         ^ bit.query(tin[v] + 1)
                         ^ (1 << (chars[lca] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    void buildTree(int n) {
        int[] parent = new int[n];
        int[] iter = new int[n];
        int[] stack = new int[n];

        Arrays.fill(parent, -1);

        int top = 0;
        stack[0] = 0;
        parent[0] = 0;
        timer = 0;

        tin[0] = timer++;

        for (int j = 0; j < LOG; j++)
            up[j][0] = 0;

        while (top >= 0) {
            int u = stack[top];

            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                for (int j = 1; j < LOG; j++)
                    up[j][v] = up[j - 1][up[j - 1][v]];

                tin[v] = timer++;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }
    }

    int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0)
                u = up[j][u];
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}