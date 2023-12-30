package Leetcode;

import java.util.LinkedList;

//二叉树中的最大路径和
public class hot46 {
    LinkedList<Integer> list = new LinkedList<>();
    public int maxPathSum(TreeNode root) {
        int temp = maxPathSunHelper(root);
        System.out.println(temp);
        list.add(temp);
        int max =Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {

            int num =list.get(i);        //remove方法不能遍历
            System.out.println(num);
            if(num>max){
                max=num;
            }
        }
        return max;
    }
    public int maxPathSunHelper(TreeNode root){       //思路对了，但是注意题意，以及何时才能都加，何时只加一个
        if(root.right==null&&root.left==null){      //没必要对结点的子结点情况分析，会使得递归更加复杂，直接对尽头的叶子结点判断情况即可

            return root.val;
        }else if(root.right==null){

            if(root.val<0){
                list.add(maxPathSunHelper(root.left));
            }
            return root.val+Math.max(0,maxPathSunHelper(root.left));
        }else if(root.left==null){

            if(root.val<0){
                list.add(maxPathSunHelper(root.right));
            }
            return root.val+Math.max(0,maxPathSunHelper(root.right));
        }else{
            int left = maxPathSunHelper(root.left);
            int right = maxPathSunHelper(root.right);
            if(root.val<0){
                list.add(Math.max(left,right));
            }else{
                list.add(root.val+left+right);
            }
            return root.val+Math.max(Math.max(0,left),Math.max(0,right));     //有时候结点不能都要，路径上的结点不能重复

        }
    }

    int result = Integer.MIN_VALUE;            //题解
    public int maxPathSum2(TreeNode root) {
        dfs(root);
        return result;
    }

    // 函数功能：返回当前节点能为父亲提供的贡献，需要结合上面的图来看！
    private int dfs(TreeNode root) {
        // 如果当前节点为叶子节点，那么对父亲贡献为 0
        if(root == null) return 0;
        // 如果不是叶子节点，计算当前节点的左右孩子对自身的贡献left和right
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 更新最大值，就是当前节点的val 加上左右节点的贡献。
        result = Math.max(result, root.val + left + right);       //计算总收益的时候再都加上，这种情况不能递归，维护一个全局变量
        // 计算当前节点能为父亲提供的最大贡献，必须是把 val 加上！
        int max = Math.max(root.val + left, root.val + right);    //递归返回时只能加一个
        // 如果贡献小于0的话，直接返回0即可！
        return Math.max(max, 0);
    }
}
