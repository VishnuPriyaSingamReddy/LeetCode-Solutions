class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer,Integer> mp=new HashMap<>();
        mp.put(0,1);
        int prefix=0,result=0;
        for(int i:nums){
            prefix+=i;
            result+=mp.getOrDefault(prefix-goal,0);
            mp.put(prefix,mp.getOrDefault(prefix,0)+1);
        }
        return result;
    }
}