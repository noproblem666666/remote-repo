package Leetcode;
//二叉树的直径
public class hot94 {

    //深度优先搜索遍历
    int ans;   //全局变量
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;      //ans代表最大路径上的结点数
        depth(root);
        return ans - 1;    //最后的路径长度为结点数减一
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }


}
