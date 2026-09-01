class Solution {
    public int smallestDivisor(int[] nums, int threshold) {

        int start = 1;
        int end = max(nums);

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (shold(mid, nums, threshold) <= threshold) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    static int shold(int mid, int[] nums, int threshold) {

        int sum = 0;

        for (int num : nums) {
            sum += (num + mid - 1) / mid;

            // No need to calculate further
            if (sum > threshold) {
                return sum;
            }
        }

        return sum;
    }

    static int max(int[] nums) {

        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        return max;
    }
}