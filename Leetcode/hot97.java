package Leetcode;

import com.sun.source.tree.Tree;

//合并二叉树
public class hot97 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {    //深度优先遍历，直接在root1上操作
        if(root1==null){
            return root2;
        }
        if(root2==null){
            return root1;
        }
        root1.val = root1.val+root2.val;
        findNode(root1,root2);
        return root1;
    }
    public void findNode(TreeNode root1, TreeNode root2){
        if(root1==null||root2==null){
            return;
        }
        findNode(root1.left,root2.left);      //注意这里必须先深度遍历，再改变结点，不然在递归之前root1的结点结构已被改变！！！
        findNode(root1.right,root2.right);

        root1.left = merge(root1.left,root2.left);
        root1.right = merge(root1.right,root2.right);



    }
    public TreeNode merge(TreeNode root1,TreeNode root2){
        if(root1==null){
            return root2;
        }
        if(root2==null){
            return root1;
        }
        //System.out.println(root1.val+"   "+root2.val);
        root1.val=root1.val+root2.val;
        return root1;
    }


    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {   //深度优先遍历，新建一棵树，更加简洁
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }


    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {   //广度优先遍历，使用队列的迭代
        //如果 t1和t2中，只要有一个是null，函数就直接返回
        if(t1==null || t2==null) {
            return t1==null? t2 : t1;
        }
        java.util.LinkedList<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        queue.add(t1);
        queue.add(t2);
        while(queue.size()>0) {
            TreeNode r1 = queue.remove();
            TreeNode r2 = queue.remove();
            r1.val += r2.val;
            //如果r1和r2的左子树都不为空，就放到队列中
            //如果r1的左子树为空，就把r2的左子树挂到r1的左子树上
            if(r1.left!=null && r2.left!=null){
                queue.add(r1.left);
                queue.add(r2.left);
            }
            else if(r1.left==null) {
                r1.left = r2.left;
            }
            //对于右子树也是一样的
            if(r1.right!=null && r2.right!=null) {
                queue.add(r1.right);
                queue.add(r2.right);
            }
            else if(r1.right==null) {
                r1.right = r2.right;
            }
        }
        return t1;
    }




}
