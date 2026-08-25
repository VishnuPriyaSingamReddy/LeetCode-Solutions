class Solution {
    public int majorityElement(int[] nums) {
        //boyermoore voting algorithm
        int count=0;
        int candidate=0;
        for(int num:nums){
            if(count==0){
                candidate=num;
                count=1;
            }
            else if(candidate==num) count++;
            else count--;
        }
        return candidate;
        
    }
}