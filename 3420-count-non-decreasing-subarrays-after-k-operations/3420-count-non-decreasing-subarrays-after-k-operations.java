
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        long ans = 0;
        long cost = 0;

        // Store {value, count} in non-increasing order
        Deque<int[]> dq = new ArrayDeque<>();

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Merge smaller values into the current value
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] temp = dq.pollLast();

                count += temp[1];

                cost += (long) (num - temp[0]) * temp[1];
            }

            dq.offerLast(new int[]{num, count});

            // Reduce window until cost becomes <= k
            while (cost > k) {
                int[] temp = dq.pollFirst();

                int value = temp[0];
                int cnt = temp[1];

                cost -= (long) (value - nums[j]);
                j--;

                if (cnt > 1) {
                    dq.offerFirst(new int[]{value, cnt - 1});
                }
            }

            // Count all valid subarrays ending at j
            ans += j - i + 1;
        }

        return ans;
    }
}
