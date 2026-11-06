class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int pref[]=new int[1001];
        for(int i[]:trips){
            pref[i[1]]+=i[0];
            pref[i[2]]-=i[0];
        }
        int pr=0;
        for(int i:pref){
            pr+=i;
            if(pr>capacity)
                return false;
        }
        return true;
    }
}