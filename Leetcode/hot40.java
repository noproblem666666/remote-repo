package Leetcode;
//对称二叉树

import java.util.LinkedList;
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
public class hot40 {

    Stack<Integer> left = new Stack<>();   //用栈来存储左右子树的遍历结果
    Stack<Integer> right = new Stack<>();

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        checkLeft(root.left);
        checkRight(root.right);
//        System.out.println(left);
//        System.out.println(right);
        if (left.size() != right.size()) {
            return false;
        }

        while (!left.isEmpty()) {
            int i = left.pop();
            int j = right.pop();
            if (i != j) {
                return false;
            }
        }
        return true;

    }

    public void checkLeft(TreeNode root) {     //都使用先序遍历，但因为是对称的，所以左右顺序不同
        if (root == null) {
            left.push(-999);
            return;
        } else {
            left.push(root.val);
        }
        checkLeft(root.left);
        checkLeft(root.right);

    }

    public void checkRight(TreeNode root) {
        if (root == null) {
            right.push(-999);
            return;
        } else {
            right.push(root.val);
        }
        checkRight(root.right);
        checkRight(root.left);
    }


    public boolean isSymmetric2(TreeNode root) {    //答案递归解法
        if (root == null) {
            return true;
        }
        //调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right);
    }

    boolean dfs(TreeNode left, TreeNode right) {
        //递归的终止条件是两个节点都为空
        //或者两个节点中有一个为空
        //或者两个节点的值不相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        //再递归的比较 左节点的左孩子 和 右节点的右孩子
        //以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    public boolean isSymmetric3(TreeNode root) {     //答案队列解法
        if(root==null || (root.left==null && root.right==null)) {
            return true;
        }
        //用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while(queue.size()>0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if(left==null && right==null) {
                continue;
            }
            if(left==null || right==null) {
                return false;
            }
            if(left.val!=right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }


}
