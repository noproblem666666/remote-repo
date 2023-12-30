package nowcoder.erchashu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

//重建二叉树
//根据前序遍历和中序遍历 Todo：记一下过程  还有Arrays.copyOfRange函数
//前序是中左右，中序是左中右，对于每次递归，差别就在根节点的位置
public class BM40 {
    public TreeNode reConstructBinaryTree(int [] pre,int [] vin) {       //递归
        int n = pre.length;
        int m = vin.length;
        //每个遍历都不能为0
        if(n == 0 || m == 0)
            return null;
        //构建根节点
        TreeNode root = new TreeNode(pre[0]);
        for(int i = 0; i < vin.length; i++){
            //找到中序遍历中的前序第一个元素
            if(pre[0] == vin[i]){
                //前序遍历跳过第一个结点，中序遍历跳过中间找到的同一个结点，左右相对次序是一样的
                //构建左子树
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
                //构建右子树
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
                break;
            }
        }
        return root;
    }


    public TreeNode reConstructBinaryTree2(int [] pre,int [] vin) {  //使用栈的非递归，整个过程类似非递归前序遍历
        int n = pre.length;
        int m = vin.length;
        //每个遍历都不能为0
        if(n == 0 || m == 0)
            return null;
        Stack<TreeNode> s = new Stack<TreeNode>();
        //首先建立前序第一个即根节点
        TreeNode root = new TreeNode(pre[0]);
        TreeNode cur = root;
        for(int i = 1, j = 0; i < n; i++){
            //要么旁边这个是它的左节点
            if(cur.val != vin[j]){
                cur.left = new TreeNode(pre[i]);
                s.push(cur);
                //要么旁边这个是它的右节点，或者祖先的右节点
                cur = cur.left;
            }else{
                j++;
                //弹出到符合的祖先
                while(!s.isEmpty() && s.peek().val == vin[j]){
                    cur = s.pop();
                    j++;
                }
                //添加右节点
                cur.right = new TreeNode(pre[i]);
                cur = cur.right;
            }
        }
        return root;
    }

}
