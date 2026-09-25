class Solution {
    private int dfs(int[][] grid,int r,int c){
        //out of bounds cond along with water
        if(r<0 || c<0 || r>= grid.length || 
        c>=grid[0].length || grid[r][c]==0) return 1;
       
        if(grid[r][c]==2) return 0;
        //visited cell marking 
        grid[r][c]=2;
        if(grid[r][c]==1) return 0;
        
        //check all the four sides and return the perim 
        return dfs(grid, r-1, c)+
        dfs(grid, r+1, c)+
        dfs(grid, r, c-1)+
        dfs(grid, r, c+1) ;
    }
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int perim = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]==1){
                    perim += dfs(grid, i, j);
                }
            }
        }
        return perim;
    }
}