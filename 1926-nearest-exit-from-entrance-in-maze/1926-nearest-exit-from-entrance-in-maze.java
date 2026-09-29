class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        //int stepCount = 0 ;
        int n = maze.length;//rows 
        int m = maze[0].length;//cols 
        Deque<int[]> dq = new ArrayDeque<>();
        //dq.offer(entrance);
        dq.offer(new int[]{entrance[0], entrance[1], 0});
        //int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        //mark entrance as visited
        maze[entrance[0]][entrance[1]]='+';
        //process the queue
        while(!dq.isEmpty()){
            int[] temp = dq.poll();//[1,2]
            int x = temp[0];
            int y = temp[1];
            int steps = temp[2];
            
            for(int i=0; i<4; i++){
                int r = x+dr[i];
                int c = y+dc[i];
                //outofbounds condtion
                if(r<0 || c<0 || r>=n || c>=m) continue;
                
                //if wall occurs in a cell 
                if(maze[r][c]=='+') continue;
                
                if(maze[r][c]=='.'){
                    dq.offer(new int[]{r,c,steps+1});
                    //when we are at border
                    //we need to return stepcount 
                    if(r==0 ||r==n-1 ||c==0 ||c==m-1){
                        return steps+1;
                    }
                }
                //mark visited cells 
                maze[r][c]='+';
            }
        }
        return -1;
    }
}
