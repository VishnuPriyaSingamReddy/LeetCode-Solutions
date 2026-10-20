import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                // Check whether this block is a cyclic
                // rotation of the sorted block.
                boolean found = false;

                for (int shift = 0; shift < k && !found; shift++) {
                    boolean match = true;

                    for (int j = 0; j < k; j++) {
                        if (nums[start + j] !=
                            sorted[start + (j + shift) % k]) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        found = true;
                    }
                }

                if (!found) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}