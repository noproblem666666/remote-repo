package nowcoder.erchashu;

import java.util.Stack;

//二叉树的镜像
public class BM33 {
    public TreeNode Mirror (TreeNode pRoot) {    //递归镜像
        // write code here
        if(pRoot==null){
            return null;
        }
        TreeNode temp = pRoot.left;
        pRoot.left=pRoot.right;
        pRoot.right = temp;
        Mirror(pRoot.left);
        Mirror(pRoot.right);
        return pRoot;
    }

    public TreeNode Mirror2 (TreeNode pRoot) {     //辅助栈
        // write code here
        if(pRoot == null) return null;
        // 构建辅助栈
        Stack<TreeNode> stack = new Stack<>();
        // 根节点入栈
        stack.add(pRoot);
        while(!stack.isEmpty()) {
            // 节点出栈
            TreeNode node = stack.pop();
            // 根节点的左右子树入栈
            if(node.left != null) stack.add(node.left);
            if(node.right != null) stack.add(node.right);
            // 左右子树交换
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return pRoot;
    }
}
