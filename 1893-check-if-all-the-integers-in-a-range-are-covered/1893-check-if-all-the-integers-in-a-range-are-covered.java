class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int diff[]=new int[52];
        for(int i[]:ranges){
            diff[i[0]]++;
            diff[i[1]+1]--;
        }
        int pref=0;
        for(int i=0;i<=51;i++){
            pref+=diff[i];
            if(i>=left&&i<=right&&pref==0)
                return false;
        }
        return true;
    }
}