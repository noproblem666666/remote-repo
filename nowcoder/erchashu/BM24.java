package nowcoder.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//二叉树的中序遍历
public class BM24 {
    public int[] inorderTraversal (TreeNode root) {   //递归
        // write code here
        if(root==null){
            return new int[0];
        }
        List<Integer> resList = new LinkedList<>();
        inorder(root,resList);
        int[] res = new int[resList.size()];
        int i = 0;
        for (Integer integer : resList) {
            res[i]= integer;
            i++;
        }
        return res;
    }

    public void inorder(TreeNode root,List<Integer> resList){

        if(root.left!=null){                  //也可以将空结点判断放到函数开始
            inorder(root.left,resList);
        }
        resList.add(root.val);
        if(root.right!=null){
            inorder(root.right,resList);
        }

    }


    public int[] inorderTraversal2 (TreeNode root) {     //栈非递归，先找最左子树结点，然后访问当前结点，之后访问右结点
        //添加遍历结果的数组
        List<Integer> list = new ArrayList();
        Stack<TreeNode> s = new Stack<TreeNode>();
        //空树返回空数组
        if(root == null)
            return new int[0];
        //当树节点不为空或栈中有节点时
        while(root != null || !s.isEmpty()){
            //每次找到最左节点
            while(root != null){
                s.push(root);
                root = root.left;
            }
            //访问该节点
            TreeNode node = s.pop();
            list.add(node.val);
            //进入右节点
            root = node.right;
        }
        //返回的结果
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }
}
