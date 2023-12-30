package nowcoder.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//二叉树的后序遍历
public class  BM25 {
    public int[] postorderTraversal (TreeNode root) {
        // write code here
        if(root==null){
            return new int[0];
        }
        List<Integer> resList = new LinkedList<>();
        postorder(root,resList);
        int[] res = new int[resList.size()];
        int i = 0;
        for (Integer integer : resList) {
            res[i]= integer;
            i++;
        }
        return res;
    }

    public void postorder(TreeNode root,List<Integer> resList){

        if(root.left!=null){                  //也可以将空结点判断放到函数开始
            postorder(root.left,resList);
        }
        if(root.right!=null){
            postorder(root.right,resList);
        }
        resList.add(root.val);
    }

    public int[] postorderTraversal2 (TreeNode root) {    //栈非递归，先找最左结点，之后看这个结点有没有右子结点（需要记录该结点是否被访问过了）
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode pre = null;
        while(root != null || !s.isEmpty()){
            //每次先找到最左边的节点
            while(root != null){
                s.push(root);
                root = root.left;
            }
            //弹出栈顶
            TreeNode node = s.pop();
            //如果该元素的右边没有或是已经访问过
            if(node.right == null || node.right == pre){
                //访问中间的节点
                list.add(node.val);
                //且记录为访问过了
                pre = node;
            }else{
                //该节点入栈
                s.push(node);
                //先访问右边
                root = node.right;
            }
        }
        //返回的结果
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }
}
