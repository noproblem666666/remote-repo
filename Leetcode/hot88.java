package Leetcode;

import java.util.HashMap;
import java.util.Map;

//路径总和|||
public class hot88 {
    //前缀和（现在通过不了，需要把key改成long，看解法三）
    Map<Integer,Integer> mem = new HashMap<Integer,Integer>();//保存前缀树
    int target;
    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        mem.put(0,1);//前缀树为0的个数至少是一个
        return dfs(root,0);
    }
    public int dfs(TreeNode root,int curSum){
        if(root == null) return 0;
        curSum += root.val;//得到当前前缀树的值
        int res = 0;

        //找map中是否有key值为curSum-target的value值，如果没有那就返回0
        res = mem.getOrDefault(curSum-target,0);//得到我们想要前缀树的个数，想要前缀树值就是当前前缀树值减去目标值
        mem.put(curSum,mem.getOrDefault(curSum,0)+1);//将当前前缀树的值保存
        int left = dfs(root.left,curSum);//遍历左边
        int right = dfs(root.right,curSum);//遍历右边
        mem.put(curSum,mem.get(curSum)-1);//防止左边前缀树影响右边前缀树，左边前缀树可能有个为6，右边正好想要一个前缀树为6的，这样子就出错了
        return res+left+right;//结果是当前节点前缀树的个数加上左边满足的个数加右边满足的个数
    }


    int ans, t;
    public int pathSum2(TreeNode root, int _t) {  //深度优先遍历，双递归
        t = _t;
        dfs1(root);
        return ans;
    }
    void dfs1(TreeNode root) {   //负责搜索所有节点
        if (root == null) return;
        dfs2(root, root.val);
        dfs1(root.left);
        dfs1(root.right);
    }
    void dfs2(TreeNode root, long val) {   //负责搜索以一个节点为根节点的所有路径，并计算路径和
        if (val == t) ans++;
        if (root.left != null) dfs2(root.left, val + root.left.val);
        if (root.right != null) dfs2(root.right, val + root.right.val);
    }


    Map<Long, Integer> map = new HashMap<>();
    //int ans, t;
    public int pathSum3(TreeNode root, int _t) {
        if (root == null) return 0;
        t = _t;
        map.put(0L, 1);
        dfs(root, root.val);
        return ans;
    }
    void dfs(TreeNode root, long val) {
        if (map.containsKey(val - t)) ans += map.get(val - t);
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) dfs(root.left, val + root.left.val);
        if (root.right != null) dfs(root.right, val + root.right.val);
        map.put(val, map.getOrDefault(val, 0) - 1);
    }


}
