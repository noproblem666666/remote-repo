package Leetcode;
//二叉树的中序遍历


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */



class TreeNode{     //自己定义TreeNode之后就不能再导TreeNode包了
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class hot37 {
    List<Integer> resList = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {     //递归方法
        if(root==null){     //输入空树时直接返回
            return resList;
        }
        mid(root);
        return resList;
    }
    public void mid(TreeNode root){
        if(root.left!=null){
            mid(root.left);
        }
        resList.add(root.val);
        if(root.right!=null){
            mid(root.right);
        }
    }
    public List<Integer> inorderTraversal2(TreeNode root) {   //迭代方法
        if(root==null){
            return resList;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            while(root.left!=null&&root.left.val!=-999){    //用-999标记该结点是否被访问过，防止无限入栈
                stack.push(root);
                root=root.left;
            }
            resList.add(root.val);
            root.val=-999;
            if(root.right!=null&&root.right.val!=-999){    //用-999标记该结点是否被访问过，防止无限入栈
                stack.push(root.right);
            }
            root=stack.pop();
        }
        return resList;
    }
    public List<Integer> inorderTraversal3(TreeNode root) {   //官方的非迭代方法
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(stack.size()>0 || root!=null) {
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            //这是模拟递归的调用
            if(root!=null) {
                stack.add(root);
                root = root.left;
                //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                //然后转向右边节点，继续上面整个过程
            } else {
                TreeNode tmp = stack.pop();
                res.add(tmp.val);
                root = tmp.right;    //取出后直接转到右子结点，使得左子结点不会无限入栈
            }
        }
        return res;
    }

}
