package nowcoder.erchashu;

//判断是不是平衡二叉树
public class BM36 {
    public boolean IsBalanced_Solution(TreeNode pRoot) {   //递归验证所有的结点
        // write code here
        if(pRoot == null){
            return true;
        }

        if(Math.abs(countHeight(pRoot.left)-countHeight(pRoot.right))>1){
            return false;
        }
        return IsBalanced_Solution(pRoot.left)&&IsBalanced_Solution(pRoot.right);
    }

    public int countHeight(TreeNode root) {     //用来计算结点的高度
        if (root == null) {
            return 0;
        }
        int left = countHeight(root.left);
        int right = countHeight(root.right);
        return Math.max(left,right) + 1;
    }


    //经过优化的写法，如果遇到不是平衡树就直接返回
    boolean isBalanced = true;         //全局变量，可以在递归函数里不返回boolean值直接判断是否为平衡树
    public boolean IsBalanced_Solution2(TreeNode root) {
        TreeDepth(root);
        return isBalanced;
    }
    public int TreeDepth(TreeNode root) {       //把-1当作不是平衡树的标记
        if(root == null)
            return 0;
        int l = TreeDepth(root.left);
        if(l == -1)  return -1;  // 提前返回
        int r = TreeDepth(root.right);
        if(r == -1)  return -1;  // 提前返回
        if(Math.abs(l-r) > 1){
            isBalanced = false;  // 不是平衡树
            return -1;  // 加一个标记-1，已经不可能是平衡树了
        }

        return Math.max(l,r)+1;
    }
}
