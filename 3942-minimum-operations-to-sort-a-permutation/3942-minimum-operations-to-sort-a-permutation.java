
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        if (n == 1) return 0;

        int pos0 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
                break;
            }
        }

        // Case 1: Rotate the original array.
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                ok = false;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (ok) {
            ans = Math.min(pos0, n - pos0 + 2);
        }

        // Case 2: Reverse the array, then rotate.
        int k = (n - 1 - pos0) % n;
        ok = true;

        for (int i = 0; i < n; i++) {
            int idx = (k + i) % n;
            if (nums[n - 1 - idx] != i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
