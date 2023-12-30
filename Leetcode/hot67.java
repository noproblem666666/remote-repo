package Leetcode;

import java.util.Stack;

//二叉树的最近公共祖先
public class hot67 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {      //用栈存储祖先
        Stack<TreeNode> stack1 = new Stack<>();     //存储p的祖先
        Stack<TreeNode> stack2 = new Stack<>();     //存储q的祖先
        findAncestor(stack1,root,p);
        findAncestor(stack2,root,q);
        int length = stack1.size()-stack2.size();
        if(length>0){              //共同祖先的深度一定相同，所以先让他们深度相同
            while(length>0){
                stack1.pop();
                length--;
            }
        }else{
            while(length<0){
                stack2.pop();
                length++;
            }
        }
        TreeNode i=new TreeNode(0);
        TreeNode j=new TreeNode(1);
        while(i.val!=j.val){
            i = stack1.pop();
            j = stack2.pop();
        }
        return i;

    }
    //递归
    public boolean findAncestor(Stack<TreeNode> stack , TreeNode root,TreeNode target){   //将一个结点的祖先（包括他自身）存储进栈里
        if(root == null){
            return false;
        }
        stack.push(root);            //注意顺序，因为一个结点也可以是他自己的祖先，所以要把自身当作祖先也放进去
        if(root == target){
            return true;
        }
        if(findAncestor(stack,root.left,target)){            //如果找到目标就一直返回true
            return true;
        }
        if(findAncestor(stack,root.right,target)){
            return true;
        }
        stack.pop();      //说明当前路径没有找到，需要删掉当前路径的结点并且返回false提醒
        return false;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {    //最简单的迭代写法
        if(root == null || root == p || root == q) return root;     //如果遇到目标就返回目标结点
        TreeNode left = lowestCommonAncestor(root.left, p, q);      //递归寻找左子树和右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;                            //目标结点只能在左子树或者右子树
        if(right == null) return left;
        return root;                                             //如果目标结点分别在两侧，那么他就是公共结点
    }


}
