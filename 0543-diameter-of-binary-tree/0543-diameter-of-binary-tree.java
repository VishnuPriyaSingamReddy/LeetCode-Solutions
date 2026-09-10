/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxDia = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfsHeight(root);
        return maxDia;
    }
    private int dfsHeight(TreeNode root){
        if(root==null) return 0;
        int lD = dfsHeight(root.left);
        int rD = dfsHeight(root.right);
        //find the maxDia 
        maxDia = Math.max(maxDia, lD+rD);
        return 1+Math.max(lD, rD); 
    }
    
}
