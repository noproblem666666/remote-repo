package Leetcode;

import java.util.*;

//二叉树的层序遍历
public class hot41 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new LinkedList<>();
        }
        List<List<Integer>> resList =new LinkedList<>();
        List<Integer> addList =new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();   //用队列层序遍历
        LinkedList<TreeNode> next = new LinkedList<>();   //存储下一层结点
        queue.add(root);
        while(queue.size()!=0){
            TreeNode node = queue.remove();
            addList.add(node.val);

            if(node.left!=null){
                next.add(node.left);
            }
            if(node.right!=null){
                next.add(node.right);
            }
            if(queue.size()==0){
                resList.add(new LinkedList<>(addList));    //浅拷贝
                addList.clear();
                System.out.println(next.size());
                queue= (LinkedList<TreeNode>) next.clone();     //队列的克隆，也可以把括号内的<TreeNode>去掉
                next.clear();     //切换到下一层
            }
            //System.out.println(next);
        }
        //System.out.println(resList);
        return resList;
    }
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();         //方法二，不需要两个队列，在每次遍历时记录这一层的节点数
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }

        return res;
    }


}
