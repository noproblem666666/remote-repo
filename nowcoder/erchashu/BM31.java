package nowcoder.erchashu;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

//Todo：同时对二叉树进行两个遍历
//对称的二叉树
public class BM31 {

    public boolean isSymmetrical (TreeNode pRoot) {
        // write code here
        if(pRoot == null){
            return true;
        }
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        addLeft(pRoot.left,left);
        addRight(pRoot.right,right);
        System.out.println(left);
        System.out.println(right);
        return left.equals(right);
    }
    public void addLeft(TreeNode root , LinkedList<Integer> list){  //先序遍历添加所有结点
        if(root==null){
            list.add(1001);     //因为结点的值绝对值小于等于1000，不能直接返回，因为那样可能混淆很多种情况
            return;
        }else{
            list.add(root.val);
        }
        addLeft(root.left,list);
        addLeft(root.right,list);
    }

    public void addRight(TreeNode root , LinkedList<Integer> list){  //注意这里是先左后右
        if(root==null){
            list.add(1001);     //因为结点的值绝对值小于等于1000，不能直接返回，因为那样可能混淆很多种情况
            return;
        }else{
            list.add(root.val);
        }
        addRight(root.right,list);
        addRight(root.left,list);
    }


    boolean recursion(TreeNode root1, TreeNode root2){   //同时对二叉树进行两个先序遍历（但是左右颠倒）
        //可以两个都为空
        if(root1 == null && root2 == null)
            return true;
        //只有一个为空或者节点值不同，必定不对称
        if(root1 == null || root2 == null || root1.val != root2.val)
            return false;
        //每层对应的节点进入递归比较
        return recursion(root1.left, root2.right) && recursion(root1.right, root2.left);   //只有都为真时才能返回真
    }
    boolean isSymmetrical2(TreeNode pRoot) {
        return recursion(pRoot, pRoot);
    }


    boolean isSymmetrical3(TreeNode pRoot) {     //用队列分别对左右子树进行层序遍历
        //空树为对称的
        if(pRoot == null)
            return true;
        //辅助队列用于从两边层次遍历
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        q1.offer(pRoot.left);
        q2.offer(pRoot.right);
        while(!q1.isEmpty() && !q2.isEmpty()){
            //分别从左边和右边弹出节点
            TreeNode left = q1.poll();
            TreeNode right = q2.poll();
            //都为空暂时对称
            if(left == null && right == null)
                continue;
            //某一个为空或者数字不相等则不对称
            if(left == null || right == null || left.val != right.val)
                return false;
            //从左往右加入队列
            q1.offer(left.left);      //空结点也需要加入，否则有很多情况遍历结果相同但是树形不相同
            q1.offer(left.right);
            //从右往左加入队列
            q2.offer(right.right);
            q2.offer(right.left);
        }
        //都检验完都是对称的
        return true;
    }
}
