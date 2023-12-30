package nowcoder.erchashu;

import java.util.LinkedList;
import java.util.Queue;

//判断是不是完全二叉树
public class BM35 {
    public boolean isCompleteTree (TreeNode root) {    //不用想的太复杂，只要遇到空结点后还有其他非空结点未遍历，就说明不是完全二叉树
        //空树一定是完全二叉树
        if(root == null)
            return true;
        //辅助队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;  //定义一个首次出现的标记位

        boolean notComplete = false;
        while(!queue.isEmpty()){
            cur = queue.poll();
            //标记第一次遇到空节点
            if(cur == null){
                notComplete = true;
                continue;
            }
            //后续访问已经遇到空节点了，说明经过了叶子
            if(notComplete)
                return false;
            queue.offer(cur.left);        //无论是否为空结点都入队
            queue.offer(cur.right);
        }
        return true;
    }
}
