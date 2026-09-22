class Solution {

    int n, k;
    int[] nums;
    Node[] tree;

    static class Node {
        int product;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            nums[index] = value;
            update(1, 0, n - 1, index);

            // We need prefix products of nums[start...n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.cnt[x];
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = makeNode(nums[l]);
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Node representing one element
    Node makeNode(int value) {

        Node res = new Node(k);

        int rem = value % k;

        res.product = rem;

        // One prefix: the element itself
        res.cnt[rem] = 1;

        return res;
    }

    // Merge two consecutive segments A + B
    Node merge(Node A, Node B) {

        Node C = new Node(k);

        // Product of the whole segment
        C.product = (int)((long) A.product * B.product % k);

        // Prefixes completely inside A
        for (int r = 0; r < k; r++) {
            C.cnt[r] += A.cnt[r];
        }

        /*
         * Prefixes that use all of A
         * and then some prefix of B.
         *
         * product = product(A) * prefix(B)
         */
        for (int r = 0; r < k; r++) {

            if (B.cnt[r] == 0)
                continue;

            int newRem = (int)((long) A.product * r % k);

            C.cnt[newRem] += B.cnt[r];
        }

        return C;
    }

    // Point update
    void update(int node, int l, int r, int index) {

        if (l == r) {
            tree[node] = makeNode(nums[index]);
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Range query
    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}