class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long both = Math.min(need1, need2);

        long ans = both * costBoth
                 + (long)(need1 - both) * cost1
                 + (long)(need2 - both) * cost2;

        ans = Math.min(ans, (long) need1 * cost1 + (long) need2 * cost2);

        ans = Math.min(ans, (long) Math.max(need1, need2) * costBoth);

        return ans;
    }
}