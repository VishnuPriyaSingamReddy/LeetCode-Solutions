import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int dp1 = 0, dp2 = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int x : nums) {
            int a = map.getOrDefault(x - 1, 0) + 1;
            int b = map.getOrDefault(x, 0) + 1;

            map.put(x, Math.max(map.getOrDefault(x, 0), a));
            map.put(x + 1, Math.max(map.getOrDefault(x + 1, 0), b));
        }

        int ans = 0;
        for (int v : map.values()) {
            ans = Math.max(ans, v);
        }

        return ans;
    }
}