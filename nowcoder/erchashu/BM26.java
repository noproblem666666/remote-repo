package nowcoder.erchashu;

import com.sun.source.tree.Tree;

import java.util.*;

//二叉树的层序遍历
public class BM26 {
    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {    //利用两个队列来记录层数
        // write code here
        if(root==null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue1 = new LinkedList<>();   //存储当前层
        Queue<TreeNode> queue2 = new LinkedList<>();   //存储下一层
        queue1.add(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        while(!queue1.isEmpty()){
            TreeNode node = queue1.poll();
            temp.add(node.val);
            if(node.left!=null){
                queue2.add(node.left);
            }
            if(node.right!=null){
                queue2.add(node.right);
            }
            if(queue1.isEmpty()){
                res.add(new ArrayList<>(temp));    //要用浅拷贝，否则之后temp的操作会影响res中的结果
                temp.clear();
                while(!queue2.isEmpty()){
                    queue1.add(queue2.poll());
                }
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> levelOrder2 (TreeNode root) {   //答案通过记录每层的队列大小来记录层数
        ArrayList<ArrayList<Integer> > res = new ArrayList();
        if(root == null)
            //如果是空，则直接返回空数组
            return res;
        //队列存储，进行层次遍历
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            //记录二叉树的某一行
            ArrayList<Integer> row = new ArrayList();
            int n = q.size();
            //因先进入的是根节点，故每层节点多少，队列大小就是多少
            for(int i = 0; i < n; i++){
                TreeNode cur = q.poll();
                row.add(cur.val);
                //若是左右孩子存在，则存入左右孩子作为下一个层次
                if(cur.left != null)
                    q.add(cur.left);
                if(cur.right != null)
                    q.add(cur.right);
            }
            //每一层加入输出
            res.add(row);
        }
        return res;
    }

    ArrayList<ArrayList<Integer> > res = new ArrayList();    //递归方法
    void traverse(TreeNode root, int depth) {
        if(root != null){
            //新的一层
            if(res.size() < depth){
                ArrayList<Integer> row = new ArrayList();
                res.add(row);
                row.add(root.val);
                //读取该层的一维数组，将元素加入末尾
            }else{
                ArrayList<Integer> row = res.get(depth - 1);
                row.add(root.val);
            }
        }
        else
            return;
        //递归左右时深度记得加1
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
    public ArrayList<ArrayList<Integer>> levelOrder3 (TreeNode root) {
        if(root == null)
            //如果是空，则直接返回
            return res;
        //递归层次遍历
        traverse(root, 1);
        return res;
    }
}
