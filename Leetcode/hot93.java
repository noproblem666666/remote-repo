package Leetcode;
//把二叉搜索树转换为累加树
public class hot93 {
    public TreeNode convertBST(TreeNode root) {    //深度优先遍历（双递归遍历树）可以画图看出规律
        if(root==null){
            return null;
        }
        covert(root,0);
        return root;
    }
    public void covert(TreeNode root,int father){  //负责先序遍历每个结点，并且传递父结点大于等于的值
        if(root==null){
            return;
        }
        int temp = root.val;
        root.val = sumVal(root.right)+temp+father;  //当前结点的值等于自身值加右子树值的和加上父结点传过来的所有大于其值的和
        covert(root.left,root.val);    //在遍历左子树时，该结点自身，该结点右子树，该结点父结点传来的值一定都是大于它的值
        covert(root.right,father);     //在遍历右子树时，只有该结点父结点传来的值才符合规定（因为根节点的father值为0，所以一直走右子树的话不会影响其值）
    }
    public int sumVal(TreeNode root){     //计算每个结点以及其子树的权值和
        if(root==null){
            return 0;
        }
        int left = sumVal(root.left);
        int right = sumVal(root.right);
        return root.val+left+right;
    }



    //再计算一个结点的值时必须先知道其右子树，计算其左子树时必须知道自身与右子树之和
    int sum = 0;
    public TreeNode convertBST2(TreeNode root) {    //答案，反向中序遍历（右子树，结点，左子树）
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }


}
