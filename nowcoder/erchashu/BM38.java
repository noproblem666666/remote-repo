package nowcoder.erchashu;

import java.util.*;

//在二叉树中找到两个节点的最近公共祖先（相比上一题不同就是不是搜索树了）
//Todo:需要认真学习
public class BM38 {
    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        //记录遍历到的每个节点的父节点。
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root.val, Integer.MIN_VALUE);//根节点没有父节点，给他默认一个值
        queue.add(root);
        //直到两个节点都找到为止。
        while (!parent.containsKey(o1) || !parent.containsKey(o2)) {
            //队列是一边进一边出，这里poll方法是出队，
            TreeNode node = queue.poll();
            if (node.left != null) {
                //左子节点不为空，记录下他的父节点
                parent.put(node.left.val, node.val);
                //左子节点不为空，把它加入到队列中
                queue.add(node.left);
            }
            //右节点同上
            if (node.right != null) {
                parent.put(node.right.val, node.val);
                queue.add(node.right);
            }
        }
        Set<Integer> ancestors = new HashSet<>();
        //记录下o1和他的祖先节点，从o1节点开始一直到根节点。
        while (parent.containsKey(o1)) {
            ancestors.add(o1);
            o1 = parent.get(o1);
        }
        //查看o1和他的祖先节点是否包含o2节点，如果不包含再看是否包含o2的父节点……
        while (!ancestors.contains(o2))
            o2 = parent.get(o2);
        return o2;
    }


    public int lowestCommonAncestor2(TreeNode root, int o1, int o2) {      //递归写法
        return helper(root, o1, o2).val;
    }

    public TreeNode helper(TreeNode root, int o1, int o2) {
        if (root == null || root.val == o1 || root.val == o2)
            return root;
        TreeNode left = helper(root.left, o1, o2);
        TreeNode right = helper(root.right, o1, o2);
        //如果left为空，说明这两个节点在root结点的右子树上，我们只需要返回右子树查找的结果即可
        if (left == null)
            return right;
        //同上
        if (right == null)
            return left;
        //如果left和right都不为空，说明这两个节点一个在root的左子树上一个在root的右子树上，
        //我们只需要返回cur结点即可。
        return root;
    }
}
