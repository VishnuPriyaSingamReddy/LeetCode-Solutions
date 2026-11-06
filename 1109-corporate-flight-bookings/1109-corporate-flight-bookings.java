class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int diff[]=new int[n];
        for(int i[]:bookings){
            diff[i[0]-1]+=i[2];
            if(i[1]<n)
                diff[i[1]]-=i[2];
        }
        for(int i=1;i<n;i++)
            diff[i]+=diff[i-1];
        return diff;
    }
}