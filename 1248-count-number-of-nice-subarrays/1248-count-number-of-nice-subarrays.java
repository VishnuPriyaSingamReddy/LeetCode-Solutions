class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0, l = 0, ans = 0;

        for (int r = 0; r < nums.length; r++) {
            if (nums[r] % 2 != 0) {
                count++;
            }

            while (count > k) {
                if (nums[l] % 2 != 0) {
                    count--;
                }
                l++;
            }

            if (count == k) {
                int temp = l;
                while (temp <= r && nums[temp] % 2 == 0) {
                    temp++;
                }
                ans += temp - l + 1;
            }
        }

        return ans;
    }
}