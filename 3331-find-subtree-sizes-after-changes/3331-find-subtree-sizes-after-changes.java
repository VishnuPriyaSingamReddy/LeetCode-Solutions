import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        Arrays.fill(newParent, -1);

        Deque<Integer>[] path = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            path[i] = new ArrayDeque<>();
        }

        ArrayList<Integer> order = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (v < 0) {
                int node = ~v;
                path[s.charAt(node) - 'a'].pop();
                continue;
            }

            int c = s.charAt(v) - 'a';

            if (!path[c].isEmpty()) {
                newParent[v] = path[c].peek();
            } else {
                newParent[v] = parent[v];
            }

            path[c].push(v);
            order.add(v);

            stack.push(~v);

            for (int i = adj[v].size() - 1; i >= 0; i--) {
                stack.push(adj[v].get(i));
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {
            int node = order.get(i);
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}