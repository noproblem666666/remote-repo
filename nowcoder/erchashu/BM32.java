package nowcoder.erchashu;

import java.util.LinkedList;
import java.util.Queue;

//合并二叉树
public class BM32 {   //要求空间复杂度为o(1)（不新建结点）


    //最简洁的写法
    public TreeNode mergeTrees (TreeNode t1, TreeNode t2) {   //对两个二叉树进行递归，以t1为基础合并树
        // write code here
        if(t1==null){        //只要有一个结点为空，就不能再继续递归下去，直接返回结点
            return t2;
        }
        if(t2==null){
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left,t2.left);        //如果当前结点合并，分别递归左子树与右子树
        t1.right = mergeTrees(t1.right,t2.right);
        return t1;         //最后返回该结点
    }

    public TreeNode mergeTrees2 (TreeNode t1, TreeNode t2) {   //用队列层次遍历
        //若只有一个节点返回另一个，两个都为null自然返回null
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        //合并根节点
        TreeNode head = new TreeNode(t1.val + t2.val);
        //连接后的树的层次遍历节点
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        //分别存两棵树的层次遍历节点
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        q.offer(head);
        q1.offer(t1);
        q2.offer(t2);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            TreeNode right1 = node1.right;
            TreeNode right2 = node2.right;
            if(left1 != null || left2 != null){
                //两个左节点都存在
                if(left1 != null && left2 != null){
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    //新节点入队列
                    q.offer(left);
                    q1.offer(left1);
                    q2.offer(left2);
                    //只连接一个节点
                }else if(left1 != null)
                    node.left = left1;
                else
                    node.left = left2;
            }
            if(right1 != null || right2 != null){
                //两个右节点都存在
                if(right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    //新节点入队列
                    q.offer(right);
                    q1.offer(right1);
                    q2.offer(right2);
                    //只连接一个节点
                }else if(right1 != null)
                    node.right = right1;
                else
                    node.right = right2;
            }
        }
        return head;
    }
}
