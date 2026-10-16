class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long mod = 1_000_000_007L;

        long[] sum = new long[4 * n];
        long[] squares = new long[4 * n];
        long[] lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);

            update(1, 0, n - 1, prev + 1, i, 1,
                   sum, squares, lazy, mod);

            ans = (ans + squares[1]) % mod;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long val,
                       long[] sum, long[] squares,
                       long[] lazy, long mod) {
        long len = r - l + 1L;

        squares[node] = (squares[node]
                + 2L * val % mod * sum[node] % mod
                + len % mod * val % mod * val % mod) % mod;

        sum[node] = (sum[node] + len * val) % mod;
        lazy[node] = (lazy[node] + val) % mod;
    }

    private void push(int node, int l, int r,
                      long[] sum, long[] squares,
                      long[] lazy, long mod) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = l + (r - l) / 2;
        long val = lazy[node];

        apply(node * 2, l, mid, val, sum, squares, lazy, mod);
        apply(node * 2 + 1, mid + 1, r, val,
              sum, squares, lazy, mod);

        lazy[node] = 0;
    }

    private void update(int node, int l, int r, int ql, int qr,
                        long val, long[] sum, long[] squares,
                        long[] lazy, long mod) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, val, sum, squares, lazy, mod);
            return;
        }

        push(node, l, r, sum, squares, lazy, mod);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr, val,
               sum, squares, lazy, mod);
        update(node * 2 + 1, mid + 1, r, ql, qr, val,
               sum, squares, lazy, mod);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % mod;
        squares[node] =
                (squares[node * 2] + squares[node * 2 + 1]) % mod;
    }
}