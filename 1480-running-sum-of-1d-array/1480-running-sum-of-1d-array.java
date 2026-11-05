class Solution {
    public int[] runningSum(int[] nums) {
        int pref[]=new int[nums.length];
        pref[0]=nums[0];
        for(int i=1;i<nums.length;i++)
            pref[i]=pref[i-1]+nums[i];
        return pref;
    }
}