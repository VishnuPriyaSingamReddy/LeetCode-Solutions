class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer,Integer> mp=new HashMap<>();
        mp.put(0,1);
        int ans=0,pref=0;
        for(int i:nums){
            pref+=i%2;
            ans+=mp.getOrDefault(pref-k,0);
            mp.put(pref,mp.getOrDefault(pref,0)+1);
        }
        return ans;
    }
}