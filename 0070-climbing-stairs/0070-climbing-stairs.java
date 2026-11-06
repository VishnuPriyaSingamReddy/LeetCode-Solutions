class Solution {
    int arr[];
    public int climbStairs(int n) {
        arr=new int[n+1];
        return fib(n);
        
    }
    public int fib(int n){
        if(n<=1)
            return 1;
        if (arr[n]!=0)
            return arr[n];
        arr[n]=fib(n-1)+fib(n-2);
        return arr[n];
    }
}