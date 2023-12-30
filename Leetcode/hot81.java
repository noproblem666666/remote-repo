package Leetcode;

import java.util.HashMap;

//打家劫舍（树形动态规划）
public class hot81 {
    public int rob(TreeNode root) {   //暴力递归（最优子结构）（会超出时间限制）
        if (root == null) return 0;    //递归计算每个结点的能偷取最大值（比较自身加四个孙子与两个儿子那个大）

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));   //由于在计算儿子的儿子和孙子时存在重复计算，导致时间复杂度很高
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    public int rob2(TreeNode root) {     //使用记忆化改进（解决重复子问题）
        HashMap<TreeNode, Integer> memo = new HashMap<>();   //树形递归不能用数组，用哈希表存储，key是结点，value是能该结点能偷取的最大金额
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    public int rob3(TreeNode root) {     //递归结合动态规划（最优解）
        int[] result = robInternal(root);            //一个结点只有偷与不偷的两种状态
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        //状态转移方程
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);    //如果当前结点不偷，那么子结点可偷可不偷
        result[1] = left[0] + right[0] + root.val;                            //如果当前结点偷，那么子结点必不偷，再加上自身的值

        return result;         //返回数组，让上一层或者最后的根判断那个更大（不能直接返回最大，因为状态转移方程需要记忆两种状态）
    }




}
