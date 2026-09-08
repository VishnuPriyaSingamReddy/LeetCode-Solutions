class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;

        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        boolean leftToRight = true;

        while(!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < sz; i++) {
                TreeNode temp = q.poll();
                level.add(temp.val);

                if(temp.left != null) q.offer(temp.left);
                if(temp.right != null) q.offer(temp.right);
            }

            if(!leftToRight)
                Collections.reverse(level);

            res.add(level);
            leftToRight = !leftToRight;
        }

        return res;
    }
}