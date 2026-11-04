class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> mp=new HashMap<>();
        mp.put(0,1);
        int pref=0,ans=0;
        for(int i:nums){
            pref+=i;
            ans+=mp.getOrDefault(pref-k,0);
            mp.put(pref,mp.getOrDefault(pref,0)+1);
        }
        return ans;
    }
}