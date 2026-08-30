class Solution:
    def minimumDeletions(self, nums: List[int]) -> int:
        n=len(nums)
        mn=nums.index(min(nums))
        mx=nums.index(max(nums))
        left,right=min(mn,mx),max(mn,mx)
        return min(right+1,n-left,left+1+n-right)