package nowcoder.erchashu;

import java.util.ArrayList;
import java.util.Stack;

//判断是不是二叉搜索树
//Todo:本题最佳解法和BM30相似，中序遍历二叉搜索树的值应该为升序
public class BM34 {
    public boolean isValidBST (TreeNode root) {     //递归检查每个子树是否为二叉搜索树（注意不能直接和左子结点右子结点进行比较）
        // write code here
        if(root==null){
            return true;
        }
        if(root.left==null&&root.right==null){
            return true;
        }
        if(root.left==null){
            if(root.val<findMin(root.right)){
                return isValidBST(root.right);
            }else{
                return false;
            }
        }else if(root.right==null){
            if(root.val>findMax(root.left)){
                return isValidBST(root.left);
            }else{
                return false;
            }

        }else{
            if(root.val<findMin(root.right)&&root.val>findMax(root.left)){
                return isValidBST(root.left)&&isValidBST(root.right);
            }else{
                return false;
            }
        }
    }

    public int findMax(TreeNode root){
        while(root.right!=null){
            root=root.right;
        }
        return root.val;
    }
    public int findMin(TreeNode root){
        while(root.left!=null){
            root=root.left;
        }
        return root.val;
    }



    int pre = Integer.MIN_VALUE;
    //中序遍历
    public boolean isValidBST2 (TreeNode root) {     //中序遍历，保存前一个结点与当前结点相比较
        if (root == null)
            return true;
        //先进入左子树
        if(!isValidBST2(root.left))
            return false;
        if(root.val < pre)
            return false;
        //更新最值
        pre = root.val;
        //再进入右子树
        return isValidBST2(root.right);
    }

    public boolean isValidBST3(TreeNode root){   //使用栈
        //设置栈用于遍历
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode head = root;
        //记录中序遍历的数组
        ArrayList<Integer> sort = new ArrayList<Integer>();
        while(head != null || !s.isEmpty()){
            //直到没有左节点
            while(head != null){
                s.push(head);
                head = head.left;
            }
            head = s.pop();
            //访问节点
            sort.add(head.val);
            //进入右边
            head = head.right;
        }
        //遍历中序结果
        for(int i = 1; i < sort.size(); i++){
            //一旦有降序，则不是搜索树
            if(sort.get(i - 1) > sort.get(i))
                return false;
        }
        return true;
    }
}
