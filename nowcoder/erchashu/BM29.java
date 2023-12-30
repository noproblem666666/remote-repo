package nowcoder.erchashu;

import java.util.Stack;

//二叉树中和为某一值的路径(一)
//Todo：注意理解题意，必须是根结点到叶子结点的路径，一个结点只有左结点为空或右结点为空的情况不算，解法二仍然可以一个函数递归
public class BM29 {
    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        if(root==null){      //空树默认为false，即使sum为0
            return false;
        }
        return hasPath(root,sum);
    }

    public boolean hasPath_(TreeNode root,int sum){     //这种写法包含了所有的子路径，题目要求的必须是到达叶子结点（没有子结点）的路径
        if(root==null){           //如果遍历到空结点就判断此时是否满足总和要求
            return sum == 0;     //简化写法
        }
        //左右子树只要有满足的路径即可返回true
        return hasPath_(root.left,sum-root.val)||hasPath_(root.right,sum-root.val);
    }

    public boolean hasPath(TreeNode root , int sum){
        //System.out.println(root.val+"  "+sum);
        if(root.left==null&&root.right==null){    //如果这个结点的左右子节点都为空，且sum==自身结点值，那么就满足条件
            return sum==root.val;
        }
        if(root.left!=null&&root.right!=null){
            return hasPath(root.left,sum- root.val)||hasPath(root.right,sum-root.val);
        }else if(root.right!=null){
            return hasPath(root.right,sum-root.val);
        }else{
            return hasPath(root.left,sum-root.val);
        }
    }

    public boolean hasPathSum2 (TreeNode root, int sum) {    //理解了题意后，提示可以简洁的写成一个函数的递归
        // 根节点为空，则直接返回false
        if (root == null){
            return false;
        }
        // 只有根节点，且值满足要求，则返回true
        if (root.left == null && root.right == null && root.val == sum){
            return true;
        }
        // 递归遍历
        return hasPathSum2(root.left,sum-root.val)||hasPathSum2(root.right,sum-root.val);
    }


    public boolean hasPathSum3 (TreeNode root, int sum) {   //非递归，用两个栈分别存储节点和相应的路径和
        //空节点找不到路径
        if(root == null)
            return false;
        //栈辅助深度优先遍历
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        //跟随s1记录到相应节点为止的路径和
        Stack<Integer> s2 = new Stack<Integer>();
        s1.push(root);
        s2.push(root.val);
        while(!s1.isEmpty()){
            //弹出相应节点
            TreeNode temp = s1.pop();
            //弹出到该点为止的当前路径和
            int cur_sum = s2.pop();
            //叶子节点且当前路径和等于sum
            if(temp.left == null && temp.right == null && cur_sum == sum)
                return true;
            //左节点及路径和入栈
            if(temp.left != null){
                s1.push(temp.left);
                s2.push(cur_sum + temp.left.val);
            }
            //右节点及路径和入栈
            if(temp.right != null){
                s1.push(temp.right);
                s2.push(cur_sum + temp.right.val);
            }
        }
        return false;
    }
}
