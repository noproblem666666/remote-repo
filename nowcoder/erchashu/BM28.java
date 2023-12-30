package nowcoder.erchashu;

import java.util.LinkedList;
import java.util.Queue;

//二叉树的最大深度
public class BM28 {
    public int maxDepth(TreeNode root) {    //递归（直接将当前层的最大深度返回，就不用在参数中加入深度了）
        // write code here
        if (root == null) {     //判断空结点情况，这样就不用再判断左子节点和右子结点的情况了
            return 0;
        }
        return Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));   //递归左右子树并返回最大的深度
    }

    public int maxDepth2(TreeNode root) {     //用队列层次遍历
        if (root == null)
            return 0;
        // 队列，每次while循环保存当前层的所有结点
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int res = 0;
        queue.add(root);
        // 遍历每一层
        while (!queue.isEmpty()) {
            int size = queue.size();      //当前层的结点个数
            // 遍历当前层每个结点
            for (int i = 0; i < size; i++) {     //将当前层的结点都遍历
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 记录层数
            res++;
        }
        return res;
    }
}
