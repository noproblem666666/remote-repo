package nowcoder.erchashu;
//二叉树的前序遍历

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
public class BM23 {

    public int[] preorderTraversal (TreeNode root) {    //递归
        // write code here
        if(root==null){
            return new int[0];
        }
        List<Integer> resList = new LinkedList<>();
        preorder(root,resList);
        int[] res = new int[resList.size()];
        int i = 0;
        for (Integer integer : resList) {
            res[i]= integer;
            i++;
        }
        return res;
    }
    public void preorder(TreeNode root,List<Integer> resList){
        resList.add(root.val);
        if(root.left!=null){                  //也可以将空结点判断放到函数开始
            preorder(root.left,resList);
        }
        if(root.right!=null){
            preorder(root.right,resList);
        }

    }

    public int[] preorderTraversal2 (TreeNode root) {      //利用栈非递归，先右后左
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        Stack<TreeNode> s = new Stack<TreeNode>();
        //空树返回空数组
        if(root == null)
            return new int[0];
        //根节点先进栈
        s.push(root);
        while(!s.isEmpty()){
            //每次栈顶就是访问的元素
            TreeNode node = s.pop();
            list.add(node.val);
            //如果右边还有右子节点进栈
            if(node.right != null)
                s.push(node.right);
            //如果左边还有左子节点进栈
            if(node.left != null)
                s.push(node.left);
        }
        //返回的结果
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }
}
