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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // If tree is empty
        if (root == null) {
            return res;
        }

        // BFS using queue
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        // Process level by level
        while (!q.isEmpty()) {
            int size = q.size();
            //List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode temp = q.pop();

                if(i==size-1) res.add(temp.val);

                // Add children to queue
                if (temp.left != null) {
                    q.offer(temp.left);
                }

                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }

            //res.add(level);
        }

        return res;
    }
}