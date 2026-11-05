class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int rows=grid.length;
        int cols=grid[0].length;
        int pref[][]=new int[rows+1][cols+1],c=0;
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++){
                pref[i+1][j+1]=pref[i+1][j]+pref[i][j+1]-pref[i][j]+grid[i][j];
                if(pref[i+1][j+1]<=k) c++;
            }
        return c;
    }
}