package nowcoder.erchashu;

import java.util.LinkedList;
import java.util.Queue;

//二叉搜索树的最近公共祖先（一个结点也可以是他自己的祖先）
//Todo：重点记下递归写法
public class BM37 {
    public int lowestCommonAncestor (TreeNode root, int p, int q) {     //寻找两个结点的所有祖先，然后找最后的共同祖先
        // write code here
        Queue<TreeNode> pPath = new LinkedList<>();
        Queue<TreeNode> qPath = new LinkedList<>();
        findPath(root,p,pPath);
        findPath(root,q,qPath);
        int pre = 0;
        while(pPath.peek()==qPath.peek()){
            pre = pPath.peek().val;
            pPath.poll();
            qPath.poll();

        }
        return pre;
    }

    public void findPath(TreeNode root, int target, Queue<TreeNode> queue){
        while(root.val!=target){
            queue.add(root);
            if(root.val>target){
                root=root.left;
            }else{
                root=root.right;
            }
        }
        queue.add(root);     //最后还需要把相同的结点放进去
    }


    public int lowestCommonAncestor2 (TreeNode root, int p, int q) {    //递归写法
        //空树找不到公共祖先
        if(root == null)
            return -1;
        //pq在该节点两边说明这就是最近公共祖先
        if((p >= root.val && q <= root.val) || (p <= root.val && q >= root.val))
            return root.val;
            //pq都在该节点的左边
        else if(p <= root.val && q <= root.val)
            //进入左子树
            return lowestCommonAncestor2(root.left, p, q);
            //pq都在该节点的右边
        else
            //进入右子树
            return lowestCommonAncestor2(root.right, p, q);
    }


}
