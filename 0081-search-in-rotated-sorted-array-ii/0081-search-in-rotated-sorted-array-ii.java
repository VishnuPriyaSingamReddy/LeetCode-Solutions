class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            //case1 :: if target is in mid pos 
            if(nums[mid]==target) return true;
            //special case because of duplicates
            if(nums[low]==nums[mid]&& 
             nums[mid]==nums[high]) {
                low++; 
                high--;
                continue;
             }

            //case2:: if left array is sorted
            if(nums[low]<=nums[mid]){
                if(nums[low]<=target && target<=nums[mid])
                    high=mid-1;
                else 
                    low=mid+1;
            }
            else{ //case3: if right array is sorted 
                if(nums[mid]<=target && target<=nums[high])
                    low = mid+1;
                else 
                    high = mid-1;
            } 
        }
        return false;
    }
}
