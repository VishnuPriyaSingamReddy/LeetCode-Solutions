class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n=len(nums)
        suf_min=[0]*n
        suf_min[-1]=nums[-1]
        for i in range(n-2,-1,-1):
            suf_min[i]=min(nums[i],suf_min[i+1])
        pref_max=float('-inf')
        for i in range(n):
            pref_max=max(pref_max,nums[i])
            if pref_max-suf_min[i]<=k:
                return i
        return -1
        