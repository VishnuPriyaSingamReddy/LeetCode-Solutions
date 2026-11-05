class NumMatrix {
    int pref[][];
    public NumMatrix(int[][] matrix) {
        int rows=matrix.length,cols=matrix[0].length;
        pref=new int[rows+1][cols+1];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                pref[i+1][j+1]=pref[i+1][j]+pref[i][j+1]-pref[i][j]+matrix[i][j];
            }
        }
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return pref[row2+1][col2+1]-pref[row2+1][col1]-pref[row1][col2+1]+pref[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */