class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int rows=mat.length,cols=mat[0].length;
        int pref[][]=new int[rows+1][cols+1];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                pref[i+1][j+1]=pref[i+1][j]+pref[i][j+1]-pref[i][j]+mat[i][j];
            
        int res[][]=new int[rows][cols];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++){
                int r1=Math.max(0,i-k);
                int c1=Math.max(0,j-k);
                int r2=Math.min(rows-1,i+k);
                int c2=Math.min(cols-1,j+k);
                res[i][j]=pref[r2+1][c2+1]-pref[r2+1][c1]-pref[r1][c2+1]+pref[r1][c1];
            }
        return res;
        
        
    }
}