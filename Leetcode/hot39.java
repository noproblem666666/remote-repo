package Leetcode;
//验证二叉搜索树
public class hot39 {
    public boolean isValidBST(TreeNode root) {
        return BST(root);

    }

    public boolean BST(TreeNode root){          //遍历每个结点，检查其是否符合二叉搜索树的规则
        if(root.left!=null&&root.right!=null){
            int max = findMax(root.left);
            int min = findMin(root.right);
            if(root.left.val>=root.val||root.right.val<=root.val||max>=root.val||min<=root.val){
                return false;
            }else{
                return BST(root.left)&&BST(root.right);
            }
        }else if(root.left!=null){
            int max = findMax(root.left);
            if(root.left.val>=root.val||max>=root.val){
                return false;
            }else{
                return BST(root.left);
            }
        }else if(root.right!=null){
            int min = findMin(root.right);
            if(root.right.val<=root.val||min<=root.val){
                return false;
            }else{
                return BST(root.right);
            }
        }else{
            return true;
        }
    }

    public int findMax(TreeNode root){     //找到左子树中的最大值（不一定是最大值，但是对每个结点遍历可以确定）
        while(root.right!=null){
            root=root.right;
        }
        return root.val;

    }
    public int findMin(TreeNode root){     //找到右子树中的最小值（不一定是最小值，但是对每个结点遍历可以确定）
        while(root.left!=null){
            root=root.left;
        }
        return root.val;
    }

    long pre = Long.MIN_VALUE;                     //答案中序遍历，更加简洁
    public boolean isValidBST2(TreeNode root) {    //pre的作用是记住前一个结点的值
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public boolean isValidBST3(TreeNode root) {          //答案递归，更加简洁（确定上下界）
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }


}
