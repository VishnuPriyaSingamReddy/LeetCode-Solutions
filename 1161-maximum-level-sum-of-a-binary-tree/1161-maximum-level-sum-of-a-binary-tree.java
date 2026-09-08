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
    public int maxLevelSum(TreeNode root) {

        // Queue for BFS
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        int level = 1;
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        while (!q.isEmpty()) {

            int size = q.size();
            int sum = 0;

            // Process current level
            for (int i = 0; i < size; i++) {

                TreeNode temp = q.poll();

                sum += temp.val;

                // Add children for next level
                if (temp.left != null) {
                    q.offer(temp.left);
                }

                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }

            // Check if current level has a bigger sum
            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }

            level++;
        }

        return maxLevel;
    }
}