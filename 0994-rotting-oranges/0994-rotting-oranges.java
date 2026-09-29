class Solution {
    public int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int minutes=0;
        int fresh=0;
        Deque<int[]> dq=new ArrayDeque<>();
        // Find rotten and fresh oranges
        for(int r=0;r<n;r++){
            for(int c=0;c<m;c++){
                if(grid[r][c]==2){
                    dq.offer(new int[]{r,c});
                }
                else if(grid[r][c]==1){
                    fresh++;
                }
            }
        }
        // Direction array
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};

        // BFS
        while(!dq.isEmpty() && fresh>0){

            int size=dq.size();

            for(int i=0;i<size;i++){

                int[] temp=dq.poll();
                int x=temp[0];
                int y=temp[1];

                for(int[] d:dir){

                    int r=x+d[0];
                    int c=y+d[1];

                    // Out of bounds
                    if(r<0||c<0||r>=n||c>=m)
                        continue;

                    // Fresh orange
                    if(grid[r][c]==1){
                        grid[r][c]=2;
                        fresh--;
                        dq.offer(new int[]{r,c});
                    }
                }
            }

            // One complete BFS level = one minute
            minutes++;
        }

        // Fresh oranges still remaining
        if(fresh>0)
            return -1;

        return minutes;
    }
}