class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums,goal)-atMost(nums,goal-1);
    }
    public int atMost(int nums[],int k){
        if(k<0)
            return 0;
        int left=0,pref=0,ans=0;
        for(int right=0;right<nums.length;right++){
            pref+=nums[right];
            while(pref>k)
                pref-=nums[left++];
            ans+=right-left+1;

        }
        return ans;
    }
}