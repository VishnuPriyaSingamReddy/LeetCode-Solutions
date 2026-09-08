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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root==null) return res;
        //bfs method (level by level) = queue 
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        //process the queue 
        while(!q.isEmpty()){
            int size = q.size();
            long sum=0;
            for(int i=0; i<size; i++){
                TreeNode temp = q.pop();
                sum += temp.val;
                //check for child 
                if(temp.left!=null) q.offer(temp.left);
                if(temp.right!=null) q.offer(temp.right);
            }
            res.add((double)sum/size);
        }
        return res;
    }
}
