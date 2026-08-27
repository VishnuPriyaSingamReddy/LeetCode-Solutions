class Solution {
    public int shipWithinDays(int[] nums, int days) {
        //like koko banan 
        int f=max(nums);
        int l= sum(nums);

        int r=l;

        while(f<=l){
         int mid=(f+l)/2;
         int ans=func(nums,mid);
         if(ans>days){
         f=mid+1;
         }
         if(ans<=days){
         r=mid;
         l=mid-1;
         }
        }

    return r;

    }

 //days check
     static int func(int nums[],int mid){
      int days=1;
      int i=0;
      int sum=0;
      while(i<nums.length){
        if(sum+nums[i]<=mid){
            sum+=nums[i];
        }else{
            sum=0;
            sum+=nums[i];
            days++;
        } 
        i++;

      }
      return days;
    }

    static int max(int[] nums){
        int max=nums[0];
        for(int num:nums){
         max=Math.max(max,num);
        }
        return max;
    }

       
     static int sum(int[] nums){
        int sum=0;
        for(int num:nums){
         sum+=num;
        }
        return sum;
    }
}
