/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        //bfs method (level by level) = queue 
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        //process the queue 
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i<n; i++){
                Node temp = q.pop();
                level.add(temp.val);
                for(Node child: temp.children)
                q.offer(child);
                //check for child 
                //if(temp.left!=null) q.offer(temp.left);
                //if(temp.right!=null) q.offer(temp.right);
            }
            res.add(level);
        }
        return res;
    }
}