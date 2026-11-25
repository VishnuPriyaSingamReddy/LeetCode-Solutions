
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int maxRank = 0;

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
            maxRank = Math.max(maxRank, nums[i]);
        }

        // Build Suffix Array using doubling + counting sort
        for (int len = 1; ; len <<= 1) {
            int maxKey = maxRank + 1;
            int[] temp = new int[n];
            int[] count = new int[maxKey + 1];

            countingSort(sa, temp, rank, len, maxKey, count);
            countingSort(sa, temp, rank, 0, maxKey, count);

            int[] newRank = new int[n];
            int classes = 0;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a2 = a + len < n ? rank[a + len] : -1;
                int b2 = b + len < n ? rank[b + len] : -1;

                if (rank[a] != rank[b] || a2 != b2) {
                    classes++;
                }

                newRank[b] = classes;
            }

            rank = newRank;
            maxRank = classes;

            if (classes == n - 1) break;
        }

        // Build inverse suffix array
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        // Kasai's algorithm to calculate LCP
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {
            int idx = sa[i];

            int best = 0;

            if (i > 0)
                best = Math.max(best, lcp[i]);

            if (i < n - 1)
                best = Math.max(best, lcp[i + 1]);

            int len = best + 1;

            if (len <= n - idx) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private void countingSort(int[] sa, int[] temp,
                              int[] rank, int offset,
                              int maxKey, int[] count) {

        java.util.Arrays.fill(count, 0);

        int n = sa.length;

        for (int idx : sa) {
            int key = idx + offset < n
                    ? rank[idx + offset] + 1 : 0;
            count[key]++;
        }

        int sum = 0;

        for (int i = 0; i <= maxKey; i++) {
            int c = count[i];
            count[i] = sum;
            sum += c;
        }

        for (int idx : sa) {
            int key = idx + offset < n
                    ? rank[idx + offset] + 1 : 0;
            temp[count[key]++] = idx;
        }

        System.arraycopy(temp, 0, sa, 0, n);
    }
}
