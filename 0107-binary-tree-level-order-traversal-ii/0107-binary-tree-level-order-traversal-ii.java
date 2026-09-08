class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        //bfs =>queue 
        Deque<TreeNode> q = new ArrayDeque<>();
        //add the root into queue 
        q.offer(root);
        //for zigzag process create a flag 
        //boolean flag = true;
        //process the queue
        while(!q.isEmpty()){
            //find the size of queue 
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i<sz; i++){
                TreeNode temp = q.pop();
                level.add(temp.val);
               // else level.add(0, temp.val);
                if(temp.left!=null) q.offer(temp.left);
                if(temp.right!=null) q.offer(temp.right);
            }
            res.add(0,level);
            //change the direction
           // flag = !flag;
        }
        return res;
    }
}