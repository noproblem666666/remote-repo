package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//二叉树的最大深度
public class hot42 {                 //与41想法相同，用层序遍历统计深度
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int n=0;
        if (root != null) {
            n++;
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int i = queue.size();         //方法二，不需要两个队列，在每次遍历时记录这一层的节点数
            for (int i1 = 0; i1 < i; i1++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if(!queue.isEmpty()){
                n++;
            }
        }

        return n;
    }

    public int maxDepth2(TreeNode root) {      //最简单的深度优先遍历，递归写法
        if(root == null) {
            return 0;        //遍历到最底层空结点，递归出口
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;     //当前层的深度为左右结点中最大的深度加上本身这一层
        }
    }


}
